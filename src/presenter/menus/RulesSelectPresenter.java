package presenter.menus;

import model.path.PathType;
import view.menus.setup.PlayerInfoView;
import view.menus.setup.RulesSelectView;

public class RulesSelectPresenter extends MenuPresenter {
    private RulesSelectView view;

    public RulesSelectPresenter(RulesSelectView view) {
        super(view);
        this.view = view;
    }

    public void standardClick() {
        view.getScene().setRoot(new PlayerInfoView(PathType.STANDARD));
    }

    public void murrayClick() {
        view.getScene().setRoot(new PlayerInfoView(PathType.MURRAY));
    }

    public void masterClick() {
        view.getScene().setRoot(new PlayerInfoView(PathType.MASTER));
    }
}
