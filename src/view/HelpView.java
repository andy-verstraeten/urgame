package view;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import utilities.CssManager;
import utilities.RulesFileHandler;
import view.menus.MenuView;

import java.io.IOException;

public class HelpView extends MenuView {

    private ScrollPane scrollPane;
    private VBox contentBox;
    private Label title;
    private Label helpText;

    public HelpView() {
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes() {
        scrollPane = new ScrollPane();
        contentBox = new VBox(10);
        title = new Label("Help");
        try {
            helpText = new Label(RulesFileHandler.getRules());
        }
        catch(IOException ioE) {
            helpText = new Label(ioE.getMessage());
        }
    }

    public void layoutNodes() {
        title.setStyle(CssManager.titleTextStyle);
        helpText.setStyle(CssManager.smallTextStyle);
        addToCenterVBox(title);
        addToCenterVBox(scrollPane);
        scrollPane.setMaxWidth(350);
        scrollPane.setPrefHeight(400);
        //scrollPane.setMaxHeight(400);
        scrollPane.setStyle(CssManager.menuPaneBG);
        contentBox.setMinWidth(350);
        contentBox.setMinHeight(400);
        contentBox.setStyle(CssManager.menuPaneBG);
        contentBox.getChildren().add(helpText);
        scrollPane.setContent(contentBox);
    }

}
