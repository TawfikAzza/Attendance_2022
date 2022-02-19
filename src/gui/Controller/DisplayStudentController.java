package gui.Controller;


import be.Attendance;
import be.Course;
import be.Student;
import gui.Model.StudentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.net.URL;

import java.text.ParseException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;


public class DisplayStudentController implements Initializable {
    @FXML
    private Button btnConfirmAttendance;
    @FXML
    private HBox hBoxCalendar, hBoxDays;
    @FXML
    private VBox vBoxCalendar;
    @FXML
    private Label lblWeek;
    @FXML
    private DatePicker dateSelector;

    private StudentModel studentModel;
    private Course courseOfTheDay;
    private LocalDate dateNow;
    private Student student;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentModel = new StudentModel();
        try {
            initCalendar();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //TODO: Work on adding new student and maybe new lectures for the weeks to come.
    // For that use the Class PersonManager
    private void initCalendar() throws ParseException {
        student = studentModel.getStudent(2);
        if(dateNow==null) {
            if(dateSelector.getValue()==null) {
                dateNow = LocalDate.now();
            } else {
                dateNow = dateSelector.getValue();
            }
        }
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        DateTimeFormatter DATE_FORMAT =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
        vBoxCalendar.setAlignment(Pos.CENTER);
        hBoxCalendar.getChildren().clear();
        hBoxDays.getChildren().clear();
        hBoxDays.setSpacing(5);
        hBoxCalendar.setSpacing(5);
        btnConfirmAttendance.setStyle("-fx-border-color: lightblue");

        /**
         * Filling the calendar with the values of the lectures contained in the attendance
         * HshMap of the student.
         * */
        for (int i =1;i<6;i++){
            Label labelDays = new Label(dateNow.with(fieldISO,i).format(DATE_FORMAT));
            labelDays.setPrefWidth(123);
            labelDays.setPrefHeight(20);
            labelDays.setAlignment(Pos.CENTER);
            labelDays.setStyle("-fx-background-color: lightblue;");
            hBoxDays.getChildren().add(labelDays);
            VBox vBox= new VBox();
            hBoxCalendar.getChildren().add(vBox);
            for (Attendance attendance:student.getAttendanceList().values()) {
                for (Map.Entry<Integer, ArrayList<String>> entry : attendance.getCourse().getAllLectures().entrySet()) {
                    Integer weekDay = entry.getKey();
                    List<String>time=entry.getValue();

                    if (weekDay==i) {
                        Label courseName = new Label(attendance.getCourse().getName());
                        courseName.setPrefWidth(120);
                        courseName.setPrefHeight(37);
                        courseName.setAlignment(Pos.CENTER);
                        if (attendance.isPresence())
                        courseName.setStyle("-fx-background-color: rgba(55,255,0,0.96);");
                        else courseName.setStyle("-fx-background-color: rgba(255,0,59,0.42);");

                        vBox.getChildren().add(courseName);

                        Label courseDuration = new Label(time.get(0)+" - "+time.get(1));
                        courseDuration.setPrefWidth(120);
                        courseDuration.setPrefHeight(20);
                        courseDuration.setAlignment(Pos.CENTER);
                        if (attendance.isPresence())
                            courseName.setStyle("-fx-background-color: rgba(55,255,0,0.96);");
                        else courseName.setStyle("-fx-background-color: rgba(255,0,59,0.42);");
                        vBox.getChildren().add(courseDuration);

                    }
        }}






            /*if(dateNow.with(fieldISO,indexDay).equals(attendance.getLecture().getDate())) {
                if(attendance.isPresence()) {
                    label.setStyle("-fx-background-color: lightgreen");
                }else {
                    label.setStyle("-fx-background-color: #ffcccb");
                }
                label.setText(attendance.getLecture().getName());
            } else {
                label.setText("No Lecture");
                label.setStyle("-fx-background-color: #ffcccb");
            }*/

            hBoxDays.setAlignment(Pos.CENTER);
            hBoxCalendar.setAlignment(Pos.CENTER);

            /*if(attendance.getLecture().getDate().equals(LocalDate.now())) {
                courseOfTheDay = attendance.getLecture();
                btnConfirmAttendance.setText(attendance.getLecture().getName());
                if(attendance.isPresence()) {
                    btnConfirmAttendance.setStyle("-fx-background-color: lightgreen");
                }
            }*/
        }

        int week = dateNow.get ( IsoFields.WEEK_OF_WEEK_BASED_YEAR );
        int weekYear = dateNow.get ( IsoFields.WEEK_BASED_YEAR );
        lblWeek.setText("Week No : "+week);

        /*  String DATE_PATTERN = "dd.MM.yyyy";
        DateTimeFormatter DATE-FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate localDate = LocalDate.now();
        DayOfWeek firstDayOfWeek = LocalDate.now().getDayOfWeek();
        WeekFields weekFields = WeekFields.of(firstDayOfWeek,4);
        WeekFields weekFields =
            System.out.println("WeekField: "+weekFields.getFirstDayOfWeek());
        System.out.println("first Day Of week: "+firstDayOfWeek);
        LocalDate localDate1 = LocalDate.parse("2022-03-24");
        System.out.println(localDate1.getDayOfWeek()+" "+localDate1.getMonth()+" "+localDate1.getYear());
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        System.out.println("Now Date: "+now.with(fieldISO, 1));

        System.out.println(localDate);
        System.out.println(DATE-FORMATTER.format(localDate));
        System.out.println(localDate.plusDays(2));*/

    }

    @FXML
    void confirmAttendance(ActionEvent event) throws ParseException {
        if(!student.getAttendanceList().get(courseOfTheDay.getId()).isPresence()) {
            student.getAttendanceList().get(courseOfTheDay.getId()).setPresence(true);
            btnConfirmAttendance.setStyle("-fx-background-color: lightgreen");
        } else {
            student.getAttendanceList().get(courseOfTheDay.getId()).setPresence(false);
            btnConfirmAttendance.setStyle("-fx-background-color: #ffcccb");
        }
        initCalendar();
    }

    @FXML
    void displayNextWeek(ActionEvent event) throws ParseException {
        dateNow = dateNow.plusDays(7);
        initCalendar();
    }

    @FXML
    void displayPreviousWeek(ActionEvent event) throws ParseException {
        dateNow = dateNow.minusDays(7);
        initCalendar();
    }


    public void handleDateSelected(ActionEvent actionEvent) throws ParseException {
        dateNow =dateSelector.getValue();
        initCalendar();

    }
}
