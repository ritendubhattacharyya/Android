package com.example.homeworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.homeworkout.activity.SetWorkoutActivity;
import com.example.homeworkout.activity.ShowBeginnersWorkoutActivity;
import com.example.homeworkout.activity.WorkoutActivity;

public class MainActivity extends AppCompatActivity {
    Button workout, set_workout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workout = (Button)findViewById(R.id.workout);
        set_workout = (Button)findViewById(R.id.set_workout);

        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ShowBeginnersWorkoutActivity.class);
                startActivity(i);
            }
        });
        set_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Wait for the update", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), SetWorkoutActivity.class));
            }
        });
    }
}
