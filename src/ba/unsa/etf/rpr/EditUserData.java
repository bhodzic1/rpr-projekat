package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class EditUserData implements Initializable {
    public EditUserData () {}

    @FXML
    private TextField username;

    @FXML
    private Button btnOk;

    @FXML
    private PasswordField password;

    private boolean usernameValid = false;
    private boolean passwordValid = false;
    private String user = null;
    private String pass = null;
    private String temp = null;
    private CollegeDAO dao = CollegeDAO.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() >= 5) {
                    username.getStyleClass().removeAll("notValid");
                    username.getStyleClass().add("valid");
                    usernameValid = true;
                } else {
                    username.getStyleClass().removeAll("valid");
                    username.getStyleClass().add("notValid");
                    usernameValid = false;
                }
            }
        });

        password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() >= 8) {
                    password.getStyleClass().removeAll("notValid");
                    password.getStyleClass().add("valid");
                    passwordValid = true;
                } else {
                    password.getStyleClass().removeAll("valid");
                    password.getStyleClass().add("notValid");
                    passwordValid = false;
                }
            }
        });
    }

    @FXML
    public void edit (ActionEvent actionEvent) {
        user = username.getText();
        pass = password.getText();
        if (usernameValid && passwordValid) {
            dao.updateLogin(username.getText(), password.getText(), temp);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Editing a user");
            alert.setHeaderText("");
            alert.setContentText("User is edited.");
            alert.show();
            ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Editing a user");
            alert.setHeaderText("Some fields are incorrect!");
            alert.setContentText("User is not edited.");
            alert.show();
        }
    }

    public void set (String string) {
        username.setText(string);
        this.temp = string;
        //this.password.setText(password);
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}