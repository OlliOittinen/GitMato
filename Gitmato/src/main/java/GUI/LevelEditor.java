package GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

class LevelEditor extends GridPane {

    private Button[][] buttons = new Button[12][9];
    private Boolean[][] buttonbooleans = new Boolean[12][9];
    private int i = 0;
    private int j = 0;

    LevelEditor createGrid() {
        setPrefSize(800, 600);
        generateButtons();
        return this;
    }

    private void generateButtons() {
        for (i = 0; i < 12; i++) {
            for (j = 0; j < 9; j++) {
                buttons[i][j] = new Button(Integer.toString(i) + " , " + Integer.toString(j));
                buttons[i][j].setOnAction(e -> {
                    String text = ((Button) e.getSource()).getText();
                    System.out.println(text);
                    int k = text.charAt(0);
                    int l = text.charAt(4);
                    buttonbooleans[k][l] = !buttonbooleans[k][l];
                    System.out.println("coordinates" + k + l + "boolean: " + buttonbooleans[k][l]);

                });
                buttonbooleans[i][j] = false;
                add(buttons[i][j], i, j);
                //buttons[i][j].setId("invisible");
                buttons[i][j].setPrefSize(50, 50);
            }
        }

    }
}
