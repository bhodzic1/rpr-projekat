package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ListOfProfessors implements Initializable {

    public ListOfProfessors () {}

    @FXML
    private TableView<Professor> table;

    @FXML
    private TableColumn<Professor,String> nameColumn;

    @FXML
    private TableColumn<Professor,String> lastnameColumn;

    @FXML
    private TableColumn<Professor, String> birthdayColumn;

    @FXML
    private TableColumn<Professor,String> employmentColumn;

    private CollegeDAO dao = CollegeDAO.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void set() {
        ArrayList<Professor> models = new ArrayList<>();
        models = dao.getAllProfessors();


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        employmentColumn.setCellValueFactory(new PropertyValueFactory<>("employmentDay"));
        table.setItems(FXCollections.observableArrayList(models));
    }
}
