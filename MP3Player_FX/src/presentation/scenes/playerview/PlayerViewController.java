package presentation.scenes.playerview;

import javafx.event.ActionEvent;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.playercontrol.PlayerControl;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class PlayerViewController {

    private PlayerView view;
    private PlayerControl playerControl;
    private Cover cover;
    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;

    public PlayerViewController(Playlist defPlaylist,Mp3Player player, PlaylistManager manager){

        this.view = new PlayerView(player,defPlaylist,manager);
        this.cover = new Cover(defPlaylist,player,manager);
        this.player = player;
        this.playerControl = view.playerControl;
        initialize();
    }

    public void initialize(){

        playerControl.play.addEventHandler(ActionEvent.ACTION, e -> player.play());
        playerControl.stop.addEventHandler(ActionEvent.ACTION, e -> player.stop());
    }

    public PlayerView getView() {
        return view;
    }
}

