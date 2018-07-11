import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.menus.IntroView;



public class UrGame extends Application {

    @Override
    public void start(Stage mainStage) {
        //StartView startView = new StartView();
        IntroView introView = new IntroView();
        mainStage.setTitle("The ancient game of Ur");
        mainStage.setScene(new Scene(introView, 600, 600));
        mainStage.show();

    }
}
