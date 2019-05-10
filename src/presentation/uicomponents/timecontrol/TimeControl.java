package presentation.uicomponents.timecontrol;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import structure.Mp3Player;

/**
 * Slider, der den aktuellen Track repräsentiert
 */
public class TimeControl extends HBox {

    private final Mp3Player player;
    private final Slider timeSlider;
    private final Label timeLabel;
    private final Label durationLabel;

    private final DoubleProperty position, duration;

    public TimeControl (Mp3Player player){
        this.player = player;
        this.timeSlider = new Slider(0, player.getDuration().get(), 0);
        this.timeLabel = new Label();
        this.durationLabel = new Label();
        this.position = new SimpleDoubleProperty();
        this.duration = new SimpleDoubleProperty();

        timeLabel.setAlignment(Pos.CENTER);


        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5,0,0,0));
        this.getChildren().addAll(timeSlider, timeLabel, durationLabel);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        initialize();
    }



    private void initialize() {
        position.bind(player.getPosition());
        duration.bind(player.getDuration());

        timeSlider.maxProperty().bind(duration);
        timeSlider.valueProperty().bind(position);

        /*
        timeSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wc, Boolean ic) {
                if (ic){
                    position.unbind();
                    timeSlider.valueProperty().unbind();
                    position.bind(timeSlider.valueProperty());
                    player.getAudioPlayer().seek(Duration.seconds(position.get()));
                }
            }
        });
        */


        //timeSlider.valueProperty().unbind();

        timeLabel.textProperty().bind(Bindings.format("%2.0fs ", position));
        durationLabel.textProperty().bind(Bindings.format("/ %2.0fs", duration));



    }
}
