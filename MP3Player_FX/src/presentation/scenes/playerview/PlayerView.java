package presentation.scenes.playerview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.playercontrol.PlayerControl;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

import java.io.IOException;

public class PlayerView extends StackPane {
    Pane playercontrol, cover;

    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;


    public PlayerView(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {
        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;

        playercontrol = new PlayerControl(defPlaylist, player, manager);
        cover = new Cover(defPlaylist, player, manager);

        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(playercontrol, cover);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


    }

}
