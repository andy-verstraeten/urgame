package view.game.gameNodes;

import com.sun.javafx.css.Size;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import model.player.Player;
import presenter.game.GamePresenter;
import view.game.GameViewElement;
import utilities.CssManager;

public class ScoreBoardPane extends VBox implements GameViewElement {
    private Label playerAScore;
    private Label playerBScore;
    private GamePresenter presenter;

    public ScoreBoardPane(GamePresenter presenter) {
        this.setStyle("-fx-background-image: url('/gfx/game/papyrusBG.png');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-max-width: 400;" +
                "-fx-max-height: 150;" +
                "-fx-min-width: 400;" +
                "-fx-min-height: 150");

        this.presenter = presenter;
        initializeNodes();
        layoutNodes();
        update();
    }

    public void initializeNodes() {
        playerAScore = new Label();
        playerBScore = new Label();
    }

    public void layoutNodes() {
        this.setPadding(new Insets(20,20,20,20));
        playerAScore.setStyle(CssManager.basicTextStyle);
        playerBScore.setStyle(CssManager.basicTextStyle);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(playerAScore, playerBScore);
    }

    public String makeScoreText(Player player) {
        return String.format("%s's score : %d (%d moves)", player.getName(),player.getScore(), player.getMoves());
    }

    @Override
    public void update() {
        playerAScore.setText(makeScoreText(this.presenter.getGame().getPlayerA()));
        playerBScore.setText(makeScoreText(this.presenter.getGame().getPlayerB()));

        if(this.presenter.getGame().getCurrentPlayer()== this.presenter.getGame().getPlayerA()) {
            playerAScore.setStyle(CssManager.boldTextStyle);
            playerBScore.setStyle(CssManager.normalTextStyle);
        }
        if(this.presenter.getGame().getCurrentPlayer()== this.presenter.getGame().getPlayerB()) {
            playerBScore.setStyle(CssManager.boldTextStyle);
            playerAScore.setStyle(CssManager.normalTextStyle);
        }
    }
}
