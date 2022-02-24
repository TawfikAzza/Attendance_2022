package gui.Controller;

import com.jfoenix.controls.JFXButton;
import gui.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{
    public JFXButton btnStudent;
    public Label testLabel;

    public JFXButton btnTeacher;
    public VBox vBoxStudent;
    public VBox vBoxTeacher;
    public BorderPane borderPane;
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

        vBoxStudent.prefWidthProperty().bind(borderPane.widthProperty().divide(2));
        vBoxStudent.prefHeightProperty().bind(borderPane.heightProperty().divide(2));

        vBoxTeacher.prefWidthProperty().bind(borderPane.widthProperty().divide(2));
        vBoxTeacher.prefHeightProperty().bind(borderPane.heightProperty().divide(2));


        btnTeacher.prefWidthProperty().bind(vBoxTeacher.widthProperty());
        btnTeacher.prefHeightProperty().bind(vBoxTeacher.heightProperty());

        btnStudent.prefWidthProperty().bind(vBoxStudent.widthProperty());
        btnStudent.prefHeightProperty().bind(vBoxStudent.heightProperty());



    }
}
