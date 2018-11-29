package presentation.scenes.playerview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.playercontrol.PlayerControl;

import java.io.IOException;

public class PlayerView extends StackPane {
    Pane playercontrol, cover;


    public PlayerView() {

        this.setAlignment(Pos.CENTER_LEFT);

        playercontrol = new PlayerControl();
        cover = new Cover();
        this.getChildren().addAll(playercontrol, cover);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


    }

}
