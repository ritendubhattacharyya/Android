package com.example.homeworkout.data_handler;

public class Advance {
    private int index;
    private String day;
    private String first_workout;
    private String second_workout;
    private String third_workout;
    private String fourth_workout;
    private String fifth_workout;


    public Advance(int index, String day, String first_workout, String second_workout, String third_workout, String fouth_workout, String fifth_workout) {
        this.index = index;
        this.day = day;
        this.first_workout = first_workout;
        this.second_workout = second_workout;
        this.third_workout = third_workout;
        this.fourth_workout = fouth_workout;
        this.fifth_workout = fifth_workout;
    }

    public Advance() {}


    public String getDay() {
        return day;
    }

    public String getFirst_workout() {
        return first_workout;
    }

    public String getSecond_workout() {
        return second_workout;
    }

    public String getThird_workout() {
        return third_workout;
    }

    public String getFourth_workout() {
        return fourth_workout;
    }

    public String getFifth_workout() {
        return fifth_workout;
    }

    public int getIndex() {
        return index;
    }
}
