package ba.unsa.etf.rpr;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GradeController implements Initializable {
    @FXML
    private TextField studentField;

    @FXML
    private TextField gradeField;

    @FXML
    private ChoiceBox<String> subjectField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button okBtn;

    private ObservableList<String> subjects;
    public GradeController () {}

    public void set (ArrayList<String> list) {
        subjects = FXCollections.observableArrayList(list);
        subjectField.setItems(subjects);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }
}
