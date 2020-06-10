package com.example.homeworkout.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkout.R;
import com.example.homeworkout.activity.WorkoutActivity;
import com.example.homeworkout.data_handler.Advance;

import java.util.ArrayList;
import java.util.List;


public class AdvncWorkoutAdapter extends RecyclerView.Adapter<AdvncWorkoutAdapter.ViewHolder> {
    List<Advance> workout_names;
    Context context;

    public AdvncWorkoutAdapter(List<Advance> workout_names, Context context) {
        this.workout_names = workout_names;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_advnc_workout_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Advance advance = workout_names.get(position);
        holder.day.setText(advance.getDay());
        holder.workout_1.setText(advance.getFirst_workout());
        holder.workout_2.setText(advance.getSecond_workout());
        holder.workout_3.setText(advance.getThird_workout());
        holder.workout_4.setText(advance.getFourth_workout());
        holder.workout_5.setText(advance.getFifth_workout());
    }

    @Override
    public int getItemCount() {
        return workout_names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView day, workout_1, workout_2, workout_3, workout_4, workout_5;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            day = itemView.findViewById(R.id.day);
            workout_1 = itemView.findViewById(R.id.workout_1);
            workout_2 = itemView.findViewById(R.id.workout_2);
            workout_3 = itemView.findViewById(R.id.workout_3);
            workout_4 = itemView.findViewById(R.id.workout_4);
            workout_5 = itemView.findViewById(R.id.workout_5);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Advance advance = workout_names.get(position);
            final ArrayList<String> workouts = new ArrayList<>();
            if(!advance.getFirst_workout().equals("null")) {
                workouts.add(advance.getFirst_workout());
            }
            if(!advance.getSecond_workout().equals("null")) {
                workouts.add(advance.getSecond_workout());
            }
            if(!advance.getThird_workout().equals("null")) {
                workouts.add(advance.getThird_workout());
            }
            if(!advance.getFourth_workout().equals("null")) {
                workouts.add(advance.getFourth_workout());
            }
            if(!advance.getFifth_workout().equals("null")) {
                workouts.add(advance.getFifth_workout());
            }

            View view = LayoutInflater.from(context).inflate(R.layout.alert_nos, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(view);
            final EditText advance_nos = view.findViewById(R.id.advance_nos);
            Button advance_start = view.findViewById(R.id.advance_start);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            advance_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String adv_nos = advance_nos.getText().toString();
                    if(adv_nos.equals("")) {
                        Toast.makeText(context, "please enter the number of sets", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(context, WorkoutActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("NumberOfSets", Integer.parseInt(adv_nos));
                        intent.putStringArrayListExtra("workout_names", workouts);
                        context.startActivity(intent);
                    }
                }
            });


        }
    }
}
