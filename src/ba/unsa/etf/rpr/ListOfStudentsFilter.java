package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ListOfStudentsFilter implements Initializable {

    public ListOfStudentsFilter () {}

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



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cycle.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Bachelor")) {
                    year.setItems(listOfYear);
                    year.setDisable(false);
                } else if (newValue.equals("Master")) {
                    year.setItems(listOfYear2);
                    year.setDisable(false);
                }
            }
        });
    }

    @FXML
    public void go (ActionEvent actionEvent) {

    }
}
