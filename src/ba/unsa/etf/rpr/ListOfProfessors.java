package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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

    @FXML
    private Button deleteBtn;

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

    @FXML
    public void deleteProfessor (ActionEvent actionEvent) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Professor professor = table.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete confirmation");
            alert.setHeaderText("Delete professor " + professor.getName() + " " + professor.getLastname());
            alert.setContentText("Are you sure you want to delete " + professor.getName() + " " + professor.getLastname() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                dao.deleteProfessor(professor.getName(), professor.getLastname(), professor.getBirthday(), professor.getEmploymentDay());
                table.setItems(FXCollections.observableArrayList(dao.getAllProfessors()));
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting professor.");
            alert.setHeaderText("Professor is not selected.");
            alert.setContentText("You need to select professor.");
            alert.show();
        }



    }
}