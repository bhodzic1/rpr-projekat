package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SubjectReport implements Initializable {

    public SubjectReport () {

    }
    @FXML
    private Label title;

    @FXML
    private Label number;

    @FXML
    private Label passed;

    @FXML
    private Label percentage;

    @FXML
    private TableView<SubjectReportModel> table;

    @FXML
    private TableColumn<SubjectReportModel, String> nameColumn;

    @FXML
    private TableColumn<SubjectReportModel, String> lastnameColumn;

    @FXML
    private TableColumn<SubjectReportModel, String> indexColumn;

    @FXML
    private TableColumn<Grade, String> gradeColumn;

    private CollegeDAO dao = CollegeDAO.getInstance();
    private int subjectId = 0;
    private ObservableList<Student> students = FXCollections.observableArrayList();

    private int n = 0;
    private int p = 0;


    public void set (String string, int id) {
        title.setText(string);
        subjectId = id;

        ArrayList<SubjectReportModel> models = new ArrayList<>();
        models = dao.getSubjectReportModel(subjectId);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        indexColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        table.setItems(FXCollections.observableArrayList(models));




        n = dao.getNumberOfStudentsOnSubject(subjectId);
        p = dao.getNumberOfPassedStudentsOnSubject(subjectId);



        number.setText(String.valueOf(n));
        passed.setText(String.valueOf(p));
        if (p != 0) {
            percentage.setText(String.valueOf(p * 100 / n) + "%");
        } else percentage.setText("0%");

    }

    public void resetLabels () {
        number.setText("");
        passed.setText("");
        percentage.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
