package view.menus;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public abstract class MenuView extends BorderPane {
    private VBox centerVBox;

    public MenuView() {
        String cssString = "-fx-background-image: url('/gfx/menus/menuBG.jpg');" +
                "-fx-background-size: 100%, 100%;" +
                "-fx-background-repeat: no-repeat;";
        this.setStyle(cssString);

        //de centerVBox zal alle nodes in het menu in zich dragen
        setupCenterVBox();
    }

    private void setupCenterVBox() {
        centerVBox = new VBox(8);
        centerVBox.setAlignment(Pos.CENTER);
        this.setCenter(centerVBox);
    }

    public abstract void initialiseNodes();
    public abstract void layoutNodes();
    //public abstract void initialiseEventHandling();

    public void addToCenterVBox(Node node) {
        centerVBox.getChildren().add(node);
    }
}
