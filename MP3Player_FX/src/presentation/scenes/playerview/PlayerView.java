package presentation.scenes.playerview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.playercontrol.PlayerControl;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class PlayerView extends StackPane {

    public PlayerControl playerControl;
    public Pane cover;
    private Mp3Player player;
    private Playlist defPlaylist;
    private PlaylistManager manager;


    public PlayerView(Mp3Player player, Playlist defPlaylist, PlaylistManager manager) {

        this.player = player;
        this.defPlaylist = defPlaylist;
        this.manager = manager;
        playerControl = new PlayerControl();
        cover = new Cover(defPlaylist,player,manager);

        this.setPadding(new Insets(10));
        this.getChildren().addAll(playerControl, cover);
        this.setAlignment(cover,Pos.CENTER_LEFT);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }
}
