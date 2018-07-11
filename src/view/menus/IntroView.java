package view.menus;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import presenter.menus.IntroPresenter;
import utilities.CssManager;
import view.menus.components.MenuButton;


import java.util.Random;

public class IntroView extends Region {
    private final Image ROSETTA_BG = new Image("file:gfx\\game\\rosetta.png");
    private final Image BLANK_BG =  new Image("file:gfx\\game\\blankSquare.png");
    private final Image BACKGROUND = new Image("file:gfx\\menus\\introBG.png");
    private final Image UR_IMG = new Image("file:gfx/menus/URLogo.png");

    private IntroPresenter presenter;

    private ImageView logoView;
    private Label creatorLabel;
    private ImageView[][] blockViews;
    private Random rand = new Random();
    private MenuButton startButton;
    private BackgroundImage bgImage;

    public IntroView() {
        this.presenter = new IntroPresenter(this);
        initialiseNodes();
        layoutNodes();
        initialiseEventHanding();
    }

    public void initialiseNodes() {
        bgImage = new BackgroundImage(BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        creatorLabel = new Label("Created by Andy Verstraeten");
        this.setBackground(new Background(bgImage));
        startButton = new MenuButton("Start");

        int horizontalReps = (int) Math.round(600/ ROSETTA_BG.getWidth());
        int verticalReps = (int) Math.round(600/ ROSETTA_BG.getHeight());
        logoView = new ImageView(UR_IMG);

        blockViews = new ImageView[horizontalReps][verticalReps];
        for(int x = 0; x < horizontalReps; x++) {
            for(int y=0; y< verticalReps; y++) {
                blockViews[x][y]= new ImageView(rand.nextBoolean()?ROSETTA_BG:BLANK_BG);
            }
        }

    }

    public void layoutNodes() {
        for(int x = 0; x < blockViews.length; x++) {
            for(int y=0; y< blockViews[x].length; y++) {
                blockViews[x][y].setX(x* ROSETTA_BG.getWidth());
                blockViews[x][y].setY(y* ROSETTA_BG.getHeight());
                this.getChildren().add(blockViews[x][y]);
            }
        }
        startButton.setLayoutX(200);
        startButton.setLayoutY(400);
        this.getChildren().add(startButton);
        logoView.setLayoutX(163);
        logoView.setLayoutY(100);
        this.getChildren().add(logoView);
        creatorLabel.setStyle(CssManager.titleTextStyle+CssManager.whiteText);
        creatorLabel.setAlignment(Pos.CENTER);
        this.getChildren().add(creatorLabel);
        creatorLabel.setLayoutX(120);
        creatorLabel.setLayoutY(300);
    }

    public void initialiseEventHanding() {
        startButton.setOnAction(event -> presenter.startClick());
    }

    public void animate() {
        for(int x = 0; x < blockViews.length; x++) {
            for(int y=0; y< blockViews[x].length; y++) {
                setupRotation(blockViews[x][y],x, y).play();
                setupFalling(blockViews[x][y], x,y).play();
                setupVertical(blockViews[x][y], x, y).play();
            }
        }
    }

    private TranslateTransition setupVertical(Node node, int x, int y) {
        TranslateTransition vertTransition = new TranslateTransition();
        vertTransition.setNode(node);
        vertTransition.setDelay(Duration.seconds(.05*x+.3*y));
        vertTransition.setFromX(rand.nextBoolean()?90:-90);
        vertTransition.setDuration(Duration.seconds(4));
        vertTransition.setCycleCount(1);
        return vertTransition;

    }

    private TranslateTransition setupFalling(Node node, int x, int y) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(node);
        translateTransition.setDelay(Duration.seconds(.05*x+.3*y));
        translateTransition.setByY(650);
        translateTransition.setDuration(Duration.seconds(4));
        translateTransition.setCycleCount(1);
        translateTransition.setInterpolator(Interpolator.EASE_IN);
        translateTransition.setOnFinished(event -> presenter.goMainMenu());
        return translateTransition;
    }



    private RotateTransition setupRotation(Node node, int x, int y) {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(node);
        rotateTransition.setDelay(Duration.seconds(.05*x+.3*y));
        rotateTransition.setByAngle(rand.nextInt(90)-180);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);

        return rotateTransition;
    }

    public void hideButton() {
        startButton.setVisible(false);
    }
}