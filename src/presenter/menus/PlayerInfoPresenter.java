package presenter.menus;

import model.path.PathType;
import view.game.GameView;
import view.menus.setup.PlayerInfoView;

public class PlayerInfoPresenter extends MenuPresenter  {

    private PlayerInfoView view;

    private PathType pathType;

    public PlayerInfoPresenter(PlayerInfoView view, PathType pathType) {
        super(view);
        this.view = view;
        this.pathType = pathType;
    }

    public void goNext(String playerAName, String playerBName) {
        if(playerAName.length() == 0)
            view.highlightPlayerAName();
        else if(playerBName.length() == 0)
            view.highlightPlayerBName();
        else {
            GameView gameView = new GameView(playerAName, playerBName, pathType);
            view.getScene().setRoot(gameView);
        }
    }

    public void checkInputA(String text) {
        if(text!= "")
            view.unHighlightPlayerAName();
    }

    public void checkInputB(String text) {
        if(text!= "")
            view.unHighlightPlayerBName();
    }

}
