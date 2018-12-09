package presentation.scenes.playlistview;

import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import presentation.uicomponents.list.List;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class PlaylistView extends StackPane {

    Pane listView;

    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;

    public PlaylistView(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {

        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;

        listView = new List(defPlaylist,player,manager);

        this.setAlignment(listView, Pos.BOTTOM_RIGHT);
        this.getChildren().addAll(listView);

    }
}
