package presentation.uicomponents.playlistcontent;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import presentation.uicomponents.filelist.FileList;
import structure.Mp3Player;
import structure.PlaylistManager;

public class PlaylistContent extends VBox {


    public PlaylistContent(Mp3Player player,PlaylistManager manager){
        FileList lists = new FileList(player, manager);

        this.setAlignment(Pos.CENTER);
        this.setMinHeight(520);
        this.setMinWidth(760);
        this.setPrefWidth(760);
        this.setPrefHeight(520);
        this.setStyle("-fx-background-color: #1b1b1b;");
        this.getChildren().addAll(lists);
    }


}
