package presentation.uicomponents.playercontrol;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import structure.Mp3Player;

/**
 * Beinhaltet die Steuerungselemente im BottomPanel:
 * play/pause, stop, skip, back
 * shuffle, repeat
 * volumeslider
 */
public class PlayerControl extends BorderPane {
    private final Mp3Player player;

    private final Button play;
    private final Button stop;
    private final Button skip;
    private final Button back;
    private final Button shuffle;
    private final Button repeat;
    private final Slider volume;
    private boolean playing;


    public PlayerControl(Mp3Player player){
        this.player = player;

        Image playImg = new Image(getClass().getResourceAsStream("../../../assets/icons/play.png"));
        Image pauseImg = new Image(getClass().getResourceAsStream("../../../assets/icons/pause.png"));

        HBox left = new HBox();
        HBox center = new HBox();
        HBox right = new HBox();

        back = new Button();
        back.setId("back");

        play = new Button();
        play.setId("play");

        Button pause = new Button();
        pause.setId("pause");

        stop = new Button();
        stop.setId("stop");

        skip = new Button();
        skip.setId("skip");

        shuffle = new Button();
        shuffle.setId("shuffle");
        shuffle.setStyle("-fx-border-color: #ee1200");

        repeat = new Button();
        repeat.setId("repeat");
        repeat.setStyle("-fx-border-color: #ee1200");

        volume = new Slider(0, 1, 1);
        volume.setValue(player.getVolume() * 100);

        center.getChildren().addAll(back, play, stop, skip);
        left.getChildren().addAll(shuffle, repeat);
        right.getChildren().addAll(volume);

        left.setPadding(new Insets(0, 0, 0, 30));
        right.setPadding(new Insets(0, 30, 0, 0));

        center.setAlignment(Pos.CENTER);
        right.setAlignment(Pos.CENTER);
        left.setAlignment(Pos.CENTER);

        center.setSpacing(5);
        left.setSpacing(5);

        this.setLeft(left);
        this.setRight(right);
        this.setCenter(center);

        this.getStylesheets().add(getClass().
                getResource("style.css").toExternalForm());

        initialize();
    }

    private void initialize(){
        /**
         * Falls auf einen der Buttons geklickt wurde
         */
        play.addEventHandler(ActionEvent.ACTION, e -> {
            if (!playing) {
                player.play();
                playing = true;
                player.loadSpektrum();
            } else {
                player.pause();
                player.getSpektrum().getData().clear();
                playing = false;
            }
        });

        stop.addEventHandler(ActionEvent.ACTION, e -> {
            player.stop();
            playing = false;
            player.getSpektrum().getData().clear();
        });

        skip.addEventHandler(ActionEvent.ACTION, e -> {
            player.skip();
            //view.setPlayerContent(new PlayerContent(player));
        });

        back.addEventHandler(ActionEvent.ACTION, e -> {
            player.back();
        });

        shuffle.addEventHandler(ActionEvent.ACTION, e -> player.shuffle());

        repeat.addEventHandler(ActionEvent.ACTION, e -> player.repeat());

        /**
         * Falls der Volumeslider verschoben wird
         */
        player.volumeProperty().bindBidirectional(volume.valueProperty());


                /**
                 * Falls sich im Backend etwas an shuffle bzw. repeat ändert
                 */
                player.shuffleProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        shuffle.setStyle("-fx-border-color: #88ee00");
                    } else {
                        shuffle.setStyle("-fx-border-color: #ee1200");
                    }
                });

        player.repeatProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                repeat.setStyle("-fx-border-color: #88ee00");
            } else {
                repeat.setStyle("-fx-border-color: #ee1200");
            }
        });

        /**
         * Switch zwischen Play/Pause Icon
         */
        player.isPlaying().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                play.setId("pause");
            } else {
                play.setId("play");
            }

        });
    }
    
}
