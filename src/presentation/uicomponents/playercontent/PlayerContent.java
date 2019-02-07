package presentation.uicomponents.playercontent;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.songdetails.SongDetails;
import presentation.uicomponents.songinfo.SongInfo;
import presentation.uicomponents.timecontrol.TimeControl;
import structure.Mp3Player;

/**
 * Beinhaltet Cover, Songinfo, Songdetails
 */
public class PlayerContent extends VBox {
    private final Cover cover;
    private final SongInfo songinfo;
    private final SongDetails songdetails;
    private final Mp3Player player;

    public PlayerContent(Mp3Player player){
        this.player = player;
        this.cover = new Cover(player.getAktTrack());
        this.songinfo = new SongInfo(player);
        this.songdetails = new SongDetails(player);

        TimeControl timecontrol = new TimeControl(player);

        this.setAlignment(Pos.CENTER);
        this.setMinHeight(520);
        this.setMinWidth(760);
        this.setPrefWidth(760);
        this.setPrefHeight(520);
        this.setStyle("-fx-background-color: #1b1b1b;");
        this.getChildren().addAll(songinfo, cover, songdetails, timecontrol);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        initialize();
    }

    private void initialize(){
        /**
         * Bei Änderung des aktSongs werden Labels und Cover neu gesetzt
         */
        player.aktSongNameProperty().addListener((observable, oldValue, newValue) -> {
            songinfo.setLabels(player);
            songdetails.setLabels(player);

            Image newCover = player.getAktTrack().getCover();

            if (newCover != null){
                cover.setImage(newCover);
            } else {
                cover.setDefImage();
            }
        });
    }
}
