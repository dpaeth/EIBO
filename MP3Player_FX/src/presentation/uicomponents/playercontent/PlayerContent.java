package presentation.uicomponents.playercontent;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.songinfo.SongInfo;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class PlayerContent extends StackPane {
    public Cover cover;
    public SongInfo songinfo;
    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;

    public PlayerContent(Playlist defPlaylist, Mp3Player player, PlaylistManager manager){
        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;
        this.setMinHeight(520);
        this.setMinWidth(760);
        this.setPrefWidth(760);
        this.setPrefHeight(520);
        this.setStyle("-fx-background-color: #D8D8D8;");

        cover = new Cover(defPlaylist, player, manager);
        songinfo = new SongInfo(defPlaylist,player,manager);


        this.getChildren().addAll(cover, songinfo);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());



    }
}
