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


    private ListOfStudents listOfStudents;
    @FXML
    private ChoiceBox<String> cycle;

    @FXML
    private ChoiceBox<String> year;

    @FXML
    private ChoiceBox<String> subject;

    @FXML
    private Button go;


    private ObservableList<String> listOfYear = FXCollections.observableArrayList("1", "2", "3");
    private ObservableList<String> listOfYear2 = FXCollections.observableArrayList("1", "2");
    private ObservableList<String> subjectsOfFirstYear = FXCollections.observableArrayList("All", "Fizika", "Matematika 1", "Osnove računarstva", "Osnove računarstva", "Linearna algebra", "Operativni sistemi", "Matematika 2", "Tehnike programiranja", "Vjerovatnoća i statistika", "Matematička logika");
    private ObservableList<String> lista = FXCollections.observableArrayList();
    private boolean bachelor;
    private boolean master;
    private boolean firstYear;
    private boolean secondYear;
    private boolean thirdYear;
    private boolean all;
    private String selectedSubject;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bachelor = false;
        master = false;
        firstYear = false;
        secondYear = false;
        thirdYear = false;
        all = false;
        lista.add(new String("Bernes"));
        lista.add(new String("Osman"));
        lista.add(new String("Ersin"));
        lista.add(new String("Harun"));
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
                    subject.setItems(subjectsOfFirstYear);
                    subject.setDisable(false);
                    cycle.setDisable(true);
                    firstYear = true;
                } else if (newValue.equals("2")) {
                    cycle.setDisable(true);
                    secondYear = true;
                } else {
                    cycle.setDisable(true);
                    thirdYear = true;
                }
            }
        });

        subject.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("All")) {
                    all = true;
                } else {
                    selectedSubject = newValue;
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
            if (firstYear == true) {
                listOfStudents.setList1(subjectsOfFirstYear);

            } else if (secondYear == true) {

            } else {

            }
        } else {

        }

        myStage.setResizable(false);
        myStage.show();
    }
}
