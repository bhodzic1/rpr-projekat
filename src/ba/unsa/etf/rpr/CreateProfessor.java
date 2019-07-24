package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateProfessor implements Initializable {

    public CreateProfessor () {}

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button okBtn;


    private CollegeDAO dao = CollegeDAO.getInstance();
    private boolean firstnameValid = false;
    private boolean lastnameValid = false;
    private boolean dateValid = false;
    private boolean usernameValid = false;
    private boolean passwordValid = false;
    private ArrayList<String> usernameList = new ArrayList<>();

    private boolean isNotEmptyValidation (String string) {
        if (string.equals(""))
            return false;
        return true;
    }

    private boolean isDateValid (LocalDate localDate) {
        if (localDate.isAfter(LocalDate.now()))
            return false;
        if (LocalDate.now().getYear() - localDate.getYear() < 17) // It is not possible to going to college before 17th
            return false;
        return true;
    }

    private boolean isPasswordValid (String string) {
        if (string.length() < 8)
            return false;
        return true;
    }

    private boolean isUsernameValid (String username) {
        for (String s : usernameList) {
            if (s.equals(username)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idField.setText(String.valueOf(dao.getMaxIdProfessor()));
        okBtn.getStyleClass().add("addBtn");
        usernameList = dao.getUsernameFromProfessor();

        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isNotEmptyValidation(newValue)) {
                    nameField.getStyleClass().removeAll("notValid");
                    nameField.getStyleClass().add("valid");
                    firstnameValid = true;
                } else {
                    nameField.getStyleClass().removeAll("valid");
                    nameField.getStyleClass().add("notValid");
                    firstnameValid = false;
                }
            }
        });

        lastnameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isNotEmptyValidation(newValue)) {
                    lastnameField.getStyleClass().removeAll("notValid");
                    lastnameField.getStyleClass().add("valid");
                    lastnameValid = true;
                } else {
                    lastnameField.getStyleClass().removeAll("valid");
                    lastnameField.getStyleClass().add("notValid");
                    lastnameValid = false;
                }
            }
        });

        dateField.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MM. yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });

        dateField.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (isDateValid(newValue)) {
                    dateField.getStyleClass().removeAll("notValid");
                    dateField.getStyleClass().add("valid");
                    dateValid = true;
                } else {
                    dateField.getStyleClass().removeAll("valid");
                    dateField.getStyleClass().add("notValid");
                    dateValid = false;
                }
            }
        });

        usernameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isNotEmptyValidation(newValue)) {
                    usernameField.getStyleClass().removeAll("notValid");
                    usernameField.getStyleClass().add("valid");
                    usernameValid = true;
                } else {
                    usernameField.getStyleClass().removeAll("valid");
                    usernameField.getStyleClass().add("notValid");
                    usernameValid = false;
                }
            }
        });


        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isPasswordValid(newValue)) {
                    passwordField.getStyleClass().removeAll("notValid");
                    passwordField.getStyleClass().add("valid");
                    passwordValid = true;
                } else {
                    passwordField.getStyleClass().removeAll("valid");
                    passwordField.getStyleClass().add("notValid");
                    passwordValid = false;
                }
            }
        });

    }

    @FXML
    public void create (ActionEvent actionEvent) {
        if (isUsernameValid(usernameField.getText()) && firstnameValid && lastnameValid && dateValid && passwordValid) {
            Professor professor = new Professor(Integer.valueOf(idField.getText()), nameField.getText(), lastnameField.getText(), dateField.getValue(), usernameField.getText(), passwordField.getText());
        }
    }
}
