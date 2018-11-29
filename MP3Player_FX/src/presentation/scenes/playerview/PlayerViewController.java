package presentation.scenes.playerview;

import presentation.uicomponents.playercontrol.PlayerControl;
import structure.Mp3Player;
import structure.PlaylistManager;

import java.io.IOException;

public class PlayerViewController {

    private PlayerView view;
    private PlayerControl playercontrol;

    private Mp3Player player;

    public PlayerViewController(){

            this.view = new PlayerView();
        this.player = player;
        //this.playercontrol = view.playercontrol;
        initialize();

    }

    public void initialize(){

    }


}

