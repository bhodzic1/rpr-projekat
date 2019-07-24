package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CreateSubject {

    public CreateSubject () {}

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<?> semesterBox;

    @FXML
    private ChoiceBox<?> professorBox;

    @FXML
    private Button okBtn;
}
