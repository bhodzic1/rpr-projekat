package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentReport implements Initializable {

    @FXML
    private TableView<StudentsModel> table;

    @FXML
    private TableColumn<StudentsModel, String> nameColumn;

    @FXML
    private TableColumn<StudentsModel, String> gradeColumn;

    @FXML
    private TableColumn<StudentsModel, String> dateColumn;

    @FXML
    private TableColumn<StudentsModel, String> professorColumn;

    @FXML
    private Label nameOfStudent;

    private int studentId;
    private CollegeDAO dao = CollegeDAO.getInstance();

    public StudentReport () {

    }

    public void set (int id, String name) {
        studentId = id;

        ArrayList<StudentsModel> models = new ArrayList<>();
        models = dao.getStudentModel(studentId);

        nameOfStudent.setText(name);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        professorColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(FXCollections.observableArrayList(models));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
