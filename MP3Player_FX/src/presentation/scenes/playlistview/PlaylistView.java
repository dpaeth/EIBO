package presentation.scenes.playlistview;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import presentation.uicomponents.list.ListViewModel;
import presentation.uicomponents.list.TrackListCell;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class PlaylistView extends StackPane {

    Pane listView;
    public TrackListCell cell;
    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;

    public PlaylistView(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {

        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;
        listView = new ListViewModel(defPlaylist,player,manager);

        this.getChildren().addAll(listView);
        this.setAlignment(listView, Pos.CENTER);
    }
}
