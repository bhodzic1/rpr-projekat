package ba.unsa.etf.rpr;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentReport implements Initializable {
    private Student student;
    public StudentReport (Student student) {
        this.student = student;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
