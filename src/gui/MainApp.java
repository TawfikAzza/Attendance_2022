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
        Scene scene = new Scene(root,301,210);

        primaryStage.setScene(scene);

        primaryStage.minWidthProperty().bind(scene.heightProperty().multiply(2));
        primaryStage.minHeightProperty().bind(scene.widthProperty().divide(2));

        MainWindowController controller = loader.getController();
        controller.setMainApp(this);
        primaryStage.setTitle("Main window");
        primaryStage.show();
    }
    public void initRootLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("View/RootLayout.fxml"));
        BorderPane rootLayout = loader.load();
        primaryStage.minWidthProperty().unbind();
        primaryStage.minHeightProperty().unbind();
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(790);
        if(layoutChosen.get().equals("student")) {
            FXMLLoader loaderStudent = new FXMLLoader();
            loaderStudent.setLocation(getClass().getResource("View/StudentDisplay.fxml"));
            AnchorPane displayStudent = loaderStudent.load();
            rootLayout.setCenter(displayStudent);
            primaryStage.setTitle("Student window");
            primaryStage.resizableProperty().set(false);

        }
        if(layoutChosen.get().equals("teacher")) {
            FXMLLoader loaderTeacher = new FXMLLoader();
            loaderTeacher.setLocation(getClass().getResource("View/TeacherDisplay.fxml"));
            AnchorPane displayTeacher = loaderTeacher.load();
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

    public void setLayoutChosen(String layoutChosen) {
        this.layoutChosen.set(layoutChosen);
    }
}
