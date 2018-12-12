package presentation.uicomponents.list;

import com.mpatric.mp3agic.Mp3File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;
import structure.Track;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListViewModel extends HBox {

    private Mp3Player player;
    private PlaylistManager manager;
    private Playlist defPlayList;
    ListView<Track> playlist;
    HashMap songs;
    List<Track> songz;

    public ListViewModel(Playlist defPlayList, Mp3Player player, PlaylistManager manager){
        this.defPlayList = defPlayList;
        this.player = player;
        this.manager = manager;

        ObservableList<Track> items = FXCollections.observableArrayList();
        songz = defPlayList.getPlaylist();
        items.setAll(songz);

        playlist = new ListView<Track>();
        playlist.setItems(items);
        playlist.setEditable(true);
        playlist.setCellFactory(
                new Callback<ListView<Track>, ListCell<Track>>() {
                    @Override
                    public ListCell<Track> call(ListView<Track> v) {
                        return new TrackListCell();
                    }
                }
        );

        this.getChildren().addAll(playlist);

    }


}
