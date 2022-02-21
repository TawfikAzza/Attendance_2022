package gui.Controller;

import be.Attendance;
import be.Student;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;

public class PersonalisedChartController implements Initializable {
    public AreaChart<?,?> persoChart;
    Student student;
    DisplayTeacherController displayTeacherController;


    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void drawChart() {
        XYChart.Series series= new XYChart.Series();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        for (int i = 1 ;i<7;i++){
            double attendanceCounter=0;
            for (Attendance attendance : this.student.getAttendanceList().values()){
                if (attendance.getLecture().getDate().equals(LocalDate.now().with(fieldISO,i))){
                    if (attendance.isPresence())
                        attendanceCounter+=1;
                }
            }
            series.getData().add(new XYChart.Data(String.valueOf(LocalDate.now().with(fieldISO, i).getDayOfWeek()),(attendanceCounter)));
        }
        persoChart.getData().add(series);
        }

    public void setMainController(DisplayTeacherController displayTeacherController) {
        this.displayTeacherController=displayTeacherController;
    }
}
