package presentation.application;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import presentation.scenes.playerview.PlayerView;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;
import structure.Views;

/**
 * Main-Applikation
 */
public class Main extends Application {

    private PlayerView playerview;

    /**
     * Hier werden alle im Backend benötigten Klassen, plus die Controller der benötigten Views initialisiert
     */
    @Override
    public void init(){

        Playlist defPlaylist = new Playlist();
        PlaylistManager manager = new PlaylistManager(defPlaylist);
        manager.addPlaylist(new Playlist("eigenePlaylist", "./newsongs"));
        Mp3Player player = new Mp3Player(manager.getPlaylist(0));


        //playlistview = new PlaylistView(player, manager, this);
        playerview = new PlayerView(player, manager, this);
        //coolView = new CoolView(player, this);

    }

    /**
     * Hier kann die default-Ansicht (was angezeigt werden soll, sobald sich das Programm öffnet) angepasst werden
     * Momentan: Playerview
     */
    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        root.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(root, 1000, 750);
        scene.setRoot(playerview);

        scene.getStylesheets().add(getClass().
                getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.setTitle("Player");
        primaryStage.setMinHeight(750);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }

    public void switchView(Views view) {
        //Scene scene = primaryStage.getScene();
        playerview.switchCenter(view);

            /*
            case playlist:
                if(scene.getRoot() != playlistview) {
                    animateBackSwitch(scene, playlistview, (Pane) scene.getRoot());
                }
                break;
            case player:
                if(scene.getRoot() != playerview) {
                    if (scene.getRoot() == playlistview) {
                        animateSwitch(scene, (Pane) scene.getRoot(), playerview);
                    } else {
                        animateBackSwitch(scene, playerview, (Pane) scene.getRoot());
                    }
                }
                break;
            case cool:
                if(scene.getRoot() != coolView) {
                    coolView = new CoolView(player, this);
                    if(scene.getRoot() == playerview) {
                        animateSwitch(scene, (Pane) scene.getRoot(), coolView);
                    } else {
                        animateBackSwitch(scene, coolView,(Pane) scene.getRoot());
                    }
                }
                break;
            case frost:
                if(scene.getRoot() != frostView) {
                    frostView = new FrostView(player, this);
                    if(scene.getRoot() != kidView) {
                        animateSwitch(scene, (Pane) scene.getRoot(), frostView);
                    } else {
                        animateBackSwitch(scene, frostView,(Pane) scene.getRoot());
                    }
                    break;
                }
            case kid:
                if(scene.getRoot() != kidView) {
                    kidView = new KidView(player, this);
                    animateSwitch(scene, (Pane) scene.getRoot(), kidView);
                    break;
                }

            */

    }

    public synchronized void animateSwitch(Scene scene, Pane fromView, Pane toView) {
        StackPane transitionView = new StackPane();
        transitionView.addEventFilter(MouseEvent.ANY,e -> {
            e.consume();
        });
        scene.setRoot(transitionView);
        transitionView.getChildren().addAll(fromView,toView);
        toView.toFront();
        toView.translateXProperty().set(scene.getWidth());
        TranslateTransition animation = new TranslateTransition();
        animation.setNode(toView);
        animation.setDuration(Duration.millis(400));
        animation.setToX(0);
        animation.setOnFinished(event -> {
            transitionView.getChildren().remove(fromView);
            transitionView.getChildren().remove(toView);
            toView.translateXProperty().set(0);
            fromView.translateXProperty().set(0);
            scene.setRoot(toView);
        });
        animation.play();
    }
    public synchronized void animateBackSwitch(Scene scene, Pane fromView, Pane toView) {
        StackPane transitionView = new StackPane();
        transitionView.addEventFilter(MouseEvent.ANY,e -> {
            e.consume();
        });
        scene.setRoot(transitionView);
        transitionView.getChildren().addAll(fromView,toView);
        toView.toFront();
        toView.translateXProperty().set(0);
        TranslateTransition animation = new TranslateTransition();
        animation.setNode(toView);
        animation.setDuration(Duration.millis(400));
        animation.setToX(scene.getWidth());
        animation.setOnFinished(event -> {
            transitionView.getChildren().remove(toView);
            transitionView.getChildren().remove(fromView);
            toView.translateXProperty().set(0);
            fromView.translateXProperty().set(0);
            scene.setRoot(fromView);

        });
        animation.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
