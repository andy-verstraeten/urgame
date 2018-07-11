package view.game.gameNodes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presenter.game.GamePresenter;
import utilities.CssManager;

public class BackTrackButton extends Button {

    private final Image ICON_IMG = new Image("file:gfx\\game\\backTrack.png");
    private GamePresenter presenter;

    public BackTrackButton(GamePresenter presenter) {
        this.setStyle(CssManager.gameButtonStyle);
        this.setGraphic(new ImageView(ICON_IMG));
        this.setMaxSize(70,70);
        this.setOnAction(event -> presenter.backTrack() );
    }

}
