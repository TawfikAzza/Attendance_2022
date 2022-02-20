package gui.Controller;

import be.Attendance;
import be.Student;
import be.Teacher;
import gui.Model.StudentModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayTeacherController implements Initializable {
    public AreaChart<?,?> attendancePerDay;
    public BarChart<?,?> attendancePerSubject;

    public LineChart<?,?> attendancePerStudent;

    public AnchorPane anchorImg;
    public ProgressBar attendanceProgress;
    public ProgressBar missedProgress;
    public Label attendanceLabel;
    public Label missedClassesLabel;
    public Label nameLabel;
    public Label educationLabel;
    public Label emailLabel;
    public Label statsLabel;

    /***
     * This class will host only one teacher as proof of concept. However, it
     * can be easily changed when the class TeacherModel is implemented.
     * for now, the teacher object which will be used will be "hard-coded" in the constructor
     * the method used to fill the students associated to a teacher will also
     * be "static" in a way as no teacher id will be provided and the teacher will have access to all the students
     * in the Student ArrayList.
     * */

    @FXML
    private TableColumn<Student, String> studentAttendance;
    @FXML
    private TableView<Student> studentList;
    @FXML
    private TableColumn<Student, String> studentName;
    private Student student;


    private final Teacher teacher;
    private final StudentModel studentModel;
    public DisplayTeacherController(){
        studentModel = new StudentModel();
        teacher = new Teacher(1,"Jeppe Led Moritz","jml@easv.dk","JavaIsFun");
        teacher.setStudentList(studentModel.getAllStudent());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateStudentList();
        studentList.setOnMouseClicked(event -> {
            setStudent( studentList.getSelectionModel().getSelectedItem());
            try {
                ImageView img = new ImageView(studentList.getSelectionModel().getSelectedItem().getImageURL());
                img.fitWidthProperty().bind(anchorImg.widthProperty());
                img.fitHeightProperty().bind(anchorImg.heightProperty());
                anchorImg.getChildren().add(img);
            }catch (NullPointerException npe){};
            double counterAttendances=0;
            double counterTotal=0;
            for (Attendance attendance : studentList.getSelectionModel().getSelectedItem().getAttendanceList().values()){
                if (attendance.isPresence())
                    counterAttendances+=1;
                counterTotal+=1;}
                 double progress = counterAttendances/counterTotal;
                attendanceProgress.setProgress(progress);
                missedProgress.setProgress(1-progress);
                attendanceLabel.setText("    "+counterAttendances);
                missedClassesLabel.setText("    "+(counterTotal-counterAttendances));
                nameLabel.setText(student.getName());
                educationLabel.setText(student.getEducation());
                emailLabel.setText(student.getEmail());
                statsLabel.setText("Personalised stats");

        });
        setUpProgressBars();
        drawAreaChart();
        drawBarChart();
        drawLineChart();
    }

    private void setUpProgressBars() {
        float counterAttendance = 0;
        float totalCounter = 0;
        for (Student student:studentModel.getAllStudent()){
            for (Attendance value : student.getAttendanceList().values()){
                if (value.isPresence())
                    counterAttendance+=1;
                totalCounter+=1;
            }
        }
         double progress=counterAttendance/totalCounter;
        attendanceLabel.setText("    "+ (counterAttendance));
        missedClassesLabel.setText("    "+ (totalCounter - counterAttendance));
        attendanceProgress.setProgress(progress );
        missedProgress.setProgress(1-progress);
    }

    private void drawLineChart() {
        XYChart.Series series= new XYChart.Series();
        for (Student student: studentModel.getAllStudent())
            series.getData().add(new XYChart.Data(student.getName(),Double.parseDouble(student.getAttendance())));
        attendancePerSubject.getData().add(series);

    }

    private void drawBarChart() {
        XYChart.Series series= new XYChart.Series();
        double allStudents = studentModel.getAllStudent().size();
        for (int i =1; i<studentModel.getAllLectures().size();i++){
            String lecName = null;
        double attendanceCounter = 0;
        for (Student student: studentModel.getAllStudent()){
             lecName= student.getAttendanceList().get(i).getLecture().getName();
            if (student.getAttendanceList().get(i).isPresence())
                attendanceCounter+=1;
        }
            series.getData().add(new XYChart.Data(lecName,(attendanceCounter/allStudents)*100));
        }
        attendancePerDay.getData().add(series);

    }

    private void drawAreaChart() {
    }

    public void updateStudentList() {
        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));

        studentList.getItems().setAll(teacher.getStudentList());
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
