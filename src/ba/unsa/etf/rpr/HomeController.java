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

    @FXML
    private Button registryButton;
    @FXML
    private Button listButton;

    private RegistrationForm formController;
    public HomeController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void registration (ActionEvent actionEvent) {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));
        formController = loader.getController();
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myStage.setTitle("Student registration");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        myStage.setResizable(false);
        myStage.show();
    }


}
