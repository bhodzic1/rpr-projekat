package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {
    Login controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        controller = new Login();
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 150));

        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.toFront();
    }


    public static void main(String[] args) {
        CollegeDAO dao = CollegeDAO.getInstance();
        dao.ispisi();
        launch(args);

    }
}
