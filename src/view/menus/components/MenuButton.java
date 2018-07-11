package view.menus.components;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import utilities.CssManager;

public class MenuButton extends Button {

    public MenuButton () {
        this.setStyle(CssManager.buttonStyle);
    }

    public MenuButton(String text) {
        super(text);

        this.setStyle(CssManager.buttonStyle);

    }
}
