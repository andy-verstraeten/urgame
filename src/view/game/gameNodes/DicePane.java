package view.game.gameNodes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import presenter.game.GamePresenter;
import view.game.GameViewElement;
import utilities.CssManager;


public class DicePane extends GridPane implements GameViewElement {

    private final Image DICE_UP = new Image("file:gfx\\game\\diceUp.png");
    private final Image DICE_DOWN = new Image("file:gfx\\game\\diceDown.png");

    private GamePresenter presenter;
    private Label diceTitel;
    private ImageView[] diceImages;

    public DicePane(GamePresenter presenter) {
        this.setPadding(new Insets(10));
        this.setStyle(CssManager.basicStyle);
        this.presenter = presenter;
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes() {
        diceTitel = new Label("Dice:");
        diceTitel.setStyle("-fx-font-size: 22; -fx-text-fill: white;");
        diceImages = new ImageView[3];
        for(int i=0; i < diceImages.length; i++) {
            diceImages[i] = new ImageView(DICE_DOWN);
        }
    }

    public void layoutNodes() {
        this.add(diceTitel, 0,0);
        for(int i=0; i < diceImages.length; i++) {
            this.add(diceImages[i], i , 1);
        }
    }
    public void update() {
        boolean[] dicesState = presenter.getGame().getDices().getUpSides();
        for(int i = 0; i < dicesState.length; i++) {
            if(dicesState[i])
                diceImages[i].setImage(DICE_UP);
            else
                diceImages[i].setImage(DICE_DOWN);
        }
    }
}
