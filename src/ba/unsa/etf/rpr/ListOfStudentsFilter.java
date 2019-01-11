package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ListOfStudentsFilter implements Initializable {

    public ListOfStudentsFilter () {}
    public ListOfStudentsFilter (StudentsModel model) {
        this.model = model;
    }


    private ListOfStudents listOfStudents;
    @FXML
    private ChoiceBox<String> cycle;

    @FXML
    private ChoiceBox<String> year;

    @FXML
    private ChoiceBox<String> semester;

    @FXML
    private Button go;
    private ObservableList<String> listOfYear = FXCollections.observableArrayList("1", "2", "3");
    private ObservableList<String> listOfYear2 = FXCollections.observableArrayList("1", "2");
    private ObservableList<String> subjects = FXCollections.observableArrayList();
    private ObservableList<String> lista = FXCollections.observableArrayList();
    private Syllabus syllabus;
    private boolean bachelor;
    private boolean master;
    private boolean firstYear;
    private boolean secondYear;
    private boolean thirdYear;
    private boolean all;
    private String selectedSubject;
    private int level = 0;
    private StudentsModel model;
    private int semesterValue;

    public void set (StudentsModel model) {
        this.model = model;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bachelor = false;
        master = false;
        firstYear = false;
        secondYear = false;
        thirdYear = false;
        all = false;
        syllabus = new Syllabus();
        cycle.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Bachelor")) {
                     year.setItems(listOfYear);
                     year.setDisable(false);
                     bachelor = true;
                } else if (newValue.equals("Master")) {
                    year.getItems().removeAll();
                    year.setItems(listOfYear2);
                    year.setDisable(false);
                    master = true;
                }
            }
        });

        year.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("1")) {
                    cycle.setDisable(true);
                    firstYear = true;
                    level = 1;
                } else if (newValue.equals("2")) {
                    cycle.setDisable(true);
                    secondYear = true;
                    level = 2;
                } else {
                    cycle.setDisable(true);
                    thirdYear = true;
                    level = 3;
                }
            }
        });

        semester.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               if (newValue.equals("1")) {
                    semesterValue = 1;
               } else {
                    semesterValue = 2;
               }
            }
        });


    }

    @FXML
    public void go (ActionEvent actionEvent) {

        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListOfStudents.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listOfStudents = loader.getController();
        myStage.setTitle("Student registration");
        myStage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));



        if (bachelor == true) {
            if (firstYear == true && semesterValue == 1) {
                subjects = syllabus.getSubjectsOfFirstYear1();
                listOfStudents.setList(subjects, level, model);

            } else if (firstYear == true && semesterValue == 2) {
                subjects = syllabus.getSubjectsOfFirstYear2();
                listOfStudents.setList(subjects, level, model);
            } else {

            }
        } else {

        }

        myStage.setResizable(false);
        myStage.show();
    }
}
