package presentation.scenes.playlistview;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.sidepanel.SidePanel;
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


    public PlayListViewController(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {

        this.view = new PlaylistView(defPlaylist, player, manager);
        this.player = player;
        this.listView = view.listView;
        this.topPanel = view.topPanel;
    }

    public PlaylistView getView() {
        return view;
    }
}

