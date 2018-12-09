package presentation.uicomponents.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class List extends ListView{

    private Mp3Player player;
    private PlaylistManager manager;
    private Playlist defPlayList;
    ListView playlist;

    public List(Playlist defPlayList, Mp3Player player, PlaylistManager manager){
        this.defPlayList = defPlayList;
        this.player = player;
        this.manager = manager;

        playlist = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList(defPlayList.getAktSong().toString());
        playlist.setItems(items);
        playlist.setPrefHeight(100);
        playlist.setPrefHeight(70);

        this.getChildren().addAll(playlist);

    }


}
