package presentation.uicomponents.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import structure.Playlist;
import structure.Track;

import java.util.List;

public class ListViewModel extends StackPane {


    ListView<Track> playlist;
    List<Track> songs;


    public ListViewModel(Playlist defPlayList){

        ObservableList<Track> items = FXCollections.observableArrayList();
        songs = defPlayList.getPlaylist();
        items.setAll(songs);

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

        //????
        //playlist.getSelectionModel().selectedItemProperty().addListener();

        this.setMaxWidth(300);
        this.setMaxHeight(500);
        this.getChildren().addAll(playlist);
        this.getStylesheets().add(getClass().
                getResource("style.css").toExternalForm());

    }
}
