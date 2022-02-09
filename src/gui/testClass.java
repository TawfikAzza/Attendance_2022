package gui;
import be.Attendance;
import be.Lecture;
import be.Student;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class testClass extends Application{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        System.out.println("first day is : "+now.with(fieldISO, 2)); // 2015-02-09 (Monday)
        List<Lecture> lectureList = new ArrayList<>();
        // filling the dates of the lecture with the current dates of the week (for the test)
        Lecture lecture1 = new Lecture(1,"SCO2", now.with(fieldISO, 1));
        Lecture lecture2 = new Lecture(2,"SDE2", now.with(fieldISO, 2));
        Lecture lecture3 = new Lecture(3,"ITO2", now.with(fieldISO, 3));
        Lecture lecture4 = new Lecture(4,"DBOS2", now.with(fieldISO, 4));
        Lecture lecture5 = new Lecture(5,"SCO2/SDE2", now.with(fieldISO, 5));

        lectureList.add(lecture1);
        lectureList.add(lecture2);
        lectureList.add(lecture3);
        lectureList.add(lecture4);
        lectureList.add(lecture5);

        Student student1 = new Student(1,"Tawfik Azza","tawf0021@easv.dk","test");
        student1.validateParticipation(lecture1);
        student1.validateParticipation(lecture2);
        student1.validateParticipation(lecture3);
        student1.validateParticipation(lecture4);
        student1.validateParticipation(lecture5);

        student1.removeParticipation(lecture4);
        student1.removeParticipation(lecture1);

        for (Attendance attendance: student1.getAttendanceList().values()) {
            if(attendance.isPresence()) {
                System.out.println("ATTENDED LECTURES: ");
                System.out.println("id: "+attendance.getLecture().getId()
                        +" Name: "+attendance.getLecture().getName()
                        +" Date: "+attendance.getLecture().getDate());
            }
            if(!attendance.isPresence()) {
                System.out.println();
                System.out.println("-------------NOT ATTENDED LECTURES: ");
                System.out.println("id: "+attendance.getLecture().getId()
                        +" Name: "+attendance.getLecture().getName()
                        +" Date: "+attendance.getLecture().getDate());
            }
        }

        System.out.println("Student: "+student1.getName()+" total Attendance : "+student1.getAttendance());


    }


}
