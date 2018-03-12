
package GUI;
import Controller.PlayerController;
import Model.Board;
import javafx.application.*;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Matopeli extends Application {

    Stage window;
    Scene scene1, scene2, scene3, scene4;
    
    private static Board board;
    private static PlayerController pc;
    final SwingNode swingNode = new SwingNode();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Gitmato");
        Sound.Music.sound1.loop();
        
        
        
        //----GAME MODE SELECTOR SCENE-----------

        //Button to Single player
        Button button1 = new Button("Player VS AI");
        button1.setOnAction(e ->
                {
                createAndSetSwingContent(swingNode, this, "vs AI");
                StackPane layout2 = new StackPane();
                layout2.getChildren().add(swingNode); // Adding swing node
                scene2 = new Scene(layout2, 800, 590);
                window.setScene(scene2);
                });
        
         //Button to Versus
        Button button2 = new Button("Versus");
        button2.setOnAction(e ->
                {
                createAndSetSwingContent(swingNode, this, "versus");
                StackPane layout3 = new StackPane();
                layout3.getChildren().add(swingNode); // Adding swing node
                scene3 = new Scene(layout3, 800, 590);
                window.setScene(scene3);
                });
        Button button3 = new Button("Single player");
        button3.setOnAction(e ->
                {
                createAndSetSwingContent(swingNode, this, "sp");
                StackPane layout4 = new StackPane();
                layout4.getChildren().add(swingNode);
                scene4 = new Scene(layout4, 800, 590);
                //layout4.getChildren().remove(0);
                window.setScene(scene4);
                });
        
        //Layout 1 - Game mode selector
        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll( button2, button1, button3);
        scene1 = new Scene(layout1, 800, 506);
        layout1.setId("pane");
        scene1.getStylesheets().add("Styling/styling.css");
        
        
        //Display scene 1 at first
        window.setScene(scene1);
        window.setOnCloseRequest(e->System.exit(0));
        window.setTitle("Gitmato");
        window.show();
    }
    
    //So this shit makes it so that we can add the board into a JavaFX Scene
     private void createAndSetSwingContent(final SwingNode swingNode, Matopeli m, String pelimoodi) {
         SwingUtilities.invokeLater(() -> {
             JPanel board = new Board(m, pelimoodi);
             swingNode.setContent(board);
             
         });
     }

}

