package presentation.scenes.playerview;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import presentation.application.Main;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

import java.io.IOException;

public class PlayerViewController {

    private PlayerView view;
    private PlayerControl pc;
    private BottomPanel bottomPanel;
    private TopPanel topPanel;
    private Mp3Player player;
    private Playlist defPlaylist;
    private Main application;
    private boolean isPlaying;
    private Button switchPlayer;
    private Button switchList;

    public PlayerViewController(Mp3Player player, Playlist defPlaylist,PlaylistManager manager,Main application){

        isPlaying = false;
        this.player = player;
        this.defPlaylist = defPlaylist;
        this.view = new PlayerView(defPlaylist,player,manager);
        this.bottomPanel = view.bottomPanel;
        this.topPanel = view.topPanel;
        this.pc = bottomPanel.playercontrol;
        this.topPanel = view.topPanel;
        this.switchList = view.topPanel.switchList;
        this.switchPlayer = view.topPanel.switchPlayer;
        this.application = application;
        initialize();
    }

    public void initialize(){
        pc.play.addEventHandler(ActionEvent.ACTION, e -> {
            if (!isPlaying) {
                player.play();
                isPlaying = true;
            }
        });
        pc.stop.addEventHandler(ActionEvent.ACTION, e -> {
            player.stop();
            isPlaying = false;
        });
        pc.skip.addEventHandler(ActionEvent.ACTION, e -> skipPlay());
        switchPlayer.addEventFilter(ActionEvent.ACTION, e -> application.switchView("PLAYER"));
        switchList.addEventFilter(ActionEvent.ACTION, e -> application.switchView("LIST"));

    }
    public void skipPlay() {
        //defPlaylist.skipToNextSong();
        player.play();
    }

    public PlayerView getView() {
        return view;
    }


}

