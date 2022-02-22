package gui.Controller;

import be.Attendance;
import be.Student;
import be.Teacher;
import gui.Model.StudentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherDisplayController implements Initializable {
    @FXML
    private AnchorPane topPane;
    @FXML
    private AreaChart<?, ?> attendancePerDay;
    @FXML
    private BarChart<?, ?> attendancePerSubject;
    @FXML
    private AnchorPane anchorImg;
    @FXML
    private ProgressBar attendanceProgress, missedProgress;
    @FXML
    private Label attendanceLabel, missedClassesLabel, nameLabel, educationLabel, emailLabel, statsLabel, label;

    @FXML
    private VBox statsVbox, progressVBox, studentInfosVBox;

    @FXML
    private HBox bottomVBox;

    @FXML
    private AnchorPane anchorLogo;


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

    public TeacherDisplayController() {
        studentModel = new StudentModel();
        teacher = new Teacher(1, "Jeppe Led Moritz", "jml@easv.dk", "JavaIsFun");
        teacher.setStudentList(studentModel.getAllStudent());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addStyleClass();
        updateStudentList();
        setUpLogo();

        studentList.setOnMouseClicked(event -> {
            setStudent(studentList.getSelectionModel().getSelectedItem());
            setUpStudentPicture(getStudent());
            if (getStudent()!=null){
                setUpPersonalisedProgressBars(getStudent());
                nameLabel.setText(student.getName());
                educationLabel.setText(student.getEducation());
                emailLabel.setText("       " + student.getEmail());}

        });
        setUpProgressBars();
        drawBarChart();
        drawLineChart();
    }

    private void setUpPersonalisedProgressBars(Student student) {
        double counterAttendances = 0;
        double counterTotal = 0;
        for (Attendance attendance : student.getAttendanceList().values()) {
            if (attendance.isPresence())
                counterAttendances += 1;
            counterTotal += 1;
        }
        double progress = counterAttendances / counterTotal;
        attendanceProgress.setProgress(progress);
        missedProgress.setProgress(1 - progress);
        attendanceLabel.setText("    " + counterAttendances);
        missedClassesLabel.setText("    " + (counterTotal - counterAttendances));
    }

    private void setUpStudentPicture(Student student) {
        try {
            ImageView img = new ImageView(student.getImageURL());
            img.fitWidthProperty().bind(anchorImg.widthProperty());
            img.fitHeightProperty().bind(anchorImg.heightProperty());
            anchorImg.getChildren().add(img);
        } catch (NullPointerException | IllegalArgumentException exception) {
            anchorImg.getChildren().clear();
            label.setText("No picture available");
            anchorImg.getChildren().add(label);
        }
    }

    private void setUpProgressBars() {
        float counterAttendance = 0;
        float totalCounter = 0;
        for (Student student : studentModel.getAllStudent()) {
            for (Attendance value : student.getAttendanceList().values()) {
                if (value.isPresence())
                    counterAttendance += 1;
                totalCounter += 1;
            }
        }
        double progress = counterAttendance / totalCounter;
        attendanceLabel.setText("    " + (counterAttendance));
        missedClassesLabel.setText("    " + (totalCounter - counterAttendance));
        attendanceProgress.setProgress(progress);
        missedProgress.setProgress(1 - progress);
    }

    private void drawLineChart() {
        XYChart.Series series = new XYChart.Series();
        for (Student student : studentModel.getAllStudent())
            series.getData().add(new XYChart.Data(student.getName(), Double.parseDouble(student.getAttendance())));
        attendancePerSubject.getData().add(series);

    }

    private void drawBarChart() {
        XYChart.Series series = new XYChart.Series();
        double allStudents = studentModel.getAllStudent().size();
        for (int i = 1; i < studentModel.getAllLectures().size(); i++) {
            String lecName = null;
            double attendanceCounter = 0;
            for (Student student : studentModel.getAllStudent()) {
                lecName = student.getAttendanceList().get(i).getLecture().getName();
                if (student.getAttendanceList().get(i).isPresence())
                    attendanceCounter += 1;
            }
            series.getData().add(new XYChart.Data(lecName, (attendanceCounter / allStudents) * 100));
        }
        attendancePerDay.getData().add(series);

    }

    public void updateStudentList() {
        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));

        studentList.getItems().setAll(teacher.getStudentList());
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void newWindow(ActionEvent actionEvent) throws IOException {
        if (getStudent() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("gui/View/PersonalisedChart.fxml"));
            Parent root = loader.load();
            PersonalisedChartController personalisedChartController = loader.getController();
            personalisedChartController.setMainController(this);
            personalisedChartController.setStudent(getStudent());
            personalisedChartController.drawChart();

            Stage stage = new Stage();
            stage.setTitle("Show more");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    private void addStyleClass() {
        anchorImg.getStyleClass().add("anchorPane");
        statsVbox.getStyleClass().add("vbox");
        progressVBox.getStyleClass().add("vbox");
        studentInfosVBox.getStyleClass().add("vbox");
        bottomVBox.getStyleClass().add("vbox");
    }

    private void setUpLogo() {
        ImageView imgLogo = new ImageView("images/EASYDVEST.jpeg");
        imgLogo.fitWidthProperty().bind(anchorLogo.widthProperty());
        imgLogo.fitHeightProperty().bind(anchorLogo.heightProperty());
        anchorLogo.getChildren().add(imgLogo);
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.close();
    }

    public void showTopAbsentStudents(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("gui/View/TopAbsentStudents.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Top absent students");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
