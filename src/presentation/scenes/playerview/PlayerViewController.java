package presentation.scenes.playerview;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import presentation.application.Main;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.viewcontrol.ViewControl;
import structure.Mp3Player;

public class PlayerViewController {

    private PlayerView view;

    private ViewControl viewControl;
    private PlayerContent content;

    private Mp3Player player;
    private Main application;

    public PlayerViewController(Mp3Player player, Main application){

        this.player = player;
        this.view = new PlayerView(player);

        this.content = view.getPlayerContent();
        this.viewControl = view.getTopPanel().getViewcontrol();

        this.application = application;
        initialize();
    }

    private void initialize(){

        viewControl.addEventFilter(ActionEvent.ACTION, e -> application.switchView("LIST"));



    }

    public PlayerView getView() {
        return this.view;
    }


}

