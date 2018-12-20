package presentation.scenes.playlistview;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.list.ListViewModel;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class PlaylistView extends BorderPane {

    public PlayerControl playerControl;
    public Pane listView;
    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;
    public TopPanel topPanel;
    private BottomPanel bottomPanel;
    private SidePanel sidePanelLeft;
    private SidePanel sidePanelRight;

    public PlaylistView(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {

        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;
        this.topPanel = new TopPanel();
        this.bottomPanel = new BottomPanel(defPlaylist, player, manager);
        this.sidePanelLeft = new SidePanel();
        this.sidePanelRight = new SidePanel();

        listView = new ListViewModel(defPlaylist);
        playerControl = new PlayerControl(defPlaylist,player,manager);

        this.setTop(topPanel);
        this.setBottom(bottomPanel);
        this.setLeft(sidePanelLeft);
        this.setRight(sidePanelRight);
        this.setCenter(listView);
    }
}