package GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

class LevelEditor extends GridPane {

    private Button[][] buttons = new Button[12][9];
    private Boolean[][] buttonbooleans = new Boolean[12][9];
    private int i = 0;
    private int j = 0;
    private int k;
    private int l;

    LevelEditor createGrid() {
        setPrefSize(800, 600);
        generateButtons();
        return this;
    }

    private void generateButtons() {
        for (i = 0; i < 12; i++) {
            for (j = 0; j < 9; j++) {
                buttons[i][j] = new Button(Integer.toString(i) + "" + Integer.toString(j));
                buttons[i][j].setOnAction(e -> {
                    String text = ((Button) e.getSource()).getText();
                    System.out.println(text);

                    if (text.length() > 2) {
                        k = Integer.parseInt(text.substring(0,2));
                        l = Character.getNumericValue(text.charAt(2));
                    } else {
                        k = Character.getNumericValue(text.charAt(0));
                        l = Character.getNumericValue(text.charAt(1));
                    }
                    buttonbooleans[k][l] = !buttonbooleans[k][l];
                    System.out.println("coordinates" + k + l + "boolean: " + buttonbooleans[k][l]);

                });
                buttonbooleans[i][j] = false;
                add(buttons[i][j], i, j);
                buttons[i][j].setId("invisible");
                buttons[i][j].setPrefSize(50, 50);
            }
        }

    }
}
