package presentation.scenes.playlistview;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import presentation.application.Main;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class PlayListViewController {

    private PlaylistView view;
    private PlayerControl playerControl;
    private Pane listView;
    private Mp3Player player;
    public TopPanel topPanel;
    private Main application;
    private Button switchPlayer;
    private Button switchList;

    public PlayListViewController(Playlist defPlaylist, Mp3Player player, PlaylistManager manager, Main application) {

        this.view = new PlaylistView(defPlaylist, player, manager);
        this.player = player;
        this.listView = view.listView;
        this.topPanel = view.topPanel;
        this.switchList = view.topPanel.switchList;
        this.switchPlayer = view.topPanel.switchPlayer;
        this.application = application;
        init();
    }

    public void init() {
        switchPlayer.addEventFilter(ActionEvent.ACTION, e -> application.switchView("PLAYER"));
        switchList.addEventFilter(ActionEvent.ACTION, e -> application.switchView("LIST"));
    }

    public PlaylistView getView() {
        return view;
    }
}

