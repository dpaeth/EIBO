package presentation.uicomponents.playercontrol;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class PlayerControl extends HBox {
    private Mp3Player player;
    private PlaylistManager manager;
    private Playlist defPlayList;
    Button play, stop, skip, back, shuffle, repeat;

    public PlayerControl(Playlist defPlayList, Mp3Player player, PlaylistManager manager){

        back = new Button();
        back.getStyleClass().addAll("icon-button");
        back.setId("back-button");

        play = new Button();
        play.getStyleClass().addAll("icon-button");
        play.setId("play-button");

        stop = new Button();
        stop.getStyleClass().addAll("icon-button");
        stop.setId("stop-button");

        skip = new Button();
        skip.getStyleClass().addAll("icon-button");
        skip.setId("skip-button");

        /*
        shuffle = new Button("shuffle");
        shuffle.getStyleClass().addAll("icon-button");
        shuffle.setId("shuffle-button");

        repeat = new Button("repeat");
        repeat.getStyleClass().addAll("icon-button");
        repeat.setId("repeat-button");
        */

        this.setSpacing(5);
        this.setPadding(new Insets(5,100,-50,40));
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(back, play, stop, skip/*, shuffle, repeat*/);
        this.getStylesheets().add(getClass().
                getResource("style.css").toExternalForm());
    }
}
