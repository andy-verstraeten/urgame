package view.menus.setup;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presenter.menus.RulesSelectPresenter;
import utilities.CssManager;
import view.menus.MenuView;
import view.menus.components.BackButton;
import view.menus.components.MenuButton;

public class RulesSelectView extends MenuView {
    private final Image STANDARD_IMG = new Image("file:gfx\\menus\\classicPath.png");
    private final Image MASTER_IMG = new Image("file:gfx\\menus\\masterPath.png");
    private final Image MURRAY_IMG = new Image("file:gfx\\menus\\murrayPath.png");


    private RulesSelectPresenter presenter;
    private StatusBox statusBox;

    private MenuButton standardButton;
    private MenuButton murrayButton;
    private MenuButton masterButton;
    private BackButton backButton;

    public RulesSelectView() {
        presenter = new RulesSelectPresenter(this);
        initialiseNodes();
        layoutNodes();
        initialiseEventHandling();
    }

    public void initialiseNodes() {
        statusBox = new StatusBox();


        standardButton = new MenuButton();
        murrayButton = new MenuButton();
        masterButton = new MenuButton();
        backButton = new BackButton(presenter);
    }

    public void layoutNodes() {

        this.addToCenterVBox(statusBox);
        standardButton.setGraphic(new ImageView(STANDARD_IMG));
        this.addToCenterVBox(standardButton);
        murrayButton.setGraphic(new ImageView(MURRAY_IMG));
        this.addToCenterVBox(murrayButton);
        masterButton.setGraphic(new ImageView(MASTER_IMG));
        this.addToCenterVBox(masterButton);
        this.addToCenterVBox(backButton);
    }

    public void initialiseEventHandling() {
        standardButton.setOnAction(event -> presenter.standardClick());
        murrayButton.setOnAction(event -> presenter.murrayClick());
        masterButton.setOnAction(event -> presenter.masterClick());
    }
}
