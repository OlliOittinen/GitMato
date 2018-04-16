package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionDialog {

Stage window2;


    public Stage loadFXML(Stage window) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/DialogDesign.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
        window2 = window;
        return window;

    }

}


