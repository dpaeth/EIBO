package presentation.uicomponents.bottompanel;

import javafx.scene.layout.StackPane;
import presentation.uicomponents.playercontrol.PlayerControl;
import structure.Mp3Player;

public class BottomPanel extends StackPane {

    public BottomPanel(Mp3Player player) {
        PlayerControl playercontrol = new PlayerControl(player);
        this.setMinSize(1000, 80);
        this.setMaxHeight(80);
        //this.setStyle("-fx-background-color: #3E3E3E;");
        this.getChildren().add(playercontrol);
        this.setId("bottompanel");
        this.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());
    }
}

