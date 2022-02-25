package gui.Controller;

import com.jfoenix.controls.JFXButton;
import gui.MainApp;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{
    @FXML
    private JFXButton btnStudent;
    @FXML
    private JFXButton btnTeacher;
    @FXML
    private VBox vBoxStudent;
    @FXML
    private VBox vBoxTeacher;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label teacherLabel;
    @FXML
    private Label studentLabel;
    @FXML
    private Label mainLabel;
    private MainApp mainApp;
    private final DoubleProperty fontSizeBigLabel = new SimpleDoubleProperty(10);
    private final DoubleProperty fontSizeSmallLabel = new SimpleDoubleProperty(10);



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

        vBoxStudent.prefWidthProperty().bind(borderPane.widthProperty().divide(10).multiply(4));
        vBoxStudent.prefHeightProperty().bind(borderPane.heightProperty().divide(2));


        mainLabel.prefWidthProperty().bind(borderPane.widthProperty());
        mainLabel.prefHeightProperty().bind(borderPane.heightProperty().divide(4));

        fontSizeBigLabel.bind(borderPane.widthProperty().add(borderPane.heightProperty()).divide(30));
        fontSizeSmallLabel.bind(borderPane.widthProperty().add(borderPane.heightProperty()).divide(100));

        mainLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSizeBigLabel.asString()));
        studentLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSizeBigLabel.asString()));
        teacherLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSizeBigLabel.asString()));

        studentLabel.prefWidthProperty().bind(vBoxStudent.widthProperty());
        studentLabel.prefHeightProperty().bind(vBoxStudent.heightProperty().divide(4));

        teacherLabel.prefWidthProperty().bind(vBoxTeacher.widthProperty());
        teacherLabel.prefHeightProperty().bind(vBoxTeacher.heightProperty().divide(4));

        vBoxTeacher.prefWidthProperty().bind(borderPane.widthProperty().divide(10).multiply(4));
        vBoxTeacher.prefHeightProperty().bind(borderPane.heightProperty().divide(2));

        btnTeacher.prefWidthProperty().bind(vBoxTeacher.widthProperty());
        btnTeacher.prefHeightProperty().bind(vBoxTeacher.heightProperty());

        btnStudent.prefWidthProperty().bind(vBoxStudent.widthProperty());
        btnStudent.prefHeightProperty().bind(vBoxStudent.heightProperty());
    }
}
