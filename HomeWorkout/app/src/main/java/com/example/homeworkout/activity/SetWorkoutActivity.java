package com.example.homeworkout.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.homeworkout.adapter.AdvncWorkoutAdapter;
import com.example.homeworkout.data_handler.Advance;
import com.example.homeworkout.database_handler.DataBaseHandler;
import com.example.homeworkout.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SetWorkoutActivity extends AppCompatActivity {
    FloatingActionButton add_schedule;
    Spinner days;
    EditText first_workout, second_workout, third_workout, fourth_workout, fifth_workout;
    Button add;
    AlertDialog alert;
    RecyclerView advance_workouts;
    AdvncWorkoutAdapter advncWorkoutAdapter;
    List<Advance> workouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_workout2);

        add_schedule = findViewById(R.id.add_schedule);
        add_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SetWorkoutActivity.this);
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.alert, null);
                days = view.findViewById(R.id.days);
                first_workout = view.findViewById(R.id.first_workout);
                second_workout = view.findViewById(R.id.second_workout);
                third_workout = view.findViewById(R.id.third_workout);
                fourth_workout = view.findViewById(R.id.fourth_workout);
                fifth_workout = view.findViewById(R.id.fifth_workout);
                add = view.findViewById(R.id.add);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.days_resource));

                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                days.setAdapter(arrayAdapter);

                builder.setView(view);
                alert = builder.create();
                alert.show();


                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataBaseHandler dataBaseHandler = new DataBaseHandler(getApplicationContext());;
                        SQLiteDatabase db = dataBaseHandler.getWritableDatabase();
                        List<Advance> calcSize = dataBaseHandler.getall(db);
                        int index;
                        if(calcSize.size() == 0) {
                            index = 0;
                        } else {
                            index = calcSize.get(calcSize.size()-1).getIndex() + 1;
                        }
                        Log.d("index", "onClick: " + index);
                        String day = days.getItemAtPosition(days.getSelectedItemPosition()).toString();
                        String exercise1 = (first_workout.getText().toString().equals("")) ? "null":first_workout.getText().toString();
                        String exercise2 = (second_workout.getText().toString().equals("")) ? "null":second_workout.getText().toString();
                        String exercise3 = (third_workout.getText().toString().equals("")) ? "null":third_workout.getText().toString();
                        String exercise4 = (fourth_workout.getText().toString().equals("")) ? "null":fourth_workout.getText().toString();
                        String exercise5 = (fifth_workout.getText().toString().equals("")) ? "null":fifth_workout.getText().toString();

                        if(exercise1.equals("null")) {
                            Toast.makeText(SetWorkoutActivity.this, "Please enter at least one workout", Toast.LENGTH_SHORT).show();
                        } else {
                            Advance advance = new Advance(index, day, exercise1, exercise2, exercise3, exercise4, exercise5);
                            dataBaseHandler.insert(db, advance);

                            Handler handler = new Handler();
                            Snackbar.make(v, "Data Added", Snackbar.LENGTH_SHORT).show();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    alert.dismiss();
                                    startActivity(new Intent(SetWorkoutActivity.this, SetWorkoutActivity.class));
                                    finish();
                                }
                            },1500);
                        }
                    }
                });

            }
        });

        DataBaseHandler dataBaseHandler = new DataBaseHandler(getApplicationContext());
        SQLiteDatabase db = dataBaseHandler.getReadableDatabase();
        workouts = dataBaseHandler.getall(db);
//        for (Advance e : workouts) {
//            Log.d("days", "onClick: " + e.getDay());
//        }

        advance_workouts = findViewById(R.id.advance_workouts);
        advncWorkoutAdapter = new AdvncWorkoutAdapter(workouts, SetWorkoutActivity.this);
        advance_workouts.setHasFixedSize(true);
        advance_workouts.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        advance_workouts.setAdapter(advncWorkoutAdapter);
        advncWorkoutAdapter.notifyDataSetChanged();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(advance_workouts);

    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            if(direction == ItemTouchHelper.LEFT) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SetWorkoutActivity.this);
                builder.setTitle("DELETE")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataBaseHandler dataBaseHandler = new DataBaseHandler(SetWorkoutActivity.this);
                                SQLiteDatabase db = dataBaseHandler.getWritableDatabase();
                                dataBaseHandler.delete(db, workouts.get(position));
                                startActivity(new Intent(SetWorkoutActivity.this, SetWorkoutActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    };
}
