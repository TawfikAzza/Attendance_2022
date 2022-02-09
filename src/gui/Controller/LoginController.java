package gui.Controller;

import gui.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button btnStudent;

    @FXML
    private Button btnTeacher;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp=mainApp;
    }
    public void openDisplayStudent(ActionEvent actionEvent) throws Exception {

     /*   FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/DisplayStudent.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage  = new Stage();
        stage.setScene(scene);
        stage.show();*/
       // Stage stage = (Stage)btnStudent.getScene().getWindow();
        mainApp.setLayoutChosen("student");
        mainApp.initRootLayout();
      //  stage.close();
    }

    public void openDisplayTeacher(ActionEvent actionEvent) throws Exception {
        mainApp.setLayoutChosen("teacher");
        mainApp.initRootLayout();
    }


}
