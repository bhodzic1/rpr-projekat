package ba.unsa.etf.rpr;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import org.junit.jupiter.api.Test;




import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;





import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
class Tests {
    Login controller;
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


}
