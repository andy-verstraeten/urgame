package presenter.menus;

import model.path.PathType;
import model.scores.Score;
import view.game.GameView;
import view.menus.*;
import view.menus.setup.RulesSelectView;

public class EndPresenter extends MenuPresenter {
    private EndView view;
    private String winnerName;
    private String loserName;
    private Score model;

    public EndPresenter(EndView view, String winnerName, String loserName, int moves, PathType pathType) {
        super(view);
        this.view=view;
        this.winnerName = winnerName;
        this.loserName = loserName;
        model = new Score(winnerName, moves, loserName, pathType);
    }

    public Score getModel() {
        return model;
    }

    public void replay() {
        view.getScene().setRoot(new GameView(model.getPlayerName(), model.getLoserName(),model.getPathType()));
    }

    public void newGame() {
        view.getScene().setRoot(new RulesSelectView());
    }

    public void mainMenu() {
        view.getScene().setRoot(new StartView());
    }
}
