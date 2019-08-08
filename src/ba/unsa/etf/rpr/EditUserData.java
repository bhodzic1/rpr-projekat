package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
