package presentation.uicomponents.playercontent;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.songinfo.SongInfo;
import presentation.uicomponents.timecontrol.TimeControl;
import structure.Mp3Player;

/**
 * @author Dorian Paeth
 * @author Julian Gräber
 * UI-Komponente - PlayerContent
 * Der Inhalt der Playerview wird definiert.
 */

public class PlayerContent extends VBox {
    private Cover cover;
    private SongInfo songinfo;
    private TimeControl timecontrol;
    private Mp3Player player;

    //Konstruktor des PlayerContents
    public PlayerContent(Mp3Player player){
        this.player = player;
        this.cover = new Cover(player.getAktTrack());
        this.songinfo = new SongInfo(player);
        this.timecontrol = new TimeControl(player);

        this.setAlignment(Pos.CENTER);
        this.setMinHeight(520);
        this.setMinWidth(760);
        this.setPrefWidth(760);
        this.setPrefHeight(520);
        this.setStyle("-fx-background-color: #2E2E30;");
        this.getChildren().addAll(songinfo, cover, timecontrol);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        initialize();
    }

    //initialisiert den Inhalt und sorgt für Aktualisierung bei Änderungen
    public void initialize(){

        //Bei Änderung des aktSongs werden Songinfo und Cover aktualisiert

        player.aktSongNameProperty().addListener((observable, oldValue, newValue) -> {
            songinfo.setLabels(player);
            Image newCover = player.getAktTrack().getCover();

            if (newCover != null){
                cover.setImage(newCover);
            } else {
                cover.setDefImage();
            }
        });
    }
}
