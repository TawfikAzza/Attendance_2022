package gui;
import be.Attendance;
import be.Course;
import be.Student;
import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
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
        List<Course> courseList = new ArrayList<>();
        // filling the dates of the lecture with the current dates of the week (for the test)
        Course course1 = new Course(1,"SCO2");
        Course course2 = new Course(2,"SDE2");
        Course course3 = new Course(3,"ITO2");
        Course course4 = new Course(4,"DBOS2");
        Course course5 = new Course(5,"SCO2/SDE2");

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
        courseList.add(course5);

        Student student1 = new Student(1,"Tawfik Azza","tawf0021@easv.dk","test");
        student1.validateParticipation(course1);
        student1.validateParticipation(course2);
        student1.validateParticipation(course3);
        student1.validateParticipation(course4);
        student1.validateParticipation(course5);

        student1.removeParticipation(course4);
        student1.removeParticipation(course1);

        for (Attendance attendance: student1.getAttendanceList().values()) {
            if(attendance.isPresence()) {
                System.out.println("ATTENDED LECTURES: ");
                System.out.println("id: "+attendance.getLecture().getId());
                      //  +" Name: "+attendance.getLecture().getName()
                //   +" Date: "+attendance.getLecture().getDate());
            }
            if(!attendance.isPresence()) {
                System.out.println();
                System.out.println("-------------NOT ATTENDED LECTURES: ");
                System.out.println("id: "+attendance.getLecture().getId());
                  //      +" Name: "+attendance.getLecture().getName()
                   //     +" Date: "+attendance.getLecture().getDate());
            }
        }

        System.out.println("Student: "+student1.getName()+" total Attendance : "+student1.getAttendance());


    }


}
