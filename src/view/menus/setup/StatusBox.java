package view.menus.setup;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.CssManager;

public class StatusBox extends HBox {
    private final Image STATUS_INCOMPLETE = new Image("file:gfx\\menus\\statusIncomplete.png");
    private final Image STATUS_COMPLETE = new Image("file:gfx\\menus\\statusComplete.png");

    private VBox rulesBox;
    private Label rulesLabel;
    private ImageView rulesStatus;
    private VBox playerBox;
    private Label playerLabel;
    private ImageView playerStatus;
    private VBox gameBox;
    private Label gameLabel;
    private ImageView gameStatus;

    StatusBox() {
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes() {
        rulesBox = new VBox();
        rulesLabel = new Label("Select path");
        rulesLabel.setStyle(CssManager.smallBoldTextStyle);
        rulesStatus = new ImageView(STATUS_COMPLETE);
        playerBox = new VBox();
        playerLabel = new Label("Player info");
        playerLabel.setStyle(CssManager.smallTextStyle);
        playerStatus = new ImageView(STATUS_INCOMPLETE);
        gameBox = new VBox();
        gameLabel = new Label("Play game");
        gameLabel.setStyle(CssManager.smallTextStyle);
        gameStatus = new ImageView(STATUS_INCOMPLETE);
    }

    public void layoutNodes() {
        rulesBox.getChildren().addAll(rulesLabel, rulesStatus);
        playerBox.getChildren().addAll(playerLabel, playerStatus);
        gameBox.getChildren().addAll(gameLabel, gameStatus);
        this.getChildren().addAll(rulesBox, playerBox, gameBox);
        this.setAlignment(Pos.CENTER);
    }

    public void stage2() {
        rulesLabel.setStyle(CssManager.smallTextStyle);
        playerLabel.setStyle(CssManager.smallBoldTextStyle);
        playerStatus.setImage(STATUS_COMPLETE);
    }

}
