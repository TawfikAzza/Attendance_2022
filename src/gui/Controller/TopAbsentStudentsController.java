package gui.Controller;

import gui.Model.StudentModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TopAbsentStudentsController implements Initializable {
    @FXML
    private ListView<?> lstView;
    private final StudentModel studentModel = new StudentModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstView.setItems(studentModel.getTopAbsentStudents());
    }
}
