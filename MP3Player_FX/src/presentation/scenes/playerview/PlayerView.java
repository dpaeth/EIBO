package presentation.scenes.playerview;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

import java.io.IOException;

public class PlayerView extends BorderPane {
    TopPanel topPanel;
    SidePanel sidePanelLeft;
    SidePanel sidePanelRight;
    PlayerContent playerContent;
    BottomPanel bottomPanel;
    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;


    public PlayerView(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {
        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;

        this.topPanel = new TopPanel();
        this.bottomPanel = new BottomPanel(defPlaylist, player, manager);
        this.sidePanelLeft = new SidePanel();
        this.sidePanelRight = new SidePanel();
        this.playerContent = new PlayerContent(defPlaylist, player, manager);


        this.setTop(topPanel);
        this.setBottom(bottomPanel);
        this.setLeft(sidePanelLeft);
        this.setRight(sidePanelRight);
        this.setCenter(playerContent);


        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


    }

}
