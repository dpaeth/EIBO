/*
package presentation;

import Backend.Mp3Player;
import Backend.PlaylistManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;


public class Player extends Application {
    private Mp3Player player;
    private PlaylistManager manager;

    Stage primaryStage;
    Pane playerView;
    Pane playlistView;


    public void init(){
        Backend.Playlist defPlaylist = new Backend.Playlist();
        Backend.PlaylistManager manager = new Backend.PlaylistManager(defPlaylist);
        Backend.Mp3Player player = new Backend.Mp3Player(manager.getAktPlaylist().getAktSong());
        this.player = player;
        this.manager = manager;
    }

    public void start(Stage primaryStage)  {
        this.playerView = new PlayerView();
        //this.playlistView = new PlaylistView();
        this.primaryStage = primaryStage;

        //Pane
        FlowPane root = new FlowPane();
        root.setStyle("-fx-background-color: #585858;");

        //Scene
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(null);

        //Startview

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        switchView("PLAYER");
        primaryStage.setTitle("Player");

        primaryStage.show();

    }

    private void switchView(String name) {
        Scene scene = primaryStage.getScene();
        switch (name) {
            case "PLAYER":
                scene.setRoot(playerView);
                break;
        }
    }


    public void stop(){

    }
}
*/
