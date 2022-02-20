package gui.Controller;

import be.Attendance;
import be.Student;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;

import java.net.URL;
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
        for(Attendance attendance: this.student.getAttendanceList().values()){
            System.out.println(attendance.getLecture().getDate().get(WeekFields.of(Locale.FRANCE).dayOfWeek()));;
        }
        }

    public void setMainController(DisplayTeacherController displayTeacherController) {
        this.displayTeacherController=displayTeacherController;
    }
}
