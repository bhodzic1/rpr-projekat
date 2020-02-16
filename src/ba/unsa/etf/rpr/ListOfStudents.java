package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ListOfStudents implements Initializable {

    @FXML
    private Tab bachelorTab;

    @FXML
    private ListView<Subject> listViewBachelor;

    @FXML
    private TableView<Student> tableViewBachelor;

    @FXML
    private TableColumn<Student, String> nameBachelor;

    @FXML
    private TableColumn<Student, String> lastnameBachelor;

    @FXML
    private TableColumn<Student, String> indexBachelor;

    @FXML
    private Tab masterTab;

    @FXML
    private ListView<Subject> listViewMaster;

    @FXML
    private TableView<Student> tableViewMaster;

    @FXML
    private TableColumn<Student, String> nameMaster;

    @FXML
    private TableColumn<Student, String> lastnameMaster;

    @FXML
    private TableColumn<Student, String> indexMaster;

    @FXML
    private ChoiceBox<String> year;

    @FXML
    private ChoiceBox<String> semester;

    @FXML
    private Button generateBtn;

    @FXML
    private Button subjectReportBtn;

    @FXML
    private Button studentReportBtn;

    @FXML
    private Button deleteBtn;

    private CollegeDAO dao = CollegeDAO.getInstance();
    private Subject selectedSubject;
    private SubjectReport subjectReport;
    private StudentReport studentReport;
    private Student selectedStudent;
    private int semesterValue = 0;
    private int tempSemester = 0;
    private int tempYear = 0;
    private int tempYearMaster = 0;
    private int semesterValueMaster = 0;
    public ObservableList<Subject> list = FXCollections.observableArrayList();
    public ObservableList<Subject> list2 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateBtn.getStyleClass().add("btnBlue");
        deleteBtn.getStyleClass().add("btnRed");

        year.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("1")) {
                    tempYearMaster = 7;
                    tempYear = 1;
                } else if (newValue.equals("2")) {
                    tempYearMaster = 9;
                    tempYear = 3;
                } else {
                    tempYear = 5;
                }
            }
        });

        semester.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("2")) {
                    tempSemester = 1;
                } else {
                    tempSemester = 0;
                }
            }
        });

    }

    public void generateList (ActionEvent actionEvent) {
        semesterValue = tempSemester + tempYear;
        semesterValueMaster = tempYearMaster + tempSemester;

        list = FXCollections.observableArrayList(dao.subjects(semesterValue));
        listViewBachelor.getItems().setAll(list);

        nameBachelor.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameBachelor.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        indexBachelor.setCellValueFactory(new PropertyValueFactory<>("indexNumber"));
        tableViewBachelor.setItems(FXCollections.observableArrayList(dao.students(1, Integer.parseInt(year.getValue()))));


        list2 = FXCollections.observableArrayList(dao.subjects(semesterValueMaster));
        listViewMaster.getItems().setAll(list2);
        nameMaster.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameMaster.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        indexMaster.setCellValueFactory(new PropertyValueFactory<>("indexNumber"));
        tableViewMaster.setItems(FXCollections.observableArrayList(dao.students(2, Integer.parseInt(year.getValue()))));

    }

    @FXML
    public void report (ActionEvent actionEvent) {
        if (bachelorTab.isSelected()) {
            if (listViewBachelor.getSelectionModel().getSelectedItem() != null) {
                selectedSubject = listViewBachelor.getSelectionModel().getSelectedItem();
                Stage myStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/subjectReport.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                subjectReport = loader.getController();
                subjectReport.set(selectedSubject.toString(), selectedSubject.getId());
                myStage.setTitle("Subject report");
                myStage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

                myStage.setResizable(false);
                myStage.show();

                myStage.setOnHiding( event -> {
                    subjectReport.resetLabels();
                } );
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Subject report");
                alert.setHeaderText("Subject is not selected.");
                alert.setContentText("You need to select subject.");
                alert.show();
            }
        } else {
            if (listViewMaster.getSelectionModel().getSelectedItem() != null) {
                selectedSubject = listViewMaster.getSelectionModel().getSelectedItem();
                Stage myStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/subjectReport.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                subjectReport = loader.getController();
                subjectReport.set(selectedSubject.toString(), selectedSubject.getId());
                myStage.setTitle("Subject report");
                myStage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

                myStage.setResizable(false);
                myStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Subject report");
                alert.setHeaderText("Subject is not selected.");
                alert.setContentText("You need to select subject.");
                alert.show();
            }
        }
    }

    @FXML
    public void studentReport (ActionEvent actionEvent) {
        if (bachelorTab.isSelected()) {
            if (tableViewBachelor.getSelectionModel().getSelectedItem() != null) {
                selectedStudent = tableViewBachelor.getSelectionModel().getSelectedItem();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/studentReport.fxml"));


                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                studentReport = loader.getController();
                studentReport.set(selectedStudent.getIndexNumber(), selectedStudent.getName() + " " + selectedStudent.getLastname());
                stage.setTitle("Student report");
                stage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();


            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Student report");
                alert.setHeaderText("Student is not selected.");
                alert.setContentText("You need to select a student.");
                alert.show();
            }
        } else {
            if (tableViewMaster.getSelectionModel().getSelectedItem() != null) {
                selectedStudent = tableViewMaster.getSelectionModel().getSelectedItem();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/studentReport.fxml"));


                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                studentReport = loader.getController();
                studentReport.set(selectedStudent.getIndexNumber(), selectedStudent.getName() + " " + selectedStudent.getLastname());
                stage.setTitle("Student report");
                stage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();


            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Student report");
                alert.setHeaderText("Student is not selected.");
                alert.setContentText("You need to select a student.");
                alert.show();
            }
        }
    }

    @FXML
    public void deleteStudent (ActionEvent actionEvent) {
        if (dao.getUsernameFromActive().equals("admin")) {
            if (bachelorTab.isSelected()) {
                if (tableViewBachelor.getSelectionModel().getSelectedItem() != null) {
                    Student student = tableViewBachelor.getSelectionModel().getSelectedItem();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete confirmation");
                    alert.setHeaderText("Delete student " + student.getName());
                    alert.setContentText("Are you sure you want to delete " + student.getName());

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        dao.deleteStudent(student.getIndexNumber());
                        tableViewBachelor.setItems(FXCollections.observableArrayList(dao.students(1, Integer.parseInt(year.getValue()))));
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Deleting student.");
                    alert.setHeaderText("Student is not selected.");
                    alert.setContentText("You need to select student.");
                    alert.show();
                }
            } else if (masterTab.isSelected()) {
                if (tableViewMaster.getSelectionModel().getSelectedItem() != null) {
                    Student student = tableViewMaster.getSelectionModel().getSelectedItem();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete confirmation");
                    alert.setHeaderText("Delete student " + student.getName());
                    alert.setContentText("Are you sure you want to delete " + student.getName() + "?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        dao.deleteStudent(student.getIndexNumber());
                        tableViewMaster.setItems(FXCollections.observableArrayList(dao.students(2, Integer.parseInt(year.getValue()))));
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Deleting student.");
                    alert.setHeaderText("Student is not selected.");
                    alert.setContentText("You need to select student.");
                    alert.show();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not possible!");
            alert.setHeaderText("You are not admin!");
            alert.setContentText("You need to be logged as admin!");
            alert.show();
        }



    }

    @FXML
    public void print (ActionEvent actionEvent) {
        File file = new File("students.txt");
        ArrayList<Student> models = new ArrayList<>();
        models = dao.getAllStudents();
        if (file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                String string = "";
                string += "Name of student, index number, enrolment day\n";
                for (Student k : models) {
                    string += k.getName() + " " + k.getLastname() + ", " + k.getIndexNumber() + ", " + k.getEnrolmentDate() + "\n";
                }
                fileWriter.write(string);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }


}
