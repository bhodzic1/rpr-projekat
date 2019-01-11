package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SubjectReport implements Initializable {

    public SubjectReport () {

    }
    @FXML
    private Label title;

    public void setTextForTitle (String string) {
        title.setText(string);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
