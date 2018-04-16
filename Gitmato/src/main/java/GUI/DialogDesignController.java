/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
/*
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;


public class DialogDesignController {

    Button playbtn;
    Button hostbtn;
    Button continuebtn;

    boolean play;
    boolean host;
    boolean address;
    boolean continuen;

    public void MultiplayOptions(){
        playbtn.setOnAction(e -> {
            play =true;
            host = false;
            address.setDisable(false);
            continuebtn.setDisable(false);
        });
        hostbtn.setOnAction(e -> {
            hostbtn.setSelected(true);
            playbtn.setSelected(false);
            continuebtn.setDisable(false);
        });
        continuebtn.setOnAction(e -> {
            Matopeli.address = address.getText();
            Matopeli.server = hostbtn.isSelected();

            Matopeli game = new Matopeli;

            try {
                Matopeli.start(OptionDialog.window);
                OptionDialog.window2.centerOnScreen();
            } catch (Exception ex) {
            }

        });
    }
    private void setSelectet(){

    }

}
*/