package presenter.menus;

import view.menus.MenuView;
import view.menus.StartView;

public class MenuPresenter  {
    protected MenuView view;

    public MenuPresenter(MenuView view) {
        this.view = view;
    }

    public void goMainMenu() {
        view.getScene().setRoot(new StartView());
    }
}
