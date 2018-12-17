package presentation.uicomponents.playercontrol;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import structure.Mp3Player;

public class PlayerControl extends HBox {

    public Button play, stop, skip, back, shuffle, repeat;
    private Mp3Player player;

    public PlayerControl() {

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

        HBox feld = new HBox();
        feld.getChildren().addAll(back, play, stop, skip/*, shuffle, repeat*/);
        feld.setSpacing(5);
        feld.setAlignment(Pos.BOTTOM_CENTER);
        this.setPadding(new Insets(50,5,100,5));
        this.getChildren().add(feld);
        this.getStylesheets().add(getClass().
                getResource("style.css").toExternalForm());
    }
}
