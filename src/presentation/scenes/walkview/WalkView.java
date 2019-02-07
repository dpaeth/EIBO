package presentation.scenes.walkview;

import javafx.scene.layout.BorderPane;
import presentation.application.Main;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.frostcontent.FrostContent;
import presentation.uicomponents.kidcontent.KidContent;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import presentation.uicomponents.coolContent.CoolContent;
import structure.Mp3Player;

public class WalkView extends BorderPane {
    private String click;
    private Mp3Player player;
    private SidePanel sidePanelLeft;


    public WalkView(Mp3Player player, Main application) {
        TopPanel topPanel = new TopPanel(application);
        BottomPanel bottomPanel = new BottomPanel(player);
        sidePanelLeft = new SidePanel();
        //SidePanel sidePanelRight = new SidePanel();

        //Pane mit anklickbaren Screenshots oder Buttons
        //SWITCHCONTENT coolContent, frostContent, kidcontent
        CoolContent coolContent = new CoolContent(player);
        //FrostContent frostContent = new FrostContent(player);
        //KidContent kidContent = new KidContent(player);
        this.setTop(topPanel);
        this.setCenter(coolContent);
        this.setBottom(bottomPanel);
        this.setStyle("-fx-background-color: #dddddd;");
        this.setLeft(sidePanelLeft);
        //this.setRight(sidePanelRight);
    }

    private void init() {

        switch (click) {
            case "cool":
                CoolContent coolContent = new CoolContent(player);
                this.setCenter(coolContent);
                break;
            case "frost":
                FrostContent frostContent = new FrostContent(player);
                this.setCenter(frostContent);
                break;
            case "kid":
                KidContent kidContent = new KidContent(player);
                this.setCenter(kidContent);
                break;
        }
    }
}
