package presenter.menus;

import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.HelpView;
import view.menus.setup.RulesSelectView;
import view.menus.ScoreView;
import view.menus.StartView;

public class StartPresenter extends MenuPresenter  {

    public StartPresenter(StartView view) {
        super(view);

    }

    public void startGame() {
        view.getScene().setRoot(new RulesSelectView());
    }

    public void goToScores() {
        view.getScene().setRoot(new ScoreView());
    }

    public void loadGame() {
        System.out.println("Load game");
    }

    public void goHelp() {
        Scene helpScene = new Scene(new HelpView(), 600,600);
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");
        helpStage.setScene(helpScene);
        helpStage.setX(100);
        helpStage.setY(100);
        helpStage.show();
    }

    public void exitGame() {
        Platform.exit();
    }
}
