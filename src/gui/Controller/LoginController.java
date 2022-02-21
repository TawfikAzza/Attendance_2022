package gui.Controller;

import com.jfoenix.controls.JFXButton;
import gui.MainApp;
import javafx.beans.binding.NumberBinding;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    public AnchorPane anchorPane;
    public VBox vBox;
    public JFXButton btnStudent;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp=mainApp;
    }
    public void openDisplayStudent(ActionEvent actionEvent) throws Exception {
        mainApp.setLayoutChosen("student");
        mainApp.initRootLayout();
    }

    public void openDisplayTeacher(ActionEvent actionEvent) throws Exception {
        mainApp.setLayoutChosen("teacher");
        mainApp.initRootLayout();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox.getStyleClass().add("vbox");
    }
}
