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


import java.io.IOException;
import java.net.URL;
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
    private ListView<?> listViewMaster;

    @FXML
    private TableView<?> tableViewMaster;

    @FXML
    private TableColumn<?, ?> nameMaster;

    @FXML
    private TableColumn<?, ?> lastnameMaster;

    @FXML
    private TableColumn<?, ?> indexMaster;

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
    private int semesterValue = 0;
    private int tempSemester = 0;
    private int tempYear = 0;
    public ObservableList<Subject> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        year.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("1")) {
                    tempYear = 1;
                } else if (newValue.equals("2")) {
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
        list = FXCollections.observableArrayList(dao.subjects(semesterValue));
        listViewBachelor.getItems().setAll(list);
        StudentsModel model = new StudentsModel();
        nameBachelor.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameBachelor.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        indexBachelor.setCellValueFactory(new PropertyValueFactory<>("indexNumber"));
        tableViewBachelor.setItems(FXCollections.observableArrayList(dao.students(1, tempYear)));
    }

    /*@FXML
    public void report (ActionEvent actionEvent) {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            selectedSubject = listView.getSelectionModel().getSelectedItem();
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SubjectReport.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            subjectReport = loader.getController();
            subjectReport.setTextForTitle(selectedSubject);
            myStage.setTitle("Student registration");
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

    @FXML
    public void studentReport (ActionEvent actionEvent) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            selectedStudent = table.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/studentReport.fxml"));
            StudentReport studentReport = new StudentReport(selectedStudent);
            loader.setController(studentReport);
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Student report");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student report");
            alert.setHeaderText("Student is not selected.");
            alert.setContentText("You need to select a student.");
            alert.show();
        }
    }*/

}
