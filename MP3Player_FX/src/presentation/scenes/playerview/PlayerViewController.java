package presentation.scenes.playerview;

import javafx.event.ActionEvent;
import presentation.application.Main;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.viewcontrol.ViewControl;
import structure.Mp3Player;

/**
 * @author Dorian Paeth
 * @author Julian Gräber
 * Controller der PlayerView.
 * Hier wird die Playerview erstellt und das Backend hinzugefügt.
 */

public class PlayerViewController {

    private PlayerView view;
    private ViewControl viewControl;
    private PlayerContent content;
    private Mp3Player player;
    private Main application;

    //Konstruktor des Controllers
    public PlayerViewController(Mp3Player player, Main application){
        this.player = player;
        this.view = new PlayerView(player);

        this.content = view.getPlayerContent();
        this.viewControl = view.getTopPanel().getViewcontrol();

        this.application = application;
        initialize();
    }

    //initialisiert die SwitchView
    private void initialize(){
        viewControl.addEventFilter(ActionEvent.ACTION, e -> application.switchView("LIST"));
    }

    //GETTER
    public PlayerView getView() {
        return this.view;
    }
}

