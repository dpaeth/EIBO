package presentation.scenes.playerview;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import presentation.application.Main;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playercontrol.PlayerControl;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

import java.io.IOException;

public class PlayerViewController {

    private PlayerView view;
    private PlayerControl pc;
    private BottomPanel bottomPanel;
    private Mp3Player player;
    private Playlist defPlaylist;
    private Main appli;

    public PlayerViewController(Mp3Player player, Playlist defPlaylist,PlaylistManager manager){
        this.player = player;
        this.defPlaylist = defPlaylist;
        this.view = new PlayerView(defPlaylist,player,manager);
        this.bottomPanel = view.bottomPanel;
        this.pc = bottomPanel.playercontrol;
        initialize();
    }

    public void initialize(){
        pc.play.addEventHandler(ActionEvent.ACTION, e -> player.play());
        pc.stop.addEventHandler(ActionEvent.ACTION, e -> player.stop());
        pc.skip.addEventHandler(ActionEvent.ACTION, e -> skipPlay());

    }
    public void skipPlay() {
        defPlaylist.skipToNextSong();
        player.play();
    }

    public PlayerView getView() {
        return view;
    }


}

