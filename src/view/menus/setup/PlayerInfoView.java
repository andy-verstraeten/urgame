package view.menus.setup;

import javafx.scene.control.TextField;
import model.path.PathType;
import presenter.menus.PlayerInfoPresenter;
import utilities.CssManager;
import view.menus.MenuView;
import view.menus.components.BackButton;
import view.menus.components.MenuButton;

public class PlayerInfoView extends MenuView {
    private PlayerInfoPresenter presenter;


    private StatusBox statusBox;
    private TextField playerAName;
    private TextField playerBName;
    private MenuButton nextButton;
    private BackButton backButton;


    public PlayerInfoView(PathType pathType) {
        this.presenter = new PlayerInfoPresenter(this, pathType);
        initialiseNodes();
        layoutNodes();
        initialiseEventHandling();
    }

    @Override
    public void initialiseNodes() {
        statusBox = new StatusBox();
        playerAName = new TextField();
        playerAName.setPromptText("First player's name");
        playerBName = new TextField();
        playerBName.setPromptText("Second player's name");
        nextButton = new MenuButton("Next");
        backButton = new BackButton(presenter);
    }

    @Override
    public void layoutNodes() {
        statusBox.stage2();
        playerAName.setMaxWidth(150);
        playerBName.setMaxWidth(150);
        //initiele focus verwijderen
        playerAName.setFocusTraversable(false);
        playerBName.setFocusTraversable(false);
        this.addToCenterVBox(statusBox);
        this.addToCenterVBox(playerAName);
        this.addToCenterVBox(playerBName);
        this.addToCenterVBox(nextButton);
        this.addToCenterVBox(backButton);
    }


    public void initialiseEventHandling() {
        nextButton.setOnAction(event -> presenter.goNext(playerAName.getText(), playerBName.getText()));
        playerAName.setOnKeyReleased(event -> presenter.checkInputA(playerAName.getText()));
        playerBName.setOnKeyReleased(event -> presenter.checkInputB(playerBName.getText()));
    }

    public void highlightPlayerAName() {
        playerAName.setStyle(CssManager.requiredFieldStyle);
    }

    public void unHighlightPlayerAName() {
        playerAName.setStyle(CssManager.removeRequiredFieldStyle);
    }

    public void highlightPlayerBName() {

        playerBName.setStyle(CssManager.requiredFieldStyle);

    }

    public void unHighlightPlayerBName()
    {
        playerBName.setStyle(CssManager.removeRequiredFieldStyle);
    }

}
