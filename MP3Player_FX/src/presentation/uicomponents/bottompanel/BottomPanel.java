package presentation.uicomponents.bottompanel;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import presentation.uicomponents.playercontrol.PlayerControl;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class BottomPanel extends StackPane {

    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;

    public PlayerControl playercontrol;

    public BottomPanel(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {
        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;
        playercontrol = new PlayerControl(defPlaylist, player, manager);

        this.setMinSize(1000, 60);
        this.setMaxHeight(60);
        this.setStyle("-fx-background-color: #2E2E30;");
        this.getChildren().add(playercontrol);

        //this.setAlignment(Pos.BASELINE_CENTER);
        //this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

}

