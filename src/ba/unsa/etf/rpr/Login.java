package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Login implements Initializable{

    private HomeController controller;
    private CollegeDAO dao = CollegeDAO.getInstance();

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button ok;

    public Login() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });

        password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });
    }

    @FXML
    public void clickOk(ActionEvent actionEvent) {
        System.out.println(username.getText());
        if (dao.checkLogin(username.getText(), password.getText())) {
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            controller = loader.getController();
            dao.addActiveUser(username.getText(), password.getText());

            ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();

            myStage.setTitle("Student registration");
            myStage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Logging");
            alert.setHeaderText("Login failed.");
            alert.setContentText("Username or password are not correct.");
            alert.show();
        }
    }


}
