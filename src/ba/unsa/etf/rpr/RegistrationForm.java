package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


import static java.lang.Character.isDigit;

public class RegistrationForm implements Initializable {

    private CollegeDAO dao = CollegeDAO.getInstance();

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
    private DatePicker enrolmentDate;

    @FXML
    private Button addButton;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private ChoiceBox<String> studyLevelBox;

    @FXML
    private ChoiceBox<String> studyYear;

    private boolean firstnameValid;
    private boolean lastnameValid;
    private boolean idValid;
    private boolean idNumberValid;
    private boolean dateValid;
    private boolean addressValid;
    private boolean emailValid;
    private boolean studyLevelValid = true;
    private boolean studyYearValid = true;
    private int studyLevel = 1;


    private boolean isNotEmptyValidation (String string) {
        if (string.equals(""))
            return false;
        return true;
    }

    private boolean isIdValid (String string) {
        if (string.length() == 0) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) {
            if (!isDigit(string.charAt(i)))
                return false;
        }
        if (string.length() > 5)
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

    private boolean isIdNumberValid (String string) {
        if (string.length() == 0) {
            return false;
        }
        if(string.length() != 13) {
            return false;
        }
        LocalDate date = dateField.getValue();
        String dateField = date.toString();
        if (!string.substring(0, 2).equals(dateField.substring(8, 10))) {
            return false;
        }
        if (!string.substring(2, 4).equals(dateField.substring(5, 7))) {
            return false;
        }
        if (!string.substring(4, 7).equals(dateField.substring(1, 4))) {
            return false;
        }

        return true;
    }

    private boolean isEmailValid (String string) {
        if (string.length() == 0) {
            return false;
        }
        if (!string.contains("@") || !string.contains(".")) {
            return false;
        }
        if (string.charAt(0) == '@' || string.charAt(string.length() - 1) == '@') {
            return false;
        }
        return true;
    }

    public void set () {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstnameValid = false;
        lastnameValid = false;
        idValid = true;
        idNumberValid = false;
        dateValid = false;
        addressValid = false;
        emailValid = false;

        idField.setText(String.valueOf(dao.getMaxId()));
        addButton.getStyleClass().add("addBtn");

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

        /*idField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isIdValid(newValue)) {
                    idField.getStyleClass().removeAll("notValid");
                    idField.getStyleClass().add("valid");
                    idValid = true;
                } else {
                    idField.getStyleClass().removeAll("valid");
                    idField.getStyleClass().add("notValid");
                    idValid = false;
                }
            }
        });*/

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

        idNumberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isIdNumberValid(newValue)) {
                    idNumberField.getStyleClass().removeAll("notValid");
                    idNumberField.getStyleClass().add("valid");
                    idNumberValid = true;
                } else {
                    idNumberField.getStyleClass().removeAll("valid");
                    idNumberField.getStyleClass().add("notValid");
                    idNumberValid = false;
                }
            }
        });

        addressField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isNotEmptyValidation(newValue)) {
                    addressField.getStyleClass().removeAll("notValid");
                    addressField.getStyleClass().add("valid");
                    addressValid = true;
                } else {
                    addressField.getStyleClass().removeAll("valid");
                    addressField.getStyleClass().add("notValid");
                    addressValid = false;
                }
            }
        });

        emailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isEmailValid(newValue)) {
                    emailField.getStyleClass().removeAll("notValid");
                    emailField.getStyleClass().add("valid");
                    emailValid = true;
                } else {
                    emailField.getStyleClass().removeAll("valid");
                    emailField.getStyleClass().add("notValid");
                    emailValid = false;
                }
            }
        });

        studyLevelBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Master")) {
                    studyLevel = 2;
                    if (Integer.valueOf(studyYear.getValue()) > 2) {
                        studyLevelBox.getStyleClass().removeAll("valid");
                        studyLevelBox.getStyleClass().add("notValid");
                        studyLevelValid = false;
                    }
                } else {
                    studyLevel = 1;
                    studyLevelBox.getStyleClass().removeAll("notValid");
                    studyLevelBox.getStyleClass().add("valid");
                    studyLevelValid = true;
                }
            }
        });

        studyYear.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("3")) {
                    if (studyLevelBox.getValue().equals("Master")) {
                        studyYearValid = false;
                        studyYear.getStyleClass().removeAll("valid");
                        studyYear.getStyleClass().add("notValid");
                    }
                } else {
                    studyYearValid = true;
                    studyYear.getStyleClass().removeAll("notValid");
                    studyYear.getStyleClass().add("valid");
                }
            }
        });
    }

    @FXML
    public void addStudent (ActionEvent actionEvent) {
        if (firstnameValid && lastnameValid && emailValid && addressValid && dateValid && idValid && idNumberValid && studyYearValid && studyLevelValid) {
            Student student = new Student(nameField.getText(), lastnameField.getText(), Integer.valueOf(idField.getText()), dateField.getValue(), idNumberField.getText(), studyLevel, Integer.valueOf(studyYear.getValue()), addressField.getText(), emailField.getText(), enrolmentDate.getValue());
            //model.addStudent(student, Integer.valueOf(studyYear.getValue()));
            dao.addStudent(student);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adding a student");
            alert.setHeaderText("");
            alert.setContentText("Student successfully added.");
            alert.show();
            ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adding a student");
            alert.setHeaderText("");
            alert.setContentText("Student is not added.");
            alert.show();
        }
    }
}
