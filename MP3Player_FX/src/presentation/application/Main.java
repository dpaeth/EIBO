package presentation.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.scenes.playerview.PlayerViewController;
import presentation.scenes.playlistview.PlayListViewController;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class Main extends Application {
    private Playlist defPlaylist;
    private PlaylistManager manager;
    private Mp3Player player;
    private PlayerViewController playerViewController;
    private PlayListViewController playListViewController;
    Stage primaryStage;

    @Override
    public void init(){

        defPlaylist = new Playlist();
        manager = new PlaylistManager(defPlaylist);
        player = new Mp3Player(manager.getAktPlaylist().getAktSong());
        playerViewController = new PlayerViewController(player,defPlaylist,manager,this);
        playListViewController = new PlayListViewController(defPlaylist,player,manager,this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        Pane root = new Pane();
        root.setStyle("-fx-background-color: transparent;");

        //switchView("PLAYER");
        Scene scene = new Scene(root, 1000, 750);
        scene.setRoot(playerViewController.getView());

        scene.getStylesheets().add(getClass().
                getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.setTitle("Player");
        primaryStage.setMinHeight(750);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }

    public void switchView(String name) {
        Scene scene = primaryStage.getScene();
        switch (name) {
            case "PLAYER":
                scene.setRoot(playerViewController.getView());
                break;
            case "LIST":
                scene.setRoot(playListViewController.getView());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
