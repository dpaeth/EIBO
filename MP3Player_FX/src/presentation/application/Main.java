package presentation.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.scenes.playerview.PlayerView;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class Main extends Application {
    private Playlist defPlaylist;
    private PlaylistManager manager;
    private Mp3Player player;



    Stage primaryStage;
    Pane playerView;

    @Override
    public void init(){
        this.defPlaylist = new Playlist();
        this.manager = new PlaylistManager(defPlaylist);
        this.player = new Mp3Player(manager.getAktPlaylist().getAktSong());

    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        this.playerView = new PlayerView(defPlaylist, player, manager);
        this.primaryStage = primaryStage;


        Pane root = new Pane();
        root.setStyle("-fx-background-color: transparent;");

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
