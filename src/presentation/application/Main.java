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
import presentation.scenes.graphicsview.GraphicsView;
import presentation.scenes.playerview.PlayerView;
import presentation.scenes.playlistview.PlaylistView;
import presentation.scenes.walkview.WalkView;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;
import structure.Views;

/**
 * Main-Applikation
 */
public class Main extends Application {

    private PlaylistView playlistview;
    private PlayerView playerview;
    private GraphicsView graphicsview;
    private WalkView walkView;

    private Stage primaryStage;

    @Override
    /**
     * Hier werden alle im Backend benötigten Klassen, plus die Controller der benötigten Views initialisiert
     */
    public void init(){

        Playlist defPlaylist = new Playlist();
        PlaylistManager manager = new PlaylistManager(defPlaylist);
        Mp3Player player = new Mp3Player(defPlaylist);
        manager.addPlaylist(new Playlist("eigenePlaylist", "./newsongs"));

        playlistview = new PlaylistView(player, manager, this);
        playerview = new PlayerView(player, this);
        graphicsview = new GraphicsView(player, this);
        walkView = new WalkView(player, this);
    }

    @Override
    /**
     * Hier kann die default-Ansicht (was angezeigt werden soll, sobald sich das Programm öffnet) angepasst werden
     * Momentan: Playerview
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Pane root = new Pane();
        root.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(root, 1000, 750);
        scene.setRoot(walkView);

        scene.getStylesheets().add(getClass().
                getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.setTitle("Player");
        primaryStage.setMinHeight(750);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }

    public synchronized void switchView(Views view) {
        Scene scene = primaryStage.getScene();
        switch (view) {
            case player:
                if(scene.getRoot() != playerview) {
                    if (scene.getRoot() == graphicsview) {
                        animateBackSwitch(scene, playerview, (Pane) scene.getRoot());
                    } else {
                        animateSwitch(scene, (Pane) scene.getRoot(), playerview);
                    }
                }
                break;
            case playlist:
                if(scene.getRoot() != playlistview) {
                    animateBackSwitch(scene, playlistview, (Pane) scene.getRoot());
                }
                break;
            case graphics:
                if(scene.getRoot() != graphicsview) {
                    if(scene.getRoot() == playerview) {
                        animateSwitch(scene, (Pane) scene.getRoot(), graphicsview);
                    } else {
                        animateBackSwitch(scene, graphicsview,(Pane) scene.getRoot());
                    }
                }
                break;
            case walk:
                if(scene.getRoot() != walkView) {
                animateSwitch(scene, (Pane) scene.getRoot(), walkView);
            }
        }
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
