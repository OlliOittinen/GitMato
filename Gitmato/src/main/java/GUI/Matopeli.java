package GUI;

import javafx.geometry.Point2D;
import Controller.PlayerController;
import Sound.Music;
import javafx.animation.AnimationTimer;
import Model.*;
import Spawnables.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Matopeli extends Application {

    private static Stage window;
    private Scene mainMenuScene, gameScene, gameOverScene, highscoreScene, highscoreTableScene, leveleditorscene;


    private Image bbackground = new Image("images/BlueBG800x600.png");
    private Image gbackground = new Image("images/GreenBG800x600.png");
    private Image rbackground = new Image("images/RedBG800x600.png");
    private Image background = bbackground;

    private Image snackicon = new Image("images/Apple(800x600).png");
    private Image bombicon = new Image("images/Bombs(800-600).png");
    private Image bombtarget = new Image("images/Target2.png");
    private Image bombfire = new Image("images/firestorm2.png");
    private Image confuseicon = new Image("images/Reverse.png");
    private Image confuseEffect = new Image("images/confusion.png");
    private Image fastericon = new Image("images/SpeedUp.png");
    private Image lasericon = new Image("images/Lasercannon.png");
    private Image switchericon = new Image("images/Portal.png");
    private Image laserH = new Image("images/LazerH.png");
    private Image laserV = new Image("images/lazerV.png");
    private Image lasersightV = new Image("images/lasersightV.png");
    private Image lasersightH = new Image("images/lasersightH.png");
    private Image lifeicon = new Image("images/Life_1.png");
    private Image shieldicon = new Image("images/Shield.png");
    private Image shieldeffect = new Image("images/ShieldEffect.png");
    private Image slowericon = new Image("images/SlowDown.png");
    private Image redwormleft = new Image("images/RedWormLeft(800x600).png");
    private Image redwormright = new Image("images/RedWormRight(800x600).png");
    private Image redwormup = new Image("images/RedWormUp(800x600).png");
    private Image redwormdown = new Image("images/RedWormDown(800x600).png");
    private Image bluewormleft = new Image("images/BlueWormLeft(800x600).png");
    private Image bluewormright = new Image("images/BlueWormRight(800x600).png");
    private Image bluewormup = new Image("images/BlueWormUp(800x600).png");
    private Image bluewormdown = new Image("images/BlueWormDown(800x600).png");
    private Image wormImage = redwormup;
    private Image worm2Image = bluewormup;
    private Image wormtailimage = new Image("images/RedWormTail(800x600).png");
    private Image wormtail2image = new Image("images/BlueWormTail(800x600).png");
    private Image wormtail = wormtailimage;
    private Image wormtail2 = wormtail2image;
    private Image redwormleftT = new Image("images/RedWormLeft(800x600)T.png");
    private Image redwormrightT = new Image("images/RedWormRight(800x600)T.png");
    private Image redwormupT = new Image("images/RedWormUp(800x600)T.png");
    private Image redwormdownT = new Image("images/RedWormDown(800x600)T.png");
    private Image bluewormleftT = new Image("images/BlueWormLeft(800x600)T.png");
    private Image bluewormrightT = new Image("images/BlueWormRight(800x600)T.png");
    private Image bluewormupT = new Image("images/BlueWormUp(800x600)T.png");
    private Image bluewormdownT = new Image("images/BlueWormDown(800x600)T.png");
    private Image wormtailT = new Image("images/RedWormTail(800x600)T.png");
    private Image wormtail2T = new Image("images/BlueWormTail(800x600)T.png");
    private Image cowboyhat = new Image("images/Hat.png");
    private Image bighat = new Image("images/BigHat.png");
    private Image bluehat = new Image("images/Hat1.png");
    private Image bigbluehat = new Image("images/Hattu1.png");
    private Image redhat = new Image("images/Hat2.png");
    private Image bigredhat = new Image("images/Hattu2.png");
    private Image purplehat = new Image("images/Hat3.png");
    private Image bigpurplehat = new Image("images/Hattu3.png");
    private Image tree1 = new Image("images/Tree1.png");

    private Image scissoricon = new Image("images/stealicon.png");
    private ArrayList<Image> infoimg = new ArrayList();
    private Image controlinfo = new Image("images/InfoControls.png");
    private Image gameideainfo = new Image("images/gameidea.png");
    private Image powerupinfo = new Image("images/powerupinfo.png");
    private Image wormskin;
    private Image worm2skin;
    private ArrayList<Image> hatimages = new ArrayList();
    private ArrayList<Image> bighatimages = new ArrayList<>();
    private ArrayList<Image> bgimages = new ArrayList<>();
    private ImageView imv = new ImageView();
    private ImageView imv2 = new ImageView();
    private ImageView infoimv = new ImageView();
    private ImageView colorbuttonimv = new ImageView();

    private static int width = 800;
    private static int height = 600;
    private Worm worm, worm2;
    private int tailsize = 0;
    private int tailsize2 = 0;
    private Board board;
    private ArrayList<Tail> body;
    private ArrayList<Tail> body2;
    private String gameMode = null;
    private PlayerController pc;
    private AnimationTimer timer;
    private Faster faster;
    private Slower slower;
    private Confuse confuse;
    private Life life;
    private Shield shield;
    private Bombs bombs;
    private Laser laser;
    private Steal steal;
    private Snack snack;
    private Switcher switcher;
    private ArrayList<Worm> worms;
    private ArrayList<Spawnables> powerups;
    private long currentTime = 0; // current time (ms)
    private long previousTime = 0; // time since last frame (ms)
    private double timeCounter = 0; // counts time (sec)
    private int frameCounter = 0;
    private double theRealFpsCounter = 0; // constantly shows the FPS
    private int score = 0;
    private Label winner_label = new Label();
    private Text scenetitle = new Text();
    private String username;
    private boolean hs_submitted;
    private Button highscore_button;
    private Button bgcolour;
    private boolean wormskinactive;
    private boolean worm2skinactive;
    private int skinindex = -1;
    private int skinindex2 = -1;
    private int infoindex = 0;
    private int bgindex = 0;

    public LevelEditor getEditorpane() {
        return editorpane;
    }

    private LevelEditor editorpane;
    private Boolean[][] booleans = new Boolean[12][9];
    private Point2D[][] coords = new Point2D[12][9];

    /**
     * Launches the application.
     *
     * @param args Command-line arguments are passed in args.
     */
    public static void main(String[] args) {
        launch(args);
    }


    private Button[] createMainMenuButtons(GraphicsContext gc) {
        Button button1 = new Button("Player VS AI");
        button1.setOnAction(e
                -> {
            gameMode = "vs AI";
            init(gameMode);
            window.setScene(gameScene);
            animationLoop(gc);
        });

        Button button2 = new Button("Versus");
        button2.setId("Versus");
        button2.setOnAction(e
                -> {
            gameMode = "versus";
            init(gameMode);
            window.setScene(gameScene);
            animationLoop(gc);
        });

        Button button3 = new Button("Single player");
        button3.setOnAction(e
                -> {
            gameMode = "sp";
            init(gameMode);
            window.setScene(gameScene);
            animationLoop(gc);
        });

        //create level editor scene
        Button leveleditor = new Button("Level Editor");
        leveleditor.setOnAction(e -> {

            Button levelexit = new Button();
            levelexit.setOnAction(event -> window.setScene(mainMenuScene));
            editorpane = new LevelEditor();
            editorpane.getChildren().add(levelexit);
            levelexit.setId("editor_exit");
            editorpane.setId("leveleditor");
            editorpane.getStylesheets().add("Styling/styling.css");
            editorpane.setAlignment(Pos.CENTER);
            Scene scene = new Scene(editorpane, width, height);
            window.setScene(scene);
        });
        Button close = new Button("Exit");
        close.setOnAction(e -> {
            //createConfirmationDialog();
            System.exit(0);
        });
        return new Button[]{button2, button1, button3, leveleditor, close};
    }

    private Scene createInfoPane() {
        colorbuttonimv.setImage(background);
        colorbuttonimv.setFitHeight(70);
        colorbuttonimv.setFitWidth(70);

        bgcolour = new Button();
        Label colorlabel = new Label("Select\nMap\nColor");
        colorlabel.setId("colorlabel");
        bgcolour.setGraphic(colorbuttonimv);
        bgcolour.setOnAction(e -> setBGcolor());
        bgcolour.setId("bgcolorbtn");
        bgcolour.setMaxSize(70, 70);

        GridPane infopane = new GridPane();
        HBox navbuttons = new HBox(50);
        navbuttons.setAlignment(Pos.BOTTOM_CENTER);
        Button forward = new Button();
        forward.setId("orangearrowbuttonright");
        Button backward = new Button();
        backward.setId("orangearrowbuttonleft");
        Button exitbutton = new Button();
        exitbutton.setId("exitbutton");
        exitbutton.setAlignment(Pos.TOP_RIGHT);
        navbuttons.getChildren().addAll(backward, forward);
        infoimv.setImage(controlinfo);
        infoimv.setFitWidth(width);
        infoimv.setFitHeight(height);
        infopane.getChildren().add(infoimv);
        infopane.getChildren().add(navbuttons);
        infopane.getChildren().add(exitbutton);
        infopane.getStylesheets().add("Styling/styling.css");
        Scene infoscene = new Scene(infopane, width, height);

        exitbutton.setOnAction(e -> window.setScene(mainMenuScene));

        infoscene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                navigateInfo("+");
            }
            if (e.getCode() == KeyCode.LEFT) {
                navigateInfo("-");
            }
        });

        forward.setOnAction(e -> {
            navigateInfo("+");
        });

        backward.setOnAction(e -> {
            navigateInfo("-");
        });
        return infoscene;
    }

    private Button[] createBlueHatChoiceButtons() {
        Button blueforwardskin = new Button();
        blueforwardskin.setOnAction(e -> checkWormSkin(2, "+"));
        blueforwardskin.setId("bluearrowbuttonright");
        Button bluebackwardskin = new Button();
        bluebackwardskin.setOnAction(e -> checkWormSkin(2, "-"));
        bluebackwardskin.setId("bluearrowbuttonleft");
        return new Button[]{blueforwardskin, bluebackwardskin};
    }

    private Button[] createRedHatChoiceButtons() {
        Button redforwardskin = new Button();
        redforwardskin.setOnAction(e -> checkWormSkin(1, "+"));
        redforwardskin.setId("redarrowbuttonright");
        Button redbackwardskin = new Button();
        redbackwardskin.setOnAction(e -> checkWormSkin(1, "-"));
        redbackwardskin.setId("redarrowbuttonleft");
        return new Button[]{redforwardskin, redbackwardskin};
    }

    /**
     * Initializes all of the necessary objects needed for the game to run.
     * Creates mainMenuScene and gameScene.
     * Adds functionality for the mainMenu buttons.
     * When game mode is selected starts the game engine.
     *
     * @param primaryStage The JavaFX Stage class is the top level JavaFX container.
     *                     The primary Stage is constructed by the platform.
     *                     Additional Stage objects may be constructed by the application.
     */
    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        window.setTitle("Gitmato");

        window.setResizable(false);
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        gameScene = new Scene(root);

        addImagesToArrays();

        window.setScene(createMainMenu(gc));

        window.setOnCloseRequest(e -> {
            //createConfirmationDialog();
            System.exit(0);
        });
        window.setTitle("Gitmato");
        window.show();
        Sound.Music.backgroundMusic.loop();
    }

    private void addImagesToArrays() {
        colorbuttonimv.setImage(bbackground);
        colorbuttonimv.setFitHeight(70);
        colorbuttonimv.setFitWidth(70);
        bgimages.add(bbackground);
        bgimages.add(gbackground);
        bgimages.add(rbackground);

        hatimages.add(cowboyhat);
        hatimages.add(bluehat);
        hatimages.add(redhat);
        hatimages.add(purplehat);

        bighatimages.add(bighat);
        bighatimages.add(bigbluehat);
        bighatimages.add(bigredhat);
        bighatimages.add(bigpurplehat);

        infoimg.add(controlinfo);
        infoimg.add(gameideainfo);
        infoimg.add(powerupinfo);
    }

    private HBox[] createButtonBoxes() {
        Label skinlabel = new Label("Select\nHat");
        skinlabel.setId("skinlabel");
        HBox bluenextButtons = new HBox(10);
        bluenextButtons.setPadding(new Insets(0, 100, 0, 0));
        bluenextButtons.setAlignment(Pos.BOTTOM_CENTER);
        Button[] blueButtons = createBlueHatChoiceButtons();
        bluenextButtons.getChildren().addAll(blueButtons[1], skinlabel, blueButtons[0]);
        Label skinlabel2 = new Label("Select\nHat");
        skinlabel2.setId("skinlabel");
        HBox rednextButtons = new HBox(10);
        rednextButtons.setPadding(new Insets(0, 0, 0, 100));
        rednextButtons.setAlignment(Pos.BOTTOM_CENTER);
        Button[] redButtons = createRedHatChoiceButtons();
        rednextButtons.getChildren().addAll(redButtons[1], skinlabel2, redButtons[0]);
        return new HBox[] {bluenextButtons, rednextButtons};
    }

    private Scene createMainMenu(GraphicsContext gc) {
        VBox menuLayout = new VBox(5);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(createMainMenuButtons(gc));
        GridPane mainmenupane = new GridPane();
        GridPane skinbuttonpane = new GridPane();
        mainmenupane.setAlignment(Pos.CENTER);
        mainmenupane.setMinSize(width, height);

        HBox[] buttonBoxes = createButtonBoxes();
        skinbuttonpane.add(buttonBoxes[0], 0, 0);
        skinbuttonpane.add(buttonBoxes[1], 1, 0);
        skinbuttonpane.setPadding(new Insets(60, 0, 40, 0));

        mainmenupane.add(createInfoBox(), 0, 0);

        imv.setTranslateX(380);
        imv.setTranslateY(-50);
        imv2.setTranslateX(10);
        imv2.setTranslateY(-50);
        imv2.setId("bluewormhat");

        mainmenupane.add(imv, 0, 0);
        mainmenupane.add(imv2, 0, 0);
        mainmenupane.add(menuLayout, 0, 0);
        mainmenupane.add(skinbuttonpane, 0, 2);
        mainmenupane.setAlignment(Pos.BOTTOM_CENTER);
        mainMenuScene = new Scene(mainmenupane, width, height);
        mainmenupane.setId("main_menu");
        mainMenuScene.getStylesheets().add("Styling/styling.css");

        return mainMenuScene;
    }

    private HBox createInfoBox() {
        HBox infobuttonbox = new HBox();
        Button infobutton = new Button();
        Label colorlabel = new Label("Select\nMap\nColor");
        colorlabel.setId("colorlabel");
        bgcolour = new Button();
        bgcolour.setGraphic(colorbuttonimv);
        bgcolour.setOnAction(e -> setBGcolor());
        bgcolour.setId("bgcolorbtn");
        bgcolour.setPrefSize(70, 70);
        infobutton.setOnAction(e -> window.setScene(createInfoPane()));
        infobutton.setId("infobutton");
        infobuttonbox.getChildren().addAll(colorlabel, bgcolour, infobutton);
        infobuttonbox.setAlignment(Pos.TOP_RIGHT);
        return infobuttonbox;
    }

    private void init(String gameMode) {
        board = new Board(this, gameMode);
        pc = new PlayerController(board, gameMode);
        worms = new ArrayList<>();
        powerups = board.getPickableList();
        snack = (Snack) powerups.get(0);
        faster = (Faster) powerups.get(1);
        slower = (Slower) powerups.get(2);
        confuse = (Confuse) powerups.get(3);
        life = (Life) powerups.get(4);
        shield = (Shield) powerups.get(5);
        bombs = (Bombs) powerups.get(6);
        laser = (Laser) powerups.get(7);
        steal = (Steal) powerups.get(8);
        switcher = (Switcher) powerups.get(9);
        worms.add(worm = board.getWorm());
        worms.add(worm2 = board.getWorm2());

        // updating scene maker
        if(editorpane != null) {
            booleans = editorpane.getButtonbooleans();
            coords = editorpane.getCoordinates();
        }
    }

    private void animationLoop(GraphicsContext gc) {
        timer = new AnimationTimer() {

            //fps-throttle
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    gameScene.setOnKeyPressed((KeyEvent event) -> pc.keyPressed(event));
                    board.updateBoard();
                    draw(gc);
                }
            }
        };
        timer.start();
    }

    /**
     * Sets the wormImages for the Worm objects to be drawn based on the direction they are going.
     */
    public void setWormImage() {
        //red (left) worm
        if (worm.isTransparencyChange()) {
            wormtail = wormtailT;
        } else if (!worm.isTransparencyChange()) {
            wormtail = wormtailimage;
        }
        if (worm.getDirection() == 1 && !worm.isTransparencyChange()) {
            wormImage = redwormleft;
        } else if (worm.getDirection() == 1 && worm.isTransparencyChange()) {
            wormImage = redwormleftT;
        }

        if (worm.getDirection() == 2 && !worm.isTransparencyChange()) {
            wormImage = redwormright;
        } else if (worm.getDirection() == 2 && worm.isTransparencyChange()) {
            wormImage = redwormrightT;
        }

        if (worm.getDirection() == 3 && !worm.isTransparencyChange()) {
            wormImage = redwormup;
        } else if (worm.getDirection() == 3 && worm.isTransparencyChange()) {
            wormImage = redwormupT;
        }

        if (worm.getDirection() == 4 && !worm.isTransparencyChange()) {
            wormImage = redwormdown;
        } else if (worm.getDirection() == 4 && worm.isTransparencyChange()) {
            wormImage = redwormdownT;
        }

        //blue (right) worm
        if (worm2.isTransparencyChange()) {
            wormtail2 = wormtail2T;
        } else if (!worm2.isTransparencyChange()) {
            wormtail2 = wormtail2image;
        }
        if (worm2.getDirection() == 1 && !worm2.isTransparencyChange()) {
            worm2Image = bluewormleft;
        } else if (worm2.getDirection() == 1 && worm2.isTransparencyChange()) {
            worm2Image = bluewormleftT;
        }

        if (worm2.getDirection() == 2 && !worm2.isTransparencyChange()) {
            worm2Image = bluewormright;
        } else if (worm2.getDirection() == 2 && worm2.isTransparencyChange()) {
            worm2Image = bluewormrightT;
        }

        if (worm2.getDirection() == 3 && !worm2.isTransparencyChange()) {
            worm2Image = bluewormup;
        } else if (worm2.getDirection() == 3 && worm2.isTransparencyChange()) {
            worm2Image = bluewormupT;
        }

        if (worm2.getDirection() == 4 && !worm2.isTransparencyChange()) {
            worm2Image = bluewormdown;
        } else if (worm2.getDirection() == 4 && worm2.isTransparencyChange()) {
            worm2Image = bluewormdownT;
        }
    }

    private void checkWormSkin(int index, String sign) {
        if (sign.equals("+") && index == 1) {
            skinindex++;
        }
        if (sign.equals("-") && index == 1) {
            skinindex--;
        }
        if (sign.equals("+") && index == 2) {
            skinindex2++;
        }
        if (sign.equals("-") && index == 2) {
            skinindex2--;
        }
        if (skinindex < hatimages.size() && skinindex > -1) {
            wormskinactive = true;
            wormskin = hatimages.get(skinindex);
            imv.setImage(bighatimages.get(skinindex));
        }
        if (skinindex2 < hatimages.size() && skinindex2 > -1) {
            worm2skinactive = true;
            worm2skin = hatimages.get(skinindex2);
            imv2.setImage(bighatimages.get(skinindex2));
        }
        if (skinindex >= hatimages.size() || skinindex <= -1) {
            skinindex = -1;
            wormskinactive = false;
            imv.setImage(null);
        }
        if (skinindex2 >= hatimages.size() || skinindex2 <= -1) {
            skinindex2 = -1;
            worm2skinactive = false;
            imv2.setImage(null);
        }

    }

    private void setBGcolor() {
        bgindex++;
        if (bgindex >= bgimages.size() || bgindex <= 0) {
            bgindex = 0;
        }
        background = bgimages.get(bgindex);
        colorbuttonimv.setImage(background);
        colorbuttonimv.setFitHeight(70);
        colorbuttonimv.setFitWidth(70);
        bgcolour.setGraphic(colorbuttonimv);
    }

    private void draw(GraphicsContext g) {
        if (board.isIngame()) {
            body = board.getTailList();
            body2 = board.getTailList2();
            tailsize = body.size();
            tailsize2 = body2.size();

            g.drawImage(background, 0, 0);

            if (tailsize > 0) {
                for (int i = 0; i < tailsize; i++) {
                    g.drawImage(wormtail, body.get(i).getX(), body.get(i).getY());
                }
            }
            if (tailsize2 > 0) {
                for (int i = 0; i < tailsize2; i++) {
                    g.drawImage(wormtail2, body2.get(i).getX(), body2.get(i).getY());
                }
            }

            //drawing the trees
            if (editorpane != null) {
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (booleans[i][j]) {
                            g.drawImage(tree1, coords[i][j].getX(), coords[i][j].getY());
                        }
                    }
                }
            }

            //draw powerups last so they're on top and easily visible to the viewer
            g.drawImage(snackicon, snack.getX(), snack.getY());
            g.drawImage(fastericon, faster.getX(), faster.getY());
            g.drawImage(slowericon, slower.getX(), slower.getY());
            g.drawImage(confuseicon, confuse.getX(), confuse.getY());
            g.drawImage(lifeicon, life.getX(), life.getY());
            g.drawImage(shieldicon, shield.getX(), shield.getY());
            g.drawImage(bombicon, bombs.getX(), bombs.getY());
            g.drawImage(bombtarget, bombs.getXBombs(1), bombs.getYBombs(1));
            g.drawImage(bombfire, bombs.getXBombs(2), bombs.getYBombs(2));
            g.drawImage(bombtarget, bombs.getXBombs(3), bombs.getYBombs(3));
            g.drawImage(bombfire, bombs.getXBombs(4), bombs.getYBombs(4));
            g.drawImage(bombtarget, bombs.getXBombs(5), bombs.getYBombs(5));
            g.drawImage(bombfire, bombs.getXBombs(6), bombs.getYBombs(6));
            g.drawImage(lasericon, laser.getX(), laser.getY());
            g.drawImage(scissoricon, steal.getX(), steal.getY());
            g.drawImage(switchericon, switcher.getX(), switcher.getY());

            //if laser's private boolean 'lethal' is not true, draw the laser sight for it
            if (!laser.getLethal()) {
                g.drawImage(lasersightH, laser.getX3(), laser.getY3());
                g.drawImage(lasersightV, laser.getX2(), laser.getY2());

                //if lethal is true, draw the laser itself
            } else {
                g.drawImage(laserH, laser.getX3(), laser.getY3());
                g.drawImage(laserV, laser.getX2(), laser.getY2());
            }

            g.drawImage(wormImage, worm.getX(), worm.getY());

            if (!gameMode.equals("sp")) {
                g.drawImage(worm2Image, worm2.getX(), worm2.getY());
            }
            if (wormskinactive) {
                g.drawImage(wormskin, worm.getX() - 5, worm.getY() - 15);
            }
            if (worm2skinactive) {
                g.drawImage(worm2skin, worm2.getX() - 5, worm2.getY() - 15);
            }
            if (worm.getShield()) {
                g.drawImage(shieldeffect, worm.getX() - 5, worm.getY() - 4);
            }
            if (worm2.getShield()) {
                g.drawImage(shieldeffect, worm2.getX() - 5, worm2.getY() - 4);
            }
            if (worm.getReverse()) {
                g.drawImage(confuseEffect, worm.getX() - 5, worm.getY() - 4);
            }
            if (worm2.getReverse()) {
                g.drawImage(confuseEffect, worm2.getX() - 5, worm2.getY() - 4);
            }
            drawPoints(g);


        } else {
            if (worm.getLife() < worm2.getLife() && !gameMode.equals("sp")) {
                score = worm2.getPoints();
                winner_label.setText("BLUE WON");
                winner_label.setTextFill(Color.BLUE);
            } else if (worm.getLife() > worm2.getLife() && !gameMode.equals("sp")) {
                score = worm.getPoints();
                winner_label.setText("RED WON");
                winner_label.setTextFill(Color.RED);
            } else {
                score = worm.getPoints();
            }
            winner_label.setId("winner_label");
            Music.backgroundMusic.stop();
            Music.death.play();
            createGameOverScene();
            createHighscoreScene();
            window.setScene(gameOverScene);
            timer.stop();
            reset();
        }
    }

    private void drawPoints(GraphicsContext g) {
        g.setFont(Font.font("Helvetica", FontWeight.BOLD, 22));
        g.setFill(Color.RED);
        g.setStroke(Color.BLACK);
        String hp = "HP: " + worm.getLife();
        String pt = "Pisteet: " + worm.getPoints();
        g.fillText(hp, 50, 30);
        g.fillText(pt, 50, 60);

        if (!gameMode.equals("sp")) {
            g.setFill(Color.BLUE);
            String hp2 = "HP: " + worm2.getLife();
            String pt2 = "Pisteet: " + worm2.getPoints();
            g.fillText(hp2, 650, 30);
            g.fillText(pt2, 650, 60);
        }

        //------------------FPS counter-------------------
        currentTime = System.currentTimeMillis();
        //delta time is difference between current and previous time divided by 1k
        double deltaTime = (double) (currentTime - previousTime) / 1_000;
        // 1/deltaTime; <- tells the current fps

        //interval tells how often fps should be drawn; currently at 0.5 seconds
        double interval = 0.5;

        //if current time is greater  than the interval
        if (timeCounter > interval) {
            //reset the counters, save current frame counts on the one that's shown to the user
            theRealFpsCounter = frameCounter;
            frameCounter = 0;
            timeCounter = 0;
        } else {
            //if current time is less than interval, add difference between times to the time counter
            timeCounter += deltaTime;
            //add  1/interval to the current frame counter
            frameCounter += (int) (1 / interval);
        }
        //save previous time as current time so when this is called again, it can use the new values
        previousTime = currentTime;

        //draw the fps on board on top of everything else (drawn last)
        String FPS = "FPS: " + theRealFpsCounter;
        g.setFill(Color.WHITE);
        g.setFont(Font.font(15));
        g.fillText(FPS, 400, 30);
    }

    private void reset() {

        Music.backgroundMusic.loop();
        board.setIngame(true);
        hs_submitted = false;

        worms.clear();
        body.clear();
        body2.clear();
        powerups.clear();

        //reset the tailsizes to be 0 for no conflicts
        tailsize = 0;
        tailsize2 = 0;

        board = new Board(this, gameMode);
        pc = new PlayerController(board, gameMode);
        powerups = board.getPickableList();
        snack = (Snack) powerups.get(0);
        faster = (Faster) powerups.get(1);
        slower = (Slower) powerups.get(2);
        confuse = (Confuse) powerups.get(3);
        life = (Life) powerups.get(4);
        shield = (Shield) powerups.get(5);
        bombs = (Bombs) powerups.get(6);
        laser = (Laser) powerups.get(7);
        steal = (Steal) powerups.get(8);
        switcher = (Switcher) powerups.get(9);
        worms.add(worm = board.getWorm());
        worms.add(worm2 = board.getWorm2());
    }

    private void createGameOverScene() {
        VBox gameOverLayout = new VBox(20);
        gameOverLayout.setId("gameOver");
        gameOverLayout.setAlignment(Pos.CENTER);

        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setId("gameOverLabel");

        Button restart = new Button("Restart");
        Button backToSS = new Button("Main menu");
        highscore_button = new Button("Submit\nhighscore");
        restart.setOnAction(e -> {
            reset();
            window.setScene(gameScene);
            timer.start();
        });

        backToSS.setOnAction(e -> window.setScene(mainMenuScene));

        highscore_button.setOnAction(e -> {
            if (!hs_submitted && board.getConnection() != null) {
                window.setScene(highscoreScene);
                scenetitle.setText("You got " + score + " points ");
            } else if (hs_submitted && board.getConnection() != null) {
                window.setScene(highscoreTableScene);
            } else {
                createConnectionAlert().showAndWait();
            }
        });

        gameOverLayout.getChildren().addAll(gameOverLabel, winner_label, restart, backToSS, highscore_button);
        gameOverScene = new Scene(gameOverLayout, width, height);
        gameOverScene.getStylesheets().add("Styling/styling.css");
    }

    private void createHighscoreScene() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        scenetitle.setId("highscore_title");
        scenetitle.setFill(Color.WHITE);
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Username: ");
        Label errorMessage = new Label();
        errorMessage.setId("errormessage");
        userName.setTextFill(Color.WHITE);
        userName.setId("username");
        grid.add(userName, 0, 10);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 10);
        grid.add(errorMessage, 0, 11);

        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        cancel.setOnAction(e -> window.setScene(gameOverScene));
        submit.setOnAction(e -> {
            if ((userTextField.getText() != null && !userTextField.getText().isEmpty())) {
                if (userTextField.getText().length() > 10) {
                    errorMessage.setText("Username should be\n1-10 characters");
                } else {
                    highscore_button.setText("Show\nhighscores");
                    hs_submitted = true;
                    username = userTextField.getText();
                    board.submitHighscore(score, username);
                    window.setScene(highscoreTableScene);
                }
            } else {
                errorMessage.setText("Please enter\nyour username");
            }
        });
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(submit, cancel);
        grid.add(hbBtn, 1, 20);

        highscoreScene = new Scene(grid, width, height);
        grid.setId("highscore_scene");
        highscoreScene.getStylesheets().add("Styling/styling.css");
    }

    /**
     * Creates the Top 10 highscores String from ArrayList: scorelist.
     * Creates the highscore table scene using GridPane as base.
     *
     * @param scorelist ArrayList where highscore values and names are stored in String format.
     */
    public void createHighscoreTableScene(ArrayList<String> scorelist) {
        //searches for match from the arraylist where username=username_given and points = score_given and gets the index of where the match was found.
        int indexOf = scorelist.indexOf(username + " " + score) + 1;
        String highscore = "";
        //adds top10 names and highscores to the String
        int i = 0;
        for (String s : scorelist) {
            i++;
            if (i <= 10) {
                highscore += +(i) + ". " + s + '\n';
            }
        }
        GridPane grid = new GridPane();
        grid.setId("highscoretable_scene");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        VBox vbox = new VBox(40);
        vbox.setAlignment(Pos.CENTER);

        Text headline = new Text("You placed: #" + indexOf + " with " + score + " points!\n\n\nTop 10");
        headline.setFill(Color.WHITE);
        headline.setId("highscoretable_headline");
        Label highscores = new Label(highscore);
        highscores.setTextFill(Color.WHITE);

        highscores.setId("top_10_highscores");
        Button okbutton = new Button("OK");
        okbutton.setOnAction(e -> {
            window.setScene(gameOverScene);
            highscore_button.setText("Show\nhighscore");
        });

        highscores.setId("highscoretable_highscores");
        okbutton.setOnAction(e -> window.setScene(gameOverScene));
        vbox.getChildren().addAll(headline, highscores, okbutton);
        grid.getChildren().addAll(vbox);

        highscoreTableScene = new Scene(grid, width, height);
        highscoreTableScene.getStylesheets().add("Styling/styling.css");
    }

    private Alert createConnectionAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Sorry! We couldn't establish connection to the database.");
        alert.setContentText("SQL Non Transient Connection Exception");

        Exception ex = new SQLNonTransientConnectionException("Could not connect to address=(host=localhost)(port=4444)(type=master) : Connection refused: connect");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        return alert;
    }

    private Alert createConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
        return alert;
    }

    private void navigateInfo(String value) {
        if (value.equals("+")) {
            if (infoindex < infoimg.size() - 1) {
                infoindex++;
            } else {
                infoindex = 0;
            }
            infoimv.setImage(infoimg.get(infoindex));
        }
        if (value.equals("-")) {
            if (infoindex > 0 && infoindex <= infoimg.size() - 1) {
                infoindex--;
            } else {
                infoindex = 0;
            }
            infoimv.setImage(infoimg.get(infoindex));
        }
    }
}