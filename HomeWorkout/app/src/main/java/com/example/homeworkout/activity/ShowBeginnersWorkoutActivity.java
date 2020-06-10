package com.example.homeworkout.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homeworkout.R;
import com.example.homeworkout.adapter.RecyclerViewAdapter;
import com.example.homeworkout.data_handler.workout_data;

import java.util.ArrayList;
import java.util.List;

public class ShowBeginnersWorkoutActivity extends AppCompatActivity {
    List<workout_data> workouts;
    List<String> workouts_name;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    Button StartWorkouts;
    EditText NOS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_beginners_workout);
        StartWorkouts = findViewById(R.id.StartWorkouts);
//        List<String> AllWorkouts = new ArrayList<>();
        recyclerView = findViewById(R.id.workout_list);
        workouts = new ArrayList<>();
        workouts_name = new ArrayList<>();
        //adding in workouts
        workouts.add(new workout_data(R.drawable.pushup, "Push up", "A push-up is a common calisthenics exercise beginning from the prone position. By raising and lowering the body using the arms, push-ups exercise the pectoral muscles, triceps, and anterior deltoids, with ancillary benefits to the rest of the deltoids, serratus anterior, coracobrachialis and the midsection as a whole."));
        workouts.add(new workout_data(R.drawable.diamondpushup, "Diamond Push up", "Diamond push-ups focus mainly on the triceps brachii, the muscle that runs along the back of your upper arm. The triceps work in tandem with the biceps on the front of your upper arm to enable extension and retraction of your forearm. This muscle group also plays a vital role in stabilizing the shoulder, a joint with the greatest range of motion — and, therefore, the most instability — in the body."));
        workouts.add(new workout_data(R.drawable.dips, "Dips", "A dip is an upper-body strength exercise. Narrow, shoulder-width dips primarily train the triceps, with major synergists being the anterior deltoid, the pectoralis muscles (sternal, clavicular, and minor), and the rhomboid muscles of the back (in that order). Wide arm training places additional emphasis on the pectoral muscles, similar in respect to the way a wide grip bench press would focus more on the pectorals and less on the triceps."));
        workouts.add(new workout_data(R.drawable.situp, "Sit up", "The sit-up (or curl-up) is an abdominal endurance training exercise to strengthen, tighten and tone the abdominal muscles. It is similar to a crunch (crunches target the rectus abdominis and also work the biceps and external and internal obliques), but sit-ups have a fuller range of motion and condition additional muscles."));
        workouts.add(new workout_data(R.drawable.legraise, "Leg raise", "To do leg lifts, start by lying flat on the floor with your legs stretched in front of you and your arms at your sides. Then, bend your knees and raise your legs so that your calves are parallel to the ground. Next, keep your abs contracted as you straighten your legs toward the ceiling."));
        workouts.add(new workout_data(R.drawable.plank, "Plank", "The plank (also called a front hold, hover, or abdominal bridge) is an isometric core strength exercise that involves maintaining a position similar to a push-up for the maximum possible time."));
        workouts.add(new workout_data(R.drawable.backraise, "Back Raise", "A hyperextension or back extension is an exercise that works the lower back as well as the mid and upper back, specifically the erector spinae. It may be performed on the ground by lying prone with arms overhead and lifting the arms, upper torso, and legs as far as possible, or using a Roman chair to hold the feet down and hips up. Another version is the bird dog exercise, performed lying on the knees, where one arm and the opposite leg are lifted."));
        workouts.add(new workout_data(R.drawable.squat, "Squat", "A squat is a strength exercise in which the trainee lowers their hips from a standing position and then stands back up. During the descent of a squat, the hip and knee joints flex while the ankle joint dorsiflexes; conversely the hip and knee joints extend and the ankle joint plantarflexes when standing up."));
        workouts.add(new workout_data(R.drawable.burpee, "Burpee", "The burpee, or squat thrust, is a full body exercise used in strength training and as an aerobic exercise. The basic movement is performed in four steps and known as a \"four-count burpee\":[1]\n" +
                "\n" +
                "Begin in a standing position.\n" +
                "Move into a squat position with your hands on the ground. (count 1)\n" +
                "Kick your feet back into a plank position, while keeping your arms extended. (count 2)\n" +
                "Immediately return your feet into squat position. (count 3)\n" +
                "Stand up from the squat position (count 4)"));
        //adding in workout name
        for(workout_data e : workouts) {
            workouts_name.add(e.getWorkout_name());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), workouts);
        recyclerView.setAdapter(recyclerViewAdapter);

//        int random;
//
//        List<Integer> UniqueNumber = new ArrayList<>();
//
//        AllWorkouts.add("PushUp");
//        AllWorkouts.add("Sit up");
//        AllWorkouts.add("BenchPress");
//        AllWorkouts.add("DiamondPushUp");
//        AllWorkouts.add("Squats");
//        AllWorkouts.add("LegPress");
//        AllWorkouts.add("Bicep Curl");
//        AllWorkouts.add("Chinning");
//        AllWorkouts.add("LegRaise");
//        AllWorkouts.add("ButterFly");
//        for(int i=0; i<4; i++) {
//            random = GenerateNumber(AllWorkouts.size());
//            for(int j=0; j<UniqueNumber.size(); j++) {
//                if(UniqueNumber.get(j) == random) {
//                    random = GenerateNumber(AllWorkouts.size());
//                    j=0;
//                }
//            }
//            UniqueNumber.add(random);
//        }
//
//        for(int e : UniqueNumber) {
//            workouts.add(AllWorkouts.get(e));
//        }


//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, workouts);
//        lvworkouts.setAdapter(arrayAdapter);
        NOS = findViewById(R.id.NOS);

        StartWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = NOS.getText().toString();
                if(value.equals("")) {
                    Toast.makeText(ShowBeginnersWorkoutActivity.this, "please enter the number of sets", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(getApplicationContext(), WorkoutActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("NumberOfSets", Integer.parseInt(value));
                    i.putStringArrayListExtra("workout_names", (ArrayList<String>) workouts_name);
                    startActivity(i);
                    finish();
                }

            }
        });
    }
//    public int GenerateNumber(int upper) {
//        Random random = new Random();
//        return random.nextInt(upper);
//    }
}
