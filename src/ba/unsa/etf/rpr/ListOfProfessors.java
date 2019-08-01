package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;


public class ListOfProfessors {

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
}
