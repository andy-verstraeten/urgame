package view.game.gameNodes;

import javafx.scene.layout.GridPane;

import presenter.game.GamePresenter;
import view.game.GameViewElement;
import utilities.CssManager;

public class BoardPane extends GridPane implements GameViewElement {
    private GamePresenter presenter;
    private BoardSquarePane[][] squares;

    public BoardPane(GamePresenter presenter) {
        this.setStyle(CssManager.basicStyle);
        this.presenter = presenter;
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes() {
        int boardHeight = this.presenter.getGame().getBoard().getSquares().length;
        int boardWidth = this.presenter.getGame().getBoard().getSquares()[0].length;
        squares = new BoardSquarePane[boardHeight][boardWidth];
        for(int y = 0; y< boardHeight;  y++) {
            for(int x = 0; x < boardWidth; x++ ) {
                squares[y][x] = new BoardSquarePane(presenter, x, y);
            }
        }
    }

    public void layoutNodes() {
        for(int y = 0; y < squares.length; y++) {
            for(int x = 0; x < squares[y].length; x++) {
                this.add(squares[y][x], x, y);
            }
        }
    }

    public void update() {
        for(int y = 0; y < squares.length; y++) {
            for(int x = 0; x < squares[y].length; x++) {
                squares[y][x].update();
            }
        }
    }
}
