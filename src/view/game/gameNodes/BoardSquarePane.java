package view.game.gameNodes;

import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.square.SquareType;
import presenter.game.GamePresenter;
import view.game.GameViewElement;



public class BoardSquarePane extends StackPane implements GameViewElement {

    private final Image ROSETTA_BG = new Image("file:gfx\\game\\rosetta.png");
    private final Image BLANK_BG =  new Image("file:gfx\\game\\blankSquare.png");
    private final Image INOUT_BG = new Image("file:gfx\\game\\InOut.png");
    private final Image WHITE_SPRITE = new Image("file:gfx\\game\\whitePiece.png");
    private final Image BLACK_SPRITE = new Image("file:gfx\\game\\blackPiece.png");

    private ImageView backgroundImage;
    private ImageView pieceImage;
    private ScaleTransition highlightTransition;
    private int x;
    private int y;
    private GamePresenter presenter;

    public BoardSquarePane(GamePresenter presenter, int x, int y) {
        this.setPrefSize(50,50);
        this.presenter = presenter;
        this.x = x;
        this.y = y;

        initialiseNodes(this.presenter.getGame().getBoard().getSquareTypeByCoords(new int[]{y,x}));
        layoutNodes();
        initialiseEventHandling();
    }

    public void initialiseNodes(SquareType squareType) {
        pieceImage = new ImageView();
        highlightTransition = new ScaleTransition(Duration.millis(500), pieceImage);
        highlightTransition.setByX(-.1);
        highlightTransition.setByY(-.1);
        highlightTransition.setCycleCount(-1);
        highlightTransition.setAutoReverse(true);
        switch(squareType) {
            case BLANK:
                backgroundImage = new ImageView(BLANK_BG);
                break;
            case ROSETTA:
                backgroundImage = new ImageView(ROSETTA_BG);
                break;
            case INOUT:
                backgroundImage = new ImageView(INOUT_BG);
                break;
        }

    }

    public void layoutNodes() {
        this.getChildren().add(backgroundImage);
        this.getChildren().add(pieceImage);
    }

    public void initialiseEventHandling() {
        this.setOnMouseClicked(event -> presenter.squareClicked(new int[]{y, x}));
    }
    public void update() {
        if(presenter.pieceTypeByCoordsIsCurrentPlayer(this.x, this.y)) {
            highlightTransition.play();
        }
        else {
            highlightTransition.stop();
        }


        if(this.presenter.getGame().getBoard().getSquareTypeByCoords(new int[]{y,x})!= SquareType.INOUT) {
            switch (presenter.getGame().getBoard().getPieceOnTypeByCoords(new int[]{this.y, this.x})) {
                case WHITE:
                    this.pieceImage.setImage(WHITE_SPRITE);
                    break;
                case BLACK:
                    this.pieceImage.setImage(BLACK_SPRITE);
                    break;
                case NONE:
                    this.pieceImage.setImage(null);
                    break;
            }
        }
    }
}
