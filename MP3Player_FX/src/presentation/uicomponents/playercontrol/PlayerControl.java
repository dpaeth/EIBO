package presentation.uicomponents.playercontrol;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PlayerControl extends HBox {
    Button play, stop, skip, back, shuffle, repeat;

    public PlayerControl(){

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



        this.setSpacing(2);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(back, play, stop, skip/*, shuffle, repeat*/);
        this.getStylesheets().add(getClass().
                getResource("style.css").toExternalForm());
    }
}
