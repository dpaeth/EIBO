package presentation.scenes.graphicsview;

import javafx.scene.layout.*;
import presentation.application.Main;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.graphicscontent.GraphicsContent;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;

public class GraphicsView extends BorderPane {


    public GraphicsView(Mp3Player player, Main application) {
        TopPanel topPanel = new TopPanel(application);
        BottomPanel bottomPanel = new BottomPanel(player);
        //SidePanel sidePanelLeft = new SidePanel();
        //SidePanel sidePanelRight = new SidePanel();
        GraphicsContent graphicscontent = new GraphicsContent(player);

        this.setTop(topPanel);
        this.setBottom(bottomPanel);
        //this.setLeft(sidePanelLeft);
        //this.setRight(sidePanelRight);
        this.setCenter(graphicscontent);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

}
