
package Controller;
import GUI.MainFrame;
import Model.Board;
import javafx.application.*;
import javafx.embed.swing.SwingNode;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Matopeli extends Application {

    Stage window;
    Scene scene1, scene2;
    
    private static Board board;
    private static MainFrame peli;
    private static PlayerController pc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Gitmato");
        
        
        
        //----GAME MODE SELECTOR SCENE-----------

        //Button to Single player
        Label label1 = new Label("Choose your gamemode!");
        Button button1 = new Button("Single player");
        //button1.setOnAction(e -> window.setScene(scene2));
        
         //Button to Versus
        //Label label1 = new Label("Versus!");
        Button button2 = new Button("Versus");
        button2.setOnAction(e -> window.setScene(scene2));

        //Layout 1 - Game mode selector
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1, button2);
        scene1 = new Scene(layout1, 800, 600);
        //---------------------------------
        
        //------VERSUS SCENE---------------

        //Layout 2 - Versus
        StackPane layout2 = new StackPane();
        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode, this);
        layout2.getChildren().add(swingNode); // Adding swing node
        scene2 = new Scene(layout2, 800, 600);
        //-----------------------------------
        
        //------GAME OVER SCENE---------------
        /*
        //Layout 3- Game over
        VBox layout3 = new VBox(20);
       
        Label label3 = new Label("GAME OVER");
        Button restart = new Button("Restart");
        restart.setOnAction(e -> window.setScene(scene2));
        
        Button backToSS = new Button("Back to Main menu");
        backToSS.setOnAction(e -> window.setScene(scene1));
        
        layout3.getChildren().addAll(label3, restart, backToSS); // Adding swing node
        scene2 = new Scene(layout2, 800, 600);
        //-----------------------------------
        */
        
        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("Title Here");
        window.show();
    }
    
    //So this shit makes it so that we can add the board into a JavaFX Scene
     private void createAndSetSwingContent(final SwingNode swingNode, Matopeli m) {
         SwingUtilities.invokeLater(() -> {
             JPanel board = new Board(m);
             swingNode.setContent(board);
         });
     }

}
/*
public class Matopeli{
    
    private static MainFrame peli;
    private static UIController uic;
    private static PlayerController pc;

   
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {     
                /*lpane = new JLayeredPane();
                peli = new MainFrame();
                peli.setLayout(new BorderLayout());
                peli.add(lpane, BorderLayout.CENTER);
                peliOhi = new GameOver(peli);

                peliOhi.setOpaque(true);

                lpane.add(board, 0, 0);
                lpane.add(peliOhi, 1, 0);

                peli.pack();
                peli.setVisible(true);
                uic = new UIController();
                pc = new PlayerController();
                peli = new MainFrame(uic);
                //peli.pack();
                peli.setVisible(true);
            }
        });         
    } 
}
    */ 
