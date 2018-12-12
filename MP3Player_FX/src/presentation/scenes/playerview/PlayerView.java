package presentation.scenes.playerview;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.list.ListViewModel;
import presentation.uicomponents.playercontrol.PlayerControl;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class PlayerView extends StackPane {
    Pane playercontrol, cover, listView;


    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;


    public PlayerView(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {
        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;

        playercontrol = new PlayerControl(defPlaylist,player,manager);
        cover = new Cover(defPlaylist, player, manager);
        listView = new ListViewModel(defPlaylist,player,manager);

        this.setAlignment(listView, Pos.CENTER_RIGHT);
        this.setAlignment(playercontrol,Pos.CENTER_LEFT);
        this.setAlignment(cover,Pos.BOTTOM_LEFT);
        this.getChildren().addAll(playercontrol, cover, listView);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


    }

}
