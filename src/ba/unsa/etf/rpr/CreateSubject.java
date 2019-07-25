package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateSubject implements Initializable {

    public CreateSubject () {}

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<?> semesterBox;

    @FXML
    private ChoiceBox<String> professorBox;

    @FXML
    private Button okBtn;

    private CollegeDAO dao = CollegeDAO.getInstance();

    public void set () {
        professorBox.getItems().setAll(FXCollections.observableArrayList(dao.getNamesProfessor()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
