package presentation.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.scenes.playerview.PlayerView;
import presentation.scenes.playlistview.PlaylistView;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class Main extends Application {
    private Playlist defPlaylist;
    private PlaylistManager manager;
    private Mp3Player player;



    Stage primaryStage;
    Pane playerView, playlistView;


    @Override
    public void init(){
        this.defPlaylist = new Playlist();
        this.manager = new PlaylistManager(defPlaylist);
        this.player = new Mp3Player(manager.getAktPlaylist().getAktSong());

    }

    @Override
    public void start(Stage primaryStage) throws Exception{



        this.playlistView = new PlaylistView(defPlaylist,player,manager);
        this.playerView = new PlayerView(defPlaylist, player, manager);
        this.primaryStage = primaryStage;

        Pane root = new Pane();
        //Pane root1 = new Pane();

        //switchView("PLAYER");
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(null);
        scene.setRoot(playerView);

        scene.getStylesheets().add(getClass().
                getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.setTitle("Player");
        primaryStage.show();

        /*Scene scene1 = new Scene(root1,800,600);
        scene1.setFill(null);
        scene1.setRoot(playlistView);

        scene1.getStylesheets().add(getClass().
                getResource("application.css").toExternalForm());
        primaryStage.setScene(scene1);
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.show();*/
    }

    private void switchView(String name) {
        Scene scene = primaryStage.getScene();
        switch (name) {
            case "PLAYER":
                scene.setRoot(playerView);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
