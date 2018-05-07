package GUI;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

class LevelEditor extends GridPane {

    private Button[][] buttons = new Button[12][9];
    private Boolean[][] buttonbooleans = new Boolean[12][9];
    private Point2D[][] coordinates = new Point2D[12][9];
    private int i = 0;
    private int j = 0;
    private int k;
    private int l;

    LevelEditor() {
        setPrefSize(800, 600);
        generateButtons();
    }


    private void generateButtons() {
        for (i = 0; i < 12; i++) {
            for (j = 0; j < 9; j++) {
                coordinates[i][j]= new Point2D((25+(62.5*i)), (25+(60*j)));
                buttons[i][j] = new Button(Integer.toString(i) + "" + Integer.toString(j));
                buttons[i][j].setOnAction(e -> {
                    String text = ((Button) e.getSource()).getText();
                    System.out.println(text);

                    if (text.length() > 2) {
                        k = Integer.parseInt(text.substring(0, 2));
                        l = Character.getNumericValue(text.charAt(2));
                    } else {
                        k = Character.getNumericValue(text.charAt(0));
                        l = Character.getNumericValue(text.charAt(1));
                    }
                    buttonbooleans[k][l] = !buttonbooleans[k][l];
                    System.out.println("coordinates" + k + l + "boolean: " + buttonbooleans[k][l]);

                    if (buttonbooleans[k][l]) {
                        ImageView tree =  new ImageView("images/Tree1.png");
                        tree.setX(coordinates[k][l].getX());
                        tree.setY(coordinates[k][l].getY());
                        buttons[k][l].setGraphic(tree);
                    }

                });
                buttonbooleans[i][j] = false;
                add(buttons[i][j], i, j);

                buttons[i][j].setId("invisible");
                buttons[i][j].setPrefSize(50, 50);
            }
        }

    }




    //getters and setters
    public Boolean[][] getButtonbooleans() {
        return buttonbooleans;
    }

    public void setButtonbooleans(Boolean[][] buttonbooleans) {
        this.buttonbooleans = buttonbooleans;
    }

    public Point2D[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point2D[][] coordinates) {
        this.coordinates = coordinates;
    }
}
