package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

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

    private ObservableList<String> list = FXCollections.observableArrayList();
    private int level;
    public ObservableList<Student> k = FXCollections.observableArrayList();
    public StudentsModel model;
    public ListOfStudents () {}
    public ListOfStudents (StudentsModel model) {
        this.model = model;
    }
    public void setList1 (ObservableList<String> l, int level, StudentsModel model) {
        this.list = l;
        listView.getItems().addAll(list);
        this.level = level;
        this.model = model;
        if (level == 1) {
            k = model.getStudentsOfFirstYear();
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            indexColumn.setCellValueFactory(new PropertyValueFactory<>("indexNumber"));
            table.setItems(model.getStudentsOfFirstYear());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setOrientation(Orientation.VERTICAL);
    }

}
