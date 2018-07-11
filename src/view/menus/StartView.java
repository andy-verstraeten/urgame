package view.menus;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presenter.menus.StartPresenter;
import view.menus.components.MenuButton;

public class StartView extends MenuView {

    private final Image UR_IMG = new Image("gfx/menus/URLogo.png");
    private StartPresenter presenter;


    private ImageView logoView;
    private MenuButton startButton;
    private MenuButton scoreButton;
    private MenuButton helpButton;
    private MenuButton exitButton;

    public StartView() {
        this.presenter = new StartPresenter(this);
        initialiseNodes();
        layoutNodes();
        initialiseEventHandling();
    }

    @Override
    public void initialiseNodes() {
        logoView = new ImageView(UR_IMG);
        startButton = new MenuButton("Start");
        helpButton = new MenuButton("Help");
        scoreButton = new MenuButton("Top scores");
        exitButton = new MenuButton("Exit");
    }

    @Override
    public void layoutNodes() {
        this.addToCenterVBox(logoView);
        this.addToCenterVBox(startButton);
        this.addToCenterVBox(helpButton);
        this.addToCenterVBox(scoreButton);
        this.addToCenterVBox(exitButton);
    }


    public void initialiseEventHandling() {
        startButton.setOnAction(event -> presenter.startGame());
        helpButton.setOnAction(event -> presenter.goHelp());
        scoreButton.setOnAction(event -> presenter.goToScores());
        exitButton.setOnAction(event -> presenter.exitGame());
    }

}
