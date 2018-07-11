package presenter.menus;

import view.menus.IntroView;
import view.menus.StartView;


public class IntroPresenter {
    private IntroView view;

    public IntroPresenter (IntroView view) {
        this.view = view;
    }

    public void startClick() {
        view.hideButton();
        view.animate();
    }

    public void goMainMenu() {
        try {
            this.view.getScene().setRoot(new StartView());
        }
        catch(NullPointerException nE) {
            
        }
    }

}
