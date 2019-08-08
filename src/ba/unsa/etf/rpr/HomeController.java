package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    @FXML
    private Button listProfBtn;

    @FXML
    private Button createProfessor;

    @FXML
    private Button createSubject;

    @FXML
    private ImageView imageView;

    @FXML
    private Label active;


    private CollegeDAO dao = CollegeDAO.getInstance();
    private RegistrationForm formController;
    private ListOfStudents listOfStudents;
    private CreateProfessor createProfessorController;
    private CreateSubject createSubjectCotroller;
    private ListOfProfessors listOfProfessors;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FileInputStream input = new FileInputStream("resources/img/logo.png");
            Image image = new Image(input);
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registration (ActionEvent actionEvent) {
        if (dao.getUsernameFromActive().equals("admin")) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not possible!");
            alert.setHeaderText("You are not admin!");
            alert.setContentText("You need to be logged as admin!");
            alert.show();
        }
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

    @FXML
    public void createProf (ActionEvent actionEvent) {
        if (dao.getUsernameFromActive().equals("admin")) {
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createProfessor.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            createProfessorController = loader.getController();
            myStage.setTitle("Create professor");
            myStage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

            myStage.setResizable(false);
            myStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not possible!");
            alert.setHeaderText("You are not admin!");
            alert.setContentText("You need to be logged as admin!");
            alert.show();
        }
    }

    @FXML
    public void createSubj (ActionEvent actionEvent) {
        if (dao.getUsernameFromActive().equals("admin")) {
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createSubject.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            createSubjectCotroller = loader.getController();
            createSubjectCotroller.set();
            myStage.setTitle("Create subject");
            myStage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

            myStage.setResizable(false);
            myStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not possible!");
            alert.setHeaderText("You are not admin!");
            alert.setContentText("You need to be logged as admin!");
            alert.show();
        }
    }

    @FXML
    public void listOfProfessors (ActionEvent actionEvent) {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/listOfProfessors.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listOfProfessors = loader.getController();
        listOfProfessors.set();
        myStage.setTitle("List of professors");
        myStage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        myStage.setResizable(false);
        myStage.show();
    }

    public void set (String username) {
        active.setText(dao.getActiveUser(username));
    }


}
