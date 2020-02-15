package ba.unsa.etf.rpr;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GradeController implements Initializable {
    @FXML
    private TextField studentField;

    @FXML
    private TextField gradeField;

    @FXML
    private ChoiceBox<String> subjectField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button okBtn;

    private CollegeDAO dao = CollegeDAO.getInstance();
    private ObservableList<String> subjects;
    private boolean gradeValid = false;
    private boolean studentValid = false;
    private boolean subjectValid = false;
    private boolean dateValid = false;
    private int subjectSemester = 0;
    private int yearOfSubject = 0;
    private int yearOfStudent = 0;
    private Student student = null;
    public GradeController () {}

    public void set (ArrayList<String> list) {
        subjects = FXCollections.observableArrayList(list);
        subjectField.setItems(subjects);
    }

    private boolean isStudentValid (int id) {
        Student student = dao.getStudentWithId(id);
        if (student != null) return true;
        return false;
    }

    private boolean isEmptyValidation (String string) {
        if (string.equals(""))
            return true;
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        okBtn.getStyleClass().add("addBtn");
        gradeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isEmptyValidation(newValue) || Integer.valueOf(newValue) > 10 || Integer.valueOf(newValue) < 6) {
                    gradeField.getStyleClass().removeAll("valid");
                    gradeField.getStyleClass().add("notValid");
                    gradeValid = false;
                } else {
                    gradeField.getStyleClass().removeAll("notValid");
                    gradeField.getStyleClass().add("valid");
                    gradeValid = true;
                }
            }
        });

        studentField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isEmptyValidation(newValue)) {
                    studentField.getStyleClass().removeAll("valid");
                    studentField.getStyleClass().add("notValid");
                    studentValid = false;
                } else {
                    studentField.getStyleClass().removeAll("notValid");
                    studentField.getStyleClass().add("valid");
                    studentValid = true;
                }
            }
        });

        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue.isAfter(LocalDate.now())) {
                    datePicker.getStyleClass().removeAll("valid");
                    datePicker.getStyleClass().add("notValid");
                    dateValid = false;
                } else {
                    datePicker.getStyleClass().removeAll("notValid");
                    datePicker.getStyleClass().add("valid");
                    dateValid = true;
                }
            }
        });

        subjectField.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null) {
                    subjectField.getStyleClass().removeAll("valid");
                    subjectField.getStyleClass().add("notValid");
                    subjectValid = false;
                } else {
                    subjectField.getStyleClass().removeAll("notValid");
                    subjectField.getStyleClass().add("valid");
                    subjectValid = true;
                }
            }
        });

    }

    @FXML
    public void addGrade (ActionEvent actionEvent) {
        if (!dao.doesStudentExists(Integer.parseInt(studentField.getText()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adding a grade");
            alert.setHeaderText("Student does not exist!");
            alert.setContentText("Grade is not added.");
            alert.show();
            return;
        }
        subjectSemester = dao.getSubjectSemester(subjectField.getSelectionModel().getSelectedItem());
        student = dao.getStudentWithId(Integer.parseInt(studentField.getText()));

        if (subjectSemester == 1 || subjectSemester == 2) {
            yearOfSubject = 1;
        } else if (subjectSemester == 3 || subjectSemester == 4) {
            yearOfSubject = 2;
        } else if (subjectSemester == 5 || subjectSemester == 6) {
            yearOfSubject = 3;
        } else if (subjectSemester == 7 || subjectSemester == 8) {
            yearOfSubject = 4;
        } else yearOfSubject = 5;

        if (student.getStudyLevel() == 2 && student.getStudyLevel() == 1) {
            yearOfStudent = 4;
        } else if (student.getStudyLevel() == 2 && student.getStudyLevel() == 2) {
            yearOfStudent = 5;
        } else yearOfStudent = student.getStudyYear();

         if (yearOfStudent != yearOfSubject) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adding a grade");
            alert.setHeaderText("Student is not on this subject!");
            alert.setContentText("Grade is not added.");
            alert.show();
        } else {
            if (studentValid && subjectValid && dateValid && gradeValid) {
                int subject = dao.getSubjectIdWithName(subjectField.getValue());
                if (dao.doesGradeExists(Integer.valueOf(studentField.getText()), subject) > 0) {
                    dao.deleteGrade(dao.doesGradeExists(Integer.valueOf(studentField.getText()), subject));
                }
                Grade grade = new Grade(1, Integer.valueOf(gradeField.getText()), datePicker.getValue(), Integer.valueOf(studentField.getText()), subject);
                dao.addGrade(grade);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Adding a grade");
                alert.setHeaderText("");
                alert.setContentText("Grade is added.");
                alert.show();
                ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Adding a grade");
                alert.setHeaderText("Some fields are incorrect!");
                alert.setContentText("Grade is not added.");
                alert.show();
            }
        }
    }
}
