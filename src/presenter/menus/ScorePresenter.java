package presenter.menus;

import view.menus.ScoreView;
import view.menus.StartView;

public class ScorePresenter extends MenuPresenter {
    private ScoreView view;

    public ScorePresenter(ScoreView view) {
        super(view);
        this.view = view;
    }

    public void goMainMenu() {
        view.getScene().setRoot(new StartView());
    }
}
