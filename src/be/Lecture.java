package be;


import java.time.LocalDate;

public class Lecture {
    private int id;
    String name;
    private LocalDate date;
    /**
     * Because I don't have access to a database, I used this class as well as
     * the Attendance class to store the data pertaining to the student's schedule
     * */
    public Lecture(int id,String name,LocalDate localDate) {
        this.id=id;
        this.name=name;
        this.date=localDate;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
