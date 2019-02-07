package presentation.scenes.playlistview;

import javafx.scene.layout.*;
import presentation.application.Main;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.playlistcontent.PlaylistContent;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.PlaylistManager;

public class PlaylistView extends BorderPane {

    public PlaylistView(Mp3Player player, PlaylistManager manager, Main application) {
        TopPanel topPanel = new TopPanel(application);
        BottomPanel bottomPanel = new BottomPanel(player);
        //SidePanel sidePanelLeft = new SidePanel();
        //SidePanel sidePanelRight = new SidePanel();
        PlaylistContent playlistContent = new PlaylistContent(player, manager);

        this.setTop(topPanel);
        this.setBottom(bottomPanel);
        //this.setLeft(sidePanelLeft);
        //this.setRight(sidePanelRight);
        this.setCenter(playlistContent);
    }

}
