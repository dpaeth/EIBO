package presentation.uicomponents.songdetails;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import structure.Mp3Player;

/**
 * Enthält weitere Songinformationen
 */
public class SongDetails extends VBox {

    private final Mp3Player player;

    private final Label album, year, track, key, length, bpm;

    public SongDetails (Mp3Player player){
        this.player = player;

        album = new Label();
        year = new Label();
        key = new Label();
        length = new Label();
        bpm = new Label();
        track = new Label();

        this.album.setAlignment(Pos.CENTER);
        this.track.setAlignment(Pos.CENTER);
        this.length.setAlignment(Pos.CENTER);
        this.bpm.setAlignment(Pos.CENTER);

        HBox col1 = new HBox();
        HBox col2 = new HBox();

        col1.getChildren().addAll(album, track);
        col2.getChildren().addAll(length, bpm);
        col1.setAlignment(Pos.CENTER);
        col2.setAlignment(Pos.CENTER);

        init();

        this.setPadding(new Insets(30, 0, 30, 0));
        this.getChildren().addAll(col1, col2);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }


    private void init(){
        album.textProperty().bind(Bindings.format("Albumname: %s", player.getAlbum()));
        year.textProperty().bind(player.getYear());
        key.textProperty().bind(player.getKey());
        length.textProperty().bind(Bindings.format("Genre: %s", player.getGenre()));
        bpm.textProperty().bind(Bindings.format("BPM: %d", player.getBpm()));
        track.textProperty().bind(Bindings.format("TrackNr: %s",player.getTrack()));

    }


}
