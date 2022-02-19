package be;


import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Course {
    private int id;
    private String name;
    private HashMap<Integer,ArrayList<String>>allLectures;
    /**
     * Because I don't have access to a database, I used this class as well as
     * the Attendance class to store the data pertaining to the student's schedule
     * */
    public Course(int id, String name) {
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, ArrayList<String>> getAllLectures() {
        return allLectures;
    }

    public void setAllLectures(HashMap<Integer, ArrayList<String>> allLectures) {
        this.allLectures = allLectures;
    }
}
