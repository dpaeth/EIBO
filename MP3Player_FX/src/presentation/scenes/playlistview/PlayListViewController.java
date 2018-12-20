package presentation.scenes.playlistview;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import presentation.application.Main;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class PlayListViewController {

    private PlaylistView view;
    private PlayerControl pc;
    private Pane listView;
    private Mp3Player player;
    private TopPanel topPanel;
    private Main application;
    private Button switchPlayer;
    private Button switchList;
    private BottomPanel bottomPanel;
    private boolean isPlaying;


    public PlayListViewController(Playlist defPlaylist, Mp3Player player, PlaylistManager manager, Main application) {

        isPlaying = false;
        this.view = new PlaylistView(defPlaylist, player, manager);
        this.player = player;
        this.listView = view.listView;
        this.topPanel = view.topPanel;
        this.switchList = view.topPanel.switchList;
        this.switchPlayer = view.topPanel.switchPlayer;
        this.application = application;
        this.bottomPanel = view.bottomPanel;
        this.pc = bottomPanel.playercontrol;
        initialize();
    }

    public void initialize() {
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
        switchPlayer.addEventFilter(ActionEvent.ACTION, e -> application.switchView("PLAYER"));
        switchList.addEventFilter(ActionEvent.ACTION, e -> application.switchView("LIST"));
    }

    public PlaylistView getView() {
        return view;
    }
}

