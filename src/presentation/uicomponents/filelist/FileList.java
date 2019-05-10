package presentation.uicomponents.filelist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import structure.Mp3Player;
import structure.PlaylistManager;

import java.util.ArrayList;


public class FileList extends VBox {

    private final Mp3Player player;
    private final PlaylistManager manager;
    private final ListView<String> playlists;
    private final ListView<String> songs;

    public FileList(Mp3Player player, PlaylistManager manager) {
        this.manager = manager;
        this.player = player;

        Label p = new Label("Playlists");
        p.setPadding(new Insets(20, 0, 0, 0));
        this.playlists = new ListView<>();
        ObservableList<String> playlistitems = FXCollections.observableArrayList(manager.getPlaylistNames());
        playlists.setItems(playlistitems);
        playlists.setMaxHeight(160);
        playlists.setMaxWidth(650);
        playlists.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        playlists.getSelectionModel().select(manager.aktPlaylistProperty().get());


        Label s = new Label("Songs");
        s.setPadding(new Insets(20, 0, 0, 0));
        this.songs = new ListView<>();
        ObservableList<String> songitems = FXCollections.observableArrayList(manager.getAktPlaylist().getSongNames());
        songs.setItems(songitems);
        songs.setMaxHeight(240);
        songs.setMaxWidth(650);
        songs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        songs.getSelectionModel().select(player.aktSongIndexProperty().get());

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(p, playlists, s, songs);
        this.setSpacing(5);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        initialize();
    }

    private void initialize() {
        /**
         * Falls sich die aktPlaylist im Backend ändert
         */
        manager.aktPlaylistProperty().addListener((observable, oldValue, newValue) -> playlists.getSelectionModel().select(newValue.intValue()));

        /**
         * Falls sich der aktuelle Song im Backend ändert
         */
        player.aktSongIndexProperty().addListener((observable, oldValue, newValue) -> songs.getSelectionModel().select(newValue.intValue()));

        /**
         * Bei Klick auf eine Playlist
         */
        playlists.setOnMouseClicked(event -> {
            player.pause();
            int selected = playlists.getSelectionModel().getSelectedIndex();
            manager.setAktPlaylist(selected);
            player.setAktPlaylist(manager.getPlaylist(selected));
            ArrayList<String> newsongs = manager.getAktPlaylist().getSongNames();
            ObservableList<String> newsongitems = FXCollections.observableArrayList(newsongs);
            songs.setItems(newsongitems);
        });

        /**
         * Bei Klick auf einen Song
         */
        songs.setOnMouseClicked(event -> {
            int selected = songs.getSelectionModel().getSelectedIndex();
            player.pause();
            player.playTrack(selected);
        });
    }
}