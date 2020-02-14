package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListOfSubjects implements Initializable {
    public ListOfSubjects () {}

    @FXML
    private TableView<Subject> table;

    @FXML
    private TableColumn<Subject,String> idColumn;

    @FXML
    private TableColumn<Subject,String> nameColumn;

    @FXML
    private TableColumn<Subject,String> semesterColumn;

    @FXML
    private Button deleteBtn;

    private CollegeDAO dao = CollegeDAO.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void set() {
        ArrayList<Subject> models = new ArrayList<>();
        models = dao.getAllSubjects();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        table.setItems(FXCollections.observableArrayList(models));
    }

    @FXML
    public void deleteProfessor (ActionEvent actionEvent) {
        if (dao.getUsernameFromActive().equals("admin")) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                Subject subject = table.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete confirmation");
                alert.setHeaderText("Delete subject " + subject.getName());
                alert.setContentText("Are you sure you want to delete " + subject.getName() + "?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.deleteSubject(subject.getId());
                    table.setItems(FXCollections.observableArrayList(dao.getAllSubjects()));
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Deleting subject.");
                alert.setHeaderText("Subject is not selected.");
                alert.setContentText("You need to select subject.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not possible!");
            alert.setHeaderText("You are not admin!");
            alert.setContentText("You need to be logged as admin!");
            alert.show();
        }



    }

    @FXML
    public void print (ActionEvent actionEvent) {
        File file = new File("subjects.txt");
        ArrayList<Subject> models = new ArrayList<>();
        models = dao.getAllSubjects();

        if (file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                String string = "";
                string += "Name of subject, semester\n";
                for (Subject k : models) {
                    string += k.getName() + ", " + k.getSemester() + "\n";
                }
                fileWriter.write(string);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }

}
