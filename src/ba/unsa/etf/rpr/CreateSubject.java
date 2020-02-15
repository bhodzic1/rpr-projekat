package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateSubject implements Initializable {

    public CreateSubject () {}

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<String> semesterBox;

    @FXML
    private ChoiceBox<String> professorBox;

    @FXML
    private Button okBtn;

    private CollegeDAO dao = CollegeDAO.getInstance();
    private boolean nameValid = false;
    private boolean professorValid = false;
    private boolean semesterValid = false;
    private int id = 0;

    private boolean isNotEmptyValidation (String string) {
        if (string.equals(""))
            return false;
        return true;
    }

    public void set () {
        professorBox.getItems().setAll(FXCollections.observableArrayList(dao.getNamesProfessor()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        okBtn.getStyleClass().add("addBtn");
        idField.setText(String.valueOf(dao.getMaxIdSubject()));

        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isNotEmptyValidation(newValue)) {
                    nameField.getStyleClass().removeAll("notValid");
                    nameField.getStyleClass().add("valid");
                    nameValid = true;
                } else {
                    nameField.getStyleClass().removeAll("valid");
                    nameField.getStyleClass().add("notValid");
                    nameValid = false;
                }
            }
        });

        professorBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    id = dao.getIdProfessorFromNameAndLastname(newValue);
                    professorBox.getStyleClass().removeAll("notValid");
                    professorBox.getStyleClass().add("valid");
                    professorValid = true;
                } else {
                    professorBox.getStyleClass().removeAll("valid");
                    professorBox.getStyleClass().add("notValid");
                    professorValid = false;
                }
            }
        });

        semesterBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    semesterBox.getStyleClass().removeAll("notValid");
                    semesterBox.getStyleClass().add("valid");
                    semesterValid = true;
                } else {
                    semesterBox.getStyleClass().removeAll("valid");
                    semesterBox.getStyleClass().add("notValid");
                    semesterValid = false;
                }
            }
        });

    }

    @FXML
    public void create (ActionEvent actionEvent) {
        if (nameValid && professorValid && semesterValid) {
            Subject subject = new Subject(Integer.valueOf(idField.getText()), nameField.getText(), id,Integer.valueOf(semesterBox.getValue()));
            dao.addSubject(subject);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adding a subject");
            alert.setHeaderText("");
            alert.setContentText("Subject successfully added.");
            alert.show();
            ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adding a subject");
            alert.setHeaderText("Some fields are incorrect!");
            alert.setContentText("Subject is not added.");
            alert.show();
        }
    }
}
