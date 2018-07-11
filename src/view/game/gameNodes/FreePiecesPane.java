package view.game.gameNodes;

import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.piece.PieceType;
import presenter.game.GamePresenter;
import view.game.GameViewElement;
import utilities.CssManager;


public class FreePiecesPane extends GridPane implements GameViewElement {
    private final Image WHITE_SPRITE = new Image("file:gfx\\game\\whitePiece.png");
    private final Image BLACK_SPRITE = new Image("file:gfx\\game\\blackPiece.png");

    private GamePresenter presenter;
    private ImageView[] pieceImageViews;
    private ScaleTransition highlightTransition;

    // deze HBOX is nodig om een rand te krijgen rond de linkerboven piece
    private HBox topLeftBox;
    public FreePiecesPane(GamePresenter presenter) {
        this.presenter = presenter;
        this.setStyle(CssManager.basicStyle);
        initialiseNodes();
        layoutNodes();
        initialiseEventHandling();
        update();
    }

    public void initialiseNodes() {
        pieceImageViews = new ImageView[7];
        for(int i = 0; i< pieceImageViews.length; i++) {
            pieceImageViews[i] = new ImageView();
        }
        topLeftBox = new HBox();
        highlightTransition = new ScaleTransition(Duration.millis(500), pieceImageViews[0]);
        highlightTransition.setByX(-.1);
        highlightTransition.setByY(-.1);
        highlightTransition.setCycleCount(-1);
        highlightTransition.setAutoReverse(true);
    }

    public void layoutNodes() {
        //plaats de imageviews in de grid 4 in 1ste kolom 3 in de kolom
        topLeftBox.getChildren().add(pieceImageViews[0]);
        highlightTransition.play();
        this.add(topLeftBox,0,0);
        for(int y = 1; y < 4; y++)  {
            this.add(pieceImageViews[y],0, y);
        }
        for(int y = 0; y <3; y++) {
            this.add(pieceImageViews[y+4], 1,y);
        }
    }

    public void initialiseEventHandling() {
        topLeftBox.setOnMouseClicked(event -> presenter.newPieceClicked());
    }

    public void update() {
        Image pieceImage = this.presenter.getGame().getCurrentPlayer().getPieceManager().getPieceType() == PieceType.WHITE?
                WHITE_SPRITE: BLACK_SPRITE;
        int piecesLeft = this.presenter.getGame().getCurrentPlayer().getPieceManager().getPiecesOffBoard().size();
        for(int i = 0; i < pieceImageViews.length; i++) {
            pieceImageViews[i].setImage(i<piecesLeft?pieceImage:null);
        }
        topLeftBox.setStyle("-fx-border-insets: 1;-fx-border-width: 1;-fx-border-color: white;");
    }
}
