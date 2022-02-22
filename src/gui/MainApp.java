package gui;

import gui.Controller.MainWindowController;
import gui.Controller.RootLayoutController;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    private AnchorPane displayStudent;
    private AnchorPane displayTeacher;
    private BorderPane rootLayout;
    private StringProperty layoutChosen;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        layoutChosen = new SimpleStringProperty("");
        initLogin();
    }
    public void initLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("View/MainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        MainWindowController controller = loader.getController();
        controller.setMainApp(this);
        primaryStage.show();
    }
    public void initRootLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("View/RootLayout.fxml"));
        rootLayout =  loader.load();
        if(layoutChosen.get().equals("student")) {
            FXMLLoader loaderStudent = new FXMLLoader();
            loaderStudent.setLocation(getClass().getResource("View/StudentDisplay.fxml"));
            displayStudent =  loaderStudent.load();
            rootLayout.setCenter(displayStudent);
            primaryStage.setTitle("Student window");
        }
        if(layoutChosen.get().equals("teacher")) {
            FXMLLoader loaderTeacher = new FXMLLoader();
            loaderTeacher.setLocation(getClass().getResource("View/TeacherDisplay.fxml"));
            displayTeacher = loaderTeacher.load();
            rootLayout.setCenter(displayTeacher);
            primaryStage.setTitle("Teacher window");
        }
        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout,790,640);
        primaryStage.setScene(scene);

        // Give the controller access to the main app.
        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);
        primaryStage.show();
    }
    public static void main(String[] args) {
            Application.launch();
        }

    public String getLayoutChosen() {
        return layoutChosen.get();
    }

    public StringProperty layoutChosenProperty() {
        return layoutChosen;
    }

    public void setLayoutChosen(String layoutChosen) {
        this.layoutChosen.set(layoutChosen);
    }
}
