package view.game;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Game;
import model.path.PathType;
import presenter.game.GamePresenter;
import view.game.gameNodes.*;
import utilities.CssManager;



public class GameView extends BorderPane implements GameViewElement {
    private GamePresenter presenter;

    private MenuBar menuBar;
    private Menu gameMenu;
    private MenuItem mainMenuItem;
    private MenuItem helpMenuItem;
    private MenuItem exitMenuItem;

    private ScoreBoardPane scoreBoardPane;
    private BoardPane boardPane;
    private DicePane dicePane;
    private FreePiecesPane freePiecesPane;
    private BackTrackButton backTrackButton;
    private VBox centerBox;

    

    public GameView(String playerAName, String playerBName, PathType pathType) {
        this.setStyle(CssManager.gameBGStyle);
        this.presenter = new GamePresenter(this, pathType);
        initialiseNodes();
        layoutNodes();
        this.initialiseEventHandling();
        this.presenter.startNewGame(playerAName, playerBName);

    }

    public void initialiseNodes() {
        //initialise menu
        menuBar = new MenuBar();
        gameMenu = new Menu("Game");
        mainMenuItem = new MenuItem("Main menu");
        helpMenuItem = new MenuItem("Help menu");
        exitMenuItem = new MenuItem("Exit");

        centerBox = new VBox(5);
        scoreBoardPane = new ScoreBoardPane(presenter);
        boardPane = new BoardPane(presenter);
        dicePane = new DicePane(presenter);
        freePiecesPane = new FreePiecesPane(presenter);
        backTrackButton = new BackTrackButton(presenter);
    }

    public void layoutNodes() {
        this.setTop(menuBar);
        gameMenu.getItems().addAll(mainMenuItem, helpMenuItem, exitMenuItem);
        menuBar.getMenus().add(gameMenu);
        //this.setTop(scoreBoardPane);
        centerBox.setPadding(new Insets(10,10,10,10));
        centerBox.getChildren().setAll(scoreBoardPane,boardPane, backTrackButton);
        this.setCenter(centerBox);
        this.setBottom(dicePane);

        this.setRight(freePiecesPane);
    }

    public void update() {
        scoreBoardPane.update();
        boardPane.update();
        dicePane.update();
        freePiecesPane.update();

    }

    public void gameAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void initialiseEventHandling() {
        this.setOnKeyPressed(event -> presenter.handleKeyPressed(event));
        mainMenuItem.setOnAction(event -> presenter.goMainMenu());
        helpMenuItem.setOnAction(event -> presenter.launchHelp());
        exitMenuItem.setOnAction(event -> presenter.exitApp());
    }
}
