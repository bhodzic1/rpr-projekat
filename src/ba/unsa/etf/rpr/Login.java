package ba.unsa.etf.rpr;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;


import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Login {
    StudentsModel model;
    HomeController controller;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button ok;

    @FXML
    public void clickOk(ActionEvent actionEvent) {
        model = new StudentsModel();
        model.set();

        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller = loader.getController();
        controller.setModel(model);

        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();

        myStage.setTitle("Student registration");
        myStage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();

    }

    public Login() {}
}
