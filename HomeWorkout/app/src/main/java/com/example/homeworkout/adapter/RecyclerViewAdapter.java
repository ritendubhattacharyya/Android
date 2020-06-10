package com.example.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkout.data_handler.workout_data;
import com.example.homeworkout.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<workout_data> list_of_data;

    public RecyclerViewAdapter(Context context, List<workout_data> list_of_data) {
        this.context = context;
        this.list_of_data = list_of_data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView workout_image;
        private TextView workout_name;
        private TextView workout_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            workout_image = itemView.findViewById(R.id.workout_image);
            workout_name = itemView.findViewById(R.id.workout_name);
            workout_desc = itemView.findViewById(R.id.workout_desc);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_workout_row,
                parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        workout_data workout_data = list_of_data.get(position);
        holder.workout_image.setImageResource(workout_data.getWorkout_image());
        holder.workout_name.setText(workout_data.getWorkout_name());
        holder.workout_desc.setText(workout_data.getWorkout_desc());

    }

    @Override
    public int getItemCount() {
        return list_of_data.size();
    }
}
