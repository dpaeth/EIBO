package presentation.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.scenes.playerview.PlayerViewController;
import presentation.scenes.playlistview.PlayListViewController;
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
    PlayerViewController playercontroller;
    PlayListViewController listController;


    @Override
    public void init() {

        this.defPlaylist = new Playlist();
        this.manager = new PlaylistManager(defPlaylist);
        this.player = new Mp3Player(manager.getAktPlaylist().getAktSong());
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.playlistView = new PlaylistView(defPlaylist,player,manager);
        this.playercontroller = new PlayerViewController(defPlaylist,player,manager);
        this.listController = new PlayListViewController(defPlaylist,player,manager);
        this.primaryStage = primaryStage;

        Pane root = new Pane();
        Pane root1 = new Pane();

        //switchView("PLAYER");
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(null);
        scene.setRoot(playercontroller.getView());

        scene.getStylesheets().add(getClass().
                getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("Player");
        primaryStage.show();
    }

    private void switchView(String name) {
        Scene scene = primaryStage.getScene();
        switch (name) {
            case "PLAYER":
                scene.setRoot(playercontroller.getView());
                break;
            case "LISTE":
                scene.setRoot(listController.getView());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
