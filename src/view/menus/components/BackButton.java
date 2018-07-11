package view.menus.components;

import presenter.menus.MenuPresenter;

public class BackButton extends MenuButton {

    public BackButton(MenuPresenter presenter) {
        super("Main menu");
        setOnAction(event -> presenter.goMainMenu());
    }


}
