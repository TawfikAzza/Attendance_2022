package gui.Controller;

import be.Student;
import be.Teacher;
import bll.PersonManager;
import gui.Model.StudentModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayTeacherController implements Initializable {
    /***
     * This class will host only one teacher as proof of concept, however, it
     * can be easily changed if if/when the class TeacherModel is implemented.
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


    private Teacher teacher;
    private StudentModel studentModel;
    public DisplayTeacherController(){
        studentModel = new StudentModel();
        teacher = new Teacher(1,"Jeppe Led Moritz","jml@easv.dk","JavaIsFun");
        teacher.setStudentList(studentModel.getAllStudent());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateStudentList();
    }
    public void updateStudentList() {
        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));

        studentList.getItems().setAll(teacher.getStudentList());
    }
}
