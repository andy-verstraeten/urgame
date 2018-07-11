package view.menus;

import javafx.scene.control.Label;
import model.path.PathType;
import presenter.menus.EndPresenter;
import utilities.CssManager;
import view.menus.components.MenuButton;

public class EndView extends MenuView{

    private EndPresenter presenter;


    private Label resultLabel;
    private MenuButton replayButton;
    private MenuButton newGameButton;
    private MenuButton mainMenuButton;

    public EndView(String winnerName, String loserName, int moves, PathType pathType) {
        presenter = new EndPresenter(this, winnerName, loserName, moves, pathType);

        initialiseNodes();
        layoutNodes();
        initialiseEventHandling();
    }

    public void initialiseNodes() {
        resultLabel = new Label(String.format("%s beat %s in %d moves!", presenter.getModel().getPlayerName(),
                presenter.getModel().getLoserName(), presenter.getModel().getMoves()));
        replayButton = new MenuButton("Replay");
        newGameButton = new MenuButton("New Game");
        mainMenuButton = new MenuButton("Main menu");
    }
    public void layoutNodes() {
        resultLabel.setStyle(CssManager.titleTextStyle);
        this.addToCenterVBox(resultLabel);
        this.addToCenterVBox(replayButton);
        this.addToCenterVBox(newGameButton);
        this.addToCenterVBox(mainMenuButton);
    }
    public void initialiseEventHandling() {
        replayButton.setOnAction(event -> presenter.replay());
        newGameButton.setOnAction(event -> presenter.newGame());
        mainMenuButton.setOnAction(event -> presenter.mainMenu() );
    }
}
