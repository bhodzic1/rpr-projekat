package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    public ListOfStudents () {}

    public void setList1 (ObservableList<String> l) {
        this.list = l;
        listView.getItems().addAll(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setOrientation(Orientation.VERTICAL);
    }

}
