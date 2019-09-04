package ba.unsa.etf.rpr;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import org.junit.jupiter.api.Test;




import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.util.List;

@ExtendWith(ApplicationExtension.class)
class Tests {
    Login controller;
    CollegeDAO dao = CollegeDAO.getInstance();
    @Start
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller = loader.getController();

        stage.setTitle("Student registration");
        stage.setScene(new Scene(loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    @Test
    public void testLogin (FxRobot robot) {
        robot.lookup("#username").tryQuery().isPresent();
        robot.clickOn("#username");
        robot.write("admin");
        robot.clickOn("#password");
        robot.write("adminadmin");
        robot.clickOn("#ok");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.lookup("#active").tryQuery().isPresent();
        String active = robot.lookup("#active").queryAs(Label.class).getText();
        assertNotNull(active);
        assertEquals("Admin", active);
    }

    @Test
    public void testCreateAndDeleteProfessor (FxRobot robot) {
        robot.lookup("#createProfessor").tryQuery().isPresent();
        robot.clickOn("#createProfessor");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.lookup("#idField").tryQuery().isPresent();
        TextField id = robot.lookup("#idField").queryAs(TextField.class);
        assertNotNull(id);
        robot.clickOn("#nameField");
        robot.write("Nikola");

        robot.clickOn("#lastnameField");
        robot.write("Nikic");

        robot.clickOn("#dateField");
        robot.write("13. 02. 1980");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#usernameField");
        robot.write("nnikic1");

        robot.clickOn("#passwordField");
        robot.write("12345678");

        robot.clickOn("#employmentDay");
        robot.write("05. 09. 2019");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#okBtn");

        robot.lookup(".dialog-pane").tryQuery().isPresent();
        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);

        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        robot.lookup("#listProfBtn").tryQuery().isPresent();
        robot.clickOn("#listProfBtn");
        TableView tableView = robot.lookup("table").queryAs(TableView.class);
        List<Professor> list = dao.getAllProfessors();

        assertTrue(list.contains("Nikola Nikic 1980-02-13 2019-09-05"));

        try {
            Thread.sleep(5000);
            robot.press(KeyCode.DOWN);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.release(KeyCode.DOWN);

        robot.clickOn("#deleteBtn");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.lookup(".dialog-pane").tryQuery().isPresent();
        DialogPane dialogPane2 = robot.lookup(".dialog-pane").queryAs(DialogPane.class);

        Button okButton2 = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        list = dao.getAllProfessors();
        assertTrue(!list.contains("Nikola Nikic 13.02.1980 05.09.2019"));

    }


}
