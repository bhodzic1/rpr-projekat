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

@ExtendWith(ApplicationExtension.class)
class Tests {
    Login controller;
    CollegeDAO dao = CollegeDAO.getInstance();
    @Start
    public void start (Stage stage) {
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
    public void testA (FxRobot robot) {
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
    public void testB (FxRobot robot) {
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
        String professors = String.valueOf(dao.getAllProfessors());

        assertTrue(professors.contains("Nikola Nikic 1980-02-13 2019-09-05"));

        robot.press(KeyCode.DOWN).sleep(2000);
        robot.release(KeyCode.DOWN);

        robot.clickOn("#deleteBtn");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.lookup(".dialog-pane").tryQuery().isPresent();
        DialogPane dialogPane2 = robot.lookup(".dialog-pane").queryAs(DialogPane.class);

        Button okButton2 = (Button) dialogPane2.lookupButton(ButtonType.OK);
        robot.clickOn(okButton2);

        professors = String.valueOf(dao.getAllProfessors());
        assertTrue(!professors.contains("Nikola Nikic 13.02.1980 05.09.2019"));
        robot.closeCurrentWindow();
    }

    @Test
    public void testC (FxRobot robot) {
        robot.lookup("#createSubject").tryQuery().isPresent();
        robot.clickOn("#createSubject");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.lookup("#idField").tryQuery().isPresent();
        TextField id = robot.lookup("#idField").queryAs(TextField.class);
        assertNotNull(id);
        robot.clickOn("#nameField");
        robot.write("Testni predmet");

        robot.clickOn("#semesterBox");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#professorBox");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#okBtn");


        robot.lookup(".dialog-pane").tryQuery().isPresent();
        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);

        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);


        robot.lookup("#listOfSubjects").tryQuery().isPresent();
        robot.clickOn("#listOfSubjects");
        TableView tableView = robot.lookup("table").queryAs(TableView.class);
        String subjects = String.valueOf(dao.getAllSubjects());

        assertTrue(subjects.contains("Testni predmet"));

        robot.press(KeyCode.DOWN).sleep(2000);
        robot.release(KeyCode.DOWN);

        robot.clickOn("#deleteBtn");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.lookup(".dialog-pane").tryQuery().isPresent();
        DialogPane dialogPane2 = robot.lookup(".dialog-pane").queryAs(DialogPane.class);

        Button okButton2 = (Button) dialogPane2.lookupButton(ButtonType.OK);
        robot.clickOn(okButton2);

        subjects = String.valueOf(dao.getAllSubjects());
        assertTrue(!subjects.contains("Testni predmet 2"));
        robot.closeCurrentWindow();
    }

    @Test
    public void testD (FxRobot robot) {
        robot.lookup("#registryButton").tryQuery().isPresent();
        robot.clickOn("#registryButton");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.lookup("#nameField").tryQuery().isPresent();

        robot.clickOn("#nameField");
        robot.write("Saša");

        robot.clickOn("#lastnameField");
        robot.write("Vugdalić");

        robot.press(KeyCode.TAB);
        robot.release(KeyCode.TAB);
        robot.press(KeyCode.TAB);
        robot.release(KeyCode.TAB);

        robot.clickOn("#dateField");
        robot.write("05. 09. 1998");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#idNumberField");
        robot.write("0509998134217");

        robot.clickOn("#studyLevelBox");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#studyYear");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#addressField");
        robot.write("Zenica");

        robot.clickOn("#emailField");
        robot.write("svugdalic1@etf.unsa.ba");

        robot.clickOn("#enrolmentDate");
        robot.write("05. 09. 2019");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#addButton");

        robot.lookup(".dialog-pane").tryQuery().isPresent();
        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);

        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);


        /*robot.lookup("#listButton").tryQuery().isPresent();
        robot.clickOn("#listButton");
        TableView tableView = robot.lookup("tableViewBachelor").queryAs(TableView.class);*/
        String students = String.valueOf(dao.getAllStudents());
        String id = String.valueOf(dao.getMaxId() - 1);

        assertTrue(students.contains("Saša Vugdalić " + id));

        dao.deleteStudent(10);
        students = String.valueOf(dao.getAllStudents());

        assertFalse(students.contains("Saša Vugdalić " + id));
        dao.deleteAllFromActive();
    }


}
