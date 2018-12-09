package presentation.uicomponents.list;

import com.mpatric.mp3agic.Mp3File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

import java.util.HashMap;

public class List extends HBox {

    private Mp3Player player;
    private PlaylistManager manager;
    private Playlist defPlayList;
    ListView playlist;
    HashMap songs;

    public List(Playlist defPlayList, Mp3Player player, PlaylistManager manager){
        this.defPlayList = defPlayList;
        this.player = player;
        this.manager = manager;

        playlist = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList();
        songs = defPlayList.getAll();
        for(int i = 0; i < songs.size(); i++) {
            String x = defPlayList.getSong(i).getFilename();
            items.add(x);
        }
        playlist.setItems(items);
        playlist.setPrefHeight(100);
        playlist.setPrefWidth(400);

        this.getChildren().addAll(playlist);

    }


}
