package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ListOfStudents implements Initializable {



    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> lastnameColumn;

    @FXML
    private TableColumn<Student, String> indexColumn;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button reportBtn;

    private SubjectReport subjectReport;
    private String selectedSubject = null;
    private ObservableList<String> list = FXCollections.observableArrayList();
    private int level;
    public ObservableList<Student> k = FXCollections.observableArrayList();
    public StudentsModel model;
    public ListOfStudents () {}

    public void setList (ObservableList<String> l, int level, StudentsModel model) {
        this.list = l;
        listView.getItems().addAll(list);
        this.level = level;
        this.model = model;
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        indexColumn.setCellValueFactory(new PropertyValueFactory<>("indexNumber"));
        if (level == 1) {
            k = model.getStudentsOfFirstYear();
            table.setItems(model.getStudentsOfFirstYear());
        } else if (level == 2) {
            k = model.getStudentsOfSecondYear();
            table.setItems(model.getStudentsOfSecondYear());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setOrientation(Orientation.VERTICAL);
    }

    @FXML
    public void report (ActionEvent actionEvent) {
        if (listView.getSelectionModel() != null) {
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
        }
    }

}
