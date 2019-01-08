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
    StudentsModel model;
    HomeController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*String url = "jdbc:sqlite:resources/sql/base.db";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String upit = "Insert into Student values (Bernes, Hodzic, 17052);";

        ResultSet result = stmt.executeQuery(upit);*/
        model = new StudentsModel();
        model.set();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        controller = new HomeController(model);
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
