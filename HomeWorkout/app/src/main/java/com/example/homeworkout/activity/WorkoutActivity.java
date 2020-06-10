package com.example.homeworkout.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkout.MainActivity;
import com.example.homeworkout.R;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {
    MediaPlayer player;
    TextView timer,workout_name;
    CountDownTimer countDownTimer;
    long setTime = 60000;
    long setBreak = 30000;
    long workBreak = 40000;
    int SetCount = 0;
    int TotalCount = 0;
    int numberOfSets;
    ArrayList<String> workoutNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        timer = findViewById(R.id.timer);
        workout_name = findViewById(R.id.workout_name);

        Intent intent = getIntent();
        numberOfSets = (intent.getIntExtra("NumberOfSets", 0));
        workoutNames = intent.getStringArrayListExtra("workout_names");
        if(player == null) {
            player = new MediaPlayer();
            player = MediaPlayer.create(WorkoutActivity.this, R.raw.workout);
        }
//        Log.d("N_O_S", "onCreate: " + workoutNames);

        CountTimer1();

    }
    public void CountTimer1() {
        workout_name.setText(workoutNames.get(TotalCount));
        play();
        countDownTimer = new CountDownTimer(setTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setTime = millisUntilFinished;
                int minutes = (int)setTime / 60000;
                int seconds = (int)setTime % 60000 / 1000;

                String time;
                time = "" +  minutes;
                time += ":";
                if(seconds < 10) {time += "0" + seconds;}
                else {time += seconds;}
                timer.setText(time);

            }

            @Override
            public void onFinish() {
                setTime = 60000;
                SetCount++;
                Pause();
                if(SetCount==numberOfSets){CountTimer3();}
                else{CountTimer2();}
            }
        }.start();

    }

    public void CountTimer2() {
        workout_name.setText("Rest or Warm up");
        countDownTimer = new CountDownTimer(setBreak, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setBreak = millisUntilFinished;
                int minutes = (int)setBreak / 60000;
                int seconds = (int)setBreak % 60000/1000;

                String time;
                time = "" +  minutes;
                time += ":";
                if(seconds < 10) {time += "0" + seconds;}
                else {time += seconds;}
                timer.setText(time);

            }

            @Override
            public void onFinish() {
                setBreak = 30000;
                CountTimer1();
            }
        }.start();

    }

    public void CountTimer3() {
        workout_name.setText("Warm up");
        countDownTimer = new CountDownTimer(workBreak, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                workBreak = millisUntilFinished;
                int minutes = (int)workBreak / 60000;
                int seconds = (int)workBreak % 60000/1000;

                String time;
                time = "" +  minutes;
                time += ":";
                if(seconds < 10) {time += "0" + seconds;}
                else {time += seconds;}
                timer.setText(time);

            }

            @Override
            public void onFinish() {
                workBreak = 40000;
                TotalCount++;

                if(TotalCount == (workoutNames.size())) {
                    stop();
                    Toast.makeText(getApplicationContext(), "Congratulation You have successfully completed your workout", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    SetCount = 0;
                    CountTimer1();
                }
            }
        }.start();
    }
    // media player methods

    public void play() {
        if(player!=null) {
            Log.d("WorkoutStart", "onCreate: " + "starting");
            player.start();
        }
    }

    public void Pause() {
        if(player!=null) {
            Log.d("WorkoutStart", "onCreate: " + "pausing");
            player.pause();
        }
    }

    public void stop(){
        if(player!=null){
            Log.d("WorkoutStart", "onCreate: " + "stopping");
            player.release();
            player = null;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        stop();
        WorkoutActivity.this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();
        WorkoutActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Pause();
        stop();
        WorkoutActivity.this.finish();
    }
}
