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
    private int subjectId;
    private ObservableList<Student> students = FXCollections.observableArrayList();

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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
