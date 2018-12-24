package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    StudentsModel studentsModel;
    HomeController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        controller = new HomeController();
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("Student Information System");
        primaryStage.setScene(new Scene(root, 600, 400));

        primaryStage.show();
        primaryStage.toFront();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
