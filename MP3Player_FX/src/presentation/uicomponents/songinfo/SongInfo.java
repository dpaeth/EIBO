package presentation.uicomponents.songinfo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import structure.Mp3Player;
import structure.Track;

/**
 * @author Dorian Paeth
 * @author Julian Gräber
 * UI-Komponente - Songinfos
 * Label der Informationenanzeige wird erstellt
 */

public class SongInfo extends VBox {

    private Mp3Player player;
    private Track track;
    private Label title, artist, album, year, key, length, bpm;

    //Konstruktor der InfoAnzeige
    public SongInfo (Mp3Player player){
        this.player = player;
        this.track = player.getAktTrack();

        title = new Label();
        title.setId("title");

        artist = new Label();
        artist.setId("artist");

        album = new Label();
        album.setId("detail");

        year = new Label();
        year.setId("detail");

        key = new Label();
        key.setId("detail");

        length = new Label();
        length.setId("detail");

        bpm = new Label();
        bpm.setId("detail");

        setLabels(player);

        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPadding(new Insets(20, 0, 30, 0));
        album.setPadding(new Insets(10,0,0,0));
        this.getChildren().addAll(title, artist, album, year, key, length);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    //Label werden mit Informationen des aktuellen Tracks gefüllt
    public void setLabels(Mp3Player player){
        this.player = player;
        this.track = player.getAktTrack();

        title.setText(track.getTitle());
        artist.setText(track.getArtist());
        album.setText("Album: "+player.getAktTrack().getAlbum());
        year.setText("Jahr: "+track.getYear());
        key.setText("Key: "+track.getKey());
        length.setText("Länge: "+(player.getAktSongLength())/1000+" Sekunden");
        bpm.setText("BPM: "+track.getBpm());
    }
}
