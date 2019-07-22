package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController implements Initializable {
    public HomeController() {

    }

    @FXML
    private Button registryButton;
    @FXML
    private Button listButton;


    private RegistrationForm formController;
    private ListOfStudents listOfStudents;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void registration (ActionEvent actionEvent) {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        formController = loader.getController();
        myStage.setTitle("Student registration");
        myStage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }

    @FXML
    public void filter (ActionEvent actionEvent) {
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

        myStage.setResizable(false);
        myStage.show();
    }


}
