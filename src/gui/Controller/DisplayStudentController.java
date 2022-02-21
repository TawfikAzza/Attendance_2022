package gui.Controller;
import be.Attendance;
import be.Lecture;
import be.Student;
import com.jfoenix.controls.JFXButton;
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
    private JFXButton btnNext;
    @FXML
    private JFXButton btnPrevious;
    @FXML
    private HBox hBoxCalendar, hBoxDays;
    @FXML
    private VBox vBoxCalendar;

    @FXML
    private Label lblWeek;
    @FXML
    private DatePicker dateSelector;

    private StudentModel studentModel;
    private Lecture lectureOfTheDay;
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
        student = studentModel.getStudent(1);
        if(dateNow==null) {
            if(dateSelector.getValue()==null) {
                dateNow = LocalDate.now();
            } else {
                dateNow = dateSelector.getValue();
             //   System.out.println("DateNow After selector:"+dateNow);
            }
        }
      //  System.out.println("Date NOw: "+dateNow);
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        int indexDay = 1;
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

        for (Attendance attendance:student.getAttendanceList().values()) {
                Label label = new Label(attendance.getLecture().getName());
              //  System.out.println("DateNow: " + dateNow.with(fieldISO, indexDay).format(DATE_FORMAT));
                Label labelDays = new Label(dateNow.with(fieldISO, indexDay).format(DATE_FORMAT));
                label.setPrefWidth(123);
                label.setPrefHeight(252);
                label.setTextAlignment(TextAlignment.CENTER);
                label.setStyle("-fx-border-color: blue;");
                labelDays.setPrefWidth(123);
                labelDays.setPrefHeight(20);
                labelDays.setTextAlignment(TextAlignment.CENTER);
                labelDays.setStyle("-fx-background-color: lightblue;");
            if (indexDay <= 7
                    && dateNow.get ( IsoFields.WEEK_OF_WEEK_BASED_YEAR ) == attendance.getLecture().getDate().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)) {

                if (dateNow.with(fieldISO, indexDay).equals(attendance.getLecture().getDate())) {
                    if (attendance.isPresence()) {
                        label.setStyle("-fx-background-color: lightgreen");
                    } else {

                        label.setStyle("-fx-background-color: #ffcccb");
                    }
                    label.setText(attendance.getLecture().getName());
                } else {

                    label.setText("No Lecture");
                    label.setStyle("-fx-background-color: #ffcccb");
                }

                hBoxDays.setAlignment(Pos.CENTER);
                hBoxCalendar.setAlignment(Pos.CENTER);
                hBoxDays.getChildren().add(labelDays);
                hBoxCalendar.getChildren().add(label);

                /**
                 *
                 * */
                indexDay++;
                if (attendance.getLecture().getDate().equals(LocalDate.now())) {
                    lectureOfTheDay = attendance.getLecture();
                    btnConfirmAttendance.setText(attendance.getLecture().getName());
                    if (attendance.isPresence()) {
                        btnConfirmAttendance.setStyle("-fx-background-color: lightgreen");
                    }
                }
            }
            }

        int week = dateNow.get ( IsoFields.WEEK_OF_WEEK_BASED_YEAR );
        int weekYear = dateNow.get ( IsoFields.WEEK_BASED_YEAR );
        lblWeek.setText("Week No : "+week);
      /*  String DATE_PATTERN = "dd.MM.yyyy";
        DateTimeFormatter DATEFORMATER = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate localDate = LocalDate.now();*/
       // DayOfWeek firstDayOfWeek = LocalDate.now().getDayOfWeek();
        //WeekFields weekFields = WeekFields.of(firstDayOfWeek,4);
        //WeekFields weekFields =
        //    System.out.println("WeekField: "+weekFields.getFirstDayOfWeek());
       /* System.out.println("first Day Of week: "+firstDayOfWeek);
        LocalDate localDate1 = LocalDate.parse("2022-03-24");
        System.out.println(localDate1.getDayOfWeek()+" "+localDate1.getMonth()+" "+localDate1.getYear());*/
      /*  LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        System.out.println("Now Date: "+now.with(fieldISO, 1)); // 2015-02-09 (Monday)*/

       /* System.out.println(localDate);
        System.out.println(DATEFORMATER.format(localDate));
        System.out.println(localDate.plusDays(2));*/

    }

    @FXML
    void confirmAttendance(ActionEvent event) throws ParseException {
        if(!student.getAttendanceList().get(lectureOfTheDay.getId()).isPresence()) {
            student.getAttendanceList().get(lectureOfTheDay.getId()).setPresence(true);
            btnConfirmAttendance.setStyle("-fx-background-color: lightgreen");
        } else {
            student.getAttendanceList().get(lectureOfTheDay.getId()).setPresence(false);
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
       // System.out.println(dateSelector.getValue().getClass());
       // LocalDate testDate = DateUtil.parse()
       /* LocalDate dateSelected = dateSelector.getValue();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        TemporalField testDate = WeekFields.of(Locale.FRANCE).weekOfWeekBasedYear();
        int week = dateSelected.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int weekOfYear = dateSelected.get(IsoFields.WEEK_BASED_YEAR);
        System.out.println(testDate);
        System.out.println("Now Date: "+dateSelected.with(fieldISO, 1)); // 2015-02-09 (Monday)
        System.out.println("WeekOfWeek: "+week+" WeekBasedYear: "+weekOfYear);*/
        dateNow =dateSelector.getValue();
        initCalendar();

    }
}
