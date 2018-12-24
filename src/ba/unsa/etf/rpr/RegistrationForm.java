package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationForm implements Initializable {

  public RegistrationForm () {};

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField idNumberField;

    @FXML
    private DatePicker dateField;

    @FXML
    private Button addButton;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private ChoiceBox<String> studyLevelBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
