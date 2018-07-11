package presenter.game;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Game;
import model.path.PathType;
import model.player.MoveException;
import model.scores.Score;
import utilities.ScoreFileHandler;
import view.HelpView;
import view.game.GameView;
import view.menus.EndView;
import view.menus.StartView;


import java.io.IOException;

public class GamePresenter {
    private Game game;
    private GameView view;
    private MoveManager moveManager;


    private boolean backTracked = false;



    public GamePresenter(GameView view, PathType pathType) {
        this.game = new Game(pathType);
        this.view = view;
        this.moveManager = new MoveManager(game);
    }

    public Game getGame() {
        return game;
    }

    public void startNewGame(String playerAName, String playerBName) {
        game.setStartValues(playerAName, playerBName);

        startTurn();
    }

    public void startTurn() {
        game.getDices().rollDice();
        view.update();
    }

    public void newPieceClicked() {
        try {
            putNewPieceOnBoard(game.getDices().getTurns());
            game.getCurrentPlayer().incrementMoves();
            backTracked = false;
            game.changeCurrentPlayer();
            startTurn();
        } catch(MoveException mE) {
             view.gameAlert(mE.getMessage());
        }
    }

    public void backTrack() {
        if(game.getOtherPlayer().getMoves()!=0 || game.getCurrentPlayer().getMoves()!=0) {
            if (!backTracked)
                rewind();
            else
                forward();
        }

    }

    public void forward() {
        backTracked=false;
        move(game.getLastMove().getPiece(), game.getLastMove().getMoves());
        if(game.getLastMove().getKilledPieceIndex()!=-1) {
            game.getEnemyPlayer().getPieceManager().setPieceToPos(
                    game.getLastMove().getKilledPieceIndex(),
                    game.getLastMove().getKilledPiecePosition());
        }
        game.changeCurrentPlayer();
        view.update();
    }

    public void rewind() {
        game.changeCurrentPlayer();
        move(game.getLastMove().getPiece(), -game.getLastMove().getMoves());
        //game.setDices(game.getLastMove().getDices());
        view.update();
        backTracked = true;
    }

    public void squareClicked(int[] coords) {

        if(game.getBoard().getPieceOnTypeByCoords(coords)== game.getCurrentPlayer().getPieceManager().getPieceType()) {
            int index = game.getCurrentPlayer().getPieceManager().getPieceIndexByCoords(coords);
            if(game.getCurrentPlayer().getPieceManager().getPiecesOnBoard().contains(index)) {
                try {
                    game.getCurrentPlayer().incrementMoves();
                    move(index, game.getDices().getTurns());
                    //END OF GAME
                    if(game.getCurrentPlayer().numberOfPiecesFinished() == 1 )
                        endOfGame();
                    game.changeCurrentPlayer();
                    startTurn();

                }
                catch (MoveException mE) {
                    view.gameAlert(mE.getMessage());
                }
                backTracked = false;
        }
        }

    }

    public void endOfGame() {
        String winnerName = game.getCurrentPlayer().getName();
        int moves = game.getCurrentPlayer().getMoves();
        game.changeCurrentPlayer();
        String loserName = game.getCurrentPlayer().getName();

        try {
            ScoreFileHandler.saveScore(new Score(winnerName, moves, game.getCurrentPlayer().getPathType()));
        }
        catch(IOException ioE) {
            view.gameAlert(ioE.getMessage());
        }
        view.getScene().setRoot(new EndView(winnerName, loserName, moves, game.getCurrentPlayer().getPathType()));
    }

    //Verplaatst pion als het kan volgens de regels
    public void move(int pieceIndex, int moves) throws MoveException {
        moveManager.move(pieceIndex, moves);
        }




    public void putNewPieceOnBoard(int moves) throws MoveException{
        if(game.getCurrentPlayer().getPieceManager().getPiecesOffBoard().size() == 0)
            throw new MoveException("All your pieces are already on the board");
        int pieceIndex = game.getCurrentPlayer().getPieceManager().getPiecesOffBoard().remove(0);
        game.getCurrentPlayer().getPieceManager().getPiecesOffBoard().add(pieceIndex);
        this.move(pieceIndex, moves);

    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.ESCAPE)
            view.getScene().setRoot(new StartView());
    }

    public boolean pieceTypeByCoordsIsCurrentPlayer(int x, int y) {
        return game.getBoard().getPieceOnTypeByCoords(new int[]{y, x}) ==
                game.getCurrentPlayer().getPieceManager().getPieceType();
    }

    public void goMainMenu() {
        view.getScene().setRoot(new StartView());
    }

    public void launchHelp() {
        Scene helpScene = new Scene(new HelpView(), 600,600);
        Stage helpStage = new Stage();
        helpStage.setX(100);
        helpStage.setY(100);
        helpStage.setScene(helpScene);
        helpStage.show();
    }

    public void exitApp() {
        Platform.exit();
    }
}
