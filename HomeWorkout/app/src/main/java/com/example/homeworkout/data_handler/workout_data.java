package com.example.homeworkout.data_handler;

public class workout_data {
    int workout_image;
    String workout_name;
    String workout_desc;

    public workout_data(int workout_image, String workout_name, String workout_desc) {
        this.workout_image = workout_image;
        this.workout_name = workout_name;
        this.workout_desc = workout_desc;
    }

    public int getWorkout_image() {
        return workout_image;
    }

    public void setWorkout_image(int workout_image) {
        this.workout_image = workout_image;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public String getWorkout_desc() {
        return workout_desc;
    }

    public void setWorkout_desc(String workout_desc) {
        this.workout_desc = workout_desc;
    }
}
