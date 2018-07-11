package view.menus;


import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.scores.Score;
import presenter.menus.ScorePresenter;
import utilities.CssManager;
import utilities.ScoreFileHandler;
import view.menus.components.MenuButton;

import java.io.IOException;
import java.util.ArrayList;

public class ScoreView extends MenuView {

    private ScorePresenter presenter;
    private Label titleLabel;
    private ArrayList<Label> scoreLabels;
    private MenuButton backButton;

    public ScoreView() {
         presenter = new ScorePresenter(this);
         scoreLabels = new ArrayList<Label>();
         initialiseNodes();
         layoutNodes();
         initialiseEventHandling();
    }

    public void initialiseNodes() {
        titleLabel = new Label("Top Scores");
        try {
            for (Score score : ScoreFileHandler.getScoreList()) {
                scoreLabels.add(new Label(String.format("%s - %d moves", score.getPlayerName(), score.getMoves())));
            }
        }
        catch(IOException ioE) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(ioE.getMessage());
            alert.showAndWait();
        }
        backButton = new MenuButton("Back");
    }
    public void layoutNodes() {
        titleLabel.setStyle(CssManager.titleTextStyle);
        this.addToCenterVBox(titleLabel);
        for(Label scoreLabel: scoreLabels) {
            scoreLabel.setStyle(CssManager.basicTextStyle);
            this.addToCenterVBox(scoreLabel);
        }
        this.addToCenterVBox(backButton);
    }
    public void initialiseEventHandling() {
        backButton.setOnAction(event -> presenter.goMainMenu());
    }
}
