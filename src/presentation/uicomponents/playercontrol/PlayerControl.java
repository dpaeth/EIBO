package presentation.uicomponents.playercontrol;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import structure.Mp3Player;

public class PlayerControl extends HBox {
    private Mp3Player player;

    public Button play;
    public Button stop;
    public Button skip;
    public Button back;
    private Button shuffle;
    private Button repeat;

    public PlayerControl(Mp3Player player){
        this.player = player;

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



        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(back, play, stop, skip/*, shuffle, repeat*/);
        this.getStylesheets().add(getClass().
                getResource("style.css").toExternalForm());
    }
}
