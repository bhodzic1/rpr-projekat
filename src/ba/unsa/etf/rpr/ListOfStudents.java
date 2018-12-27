package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListOfStudents implements Initializable {

    public ListOfStudents () {}

    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> lastnameColumn;

    @FXML
    private TableColumn<Student, String> indexColumn;

    @FXML
    private ListView<Subject> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
