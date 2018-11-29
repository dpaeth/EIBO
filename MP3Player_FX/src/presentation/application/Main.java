package presentation.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.scenes.playerview.PlayerView;

public class Main extends Application {
    //private Mp3Player player;
    //private PlaylistManager manager;

    Stage primaryStage;
    Pane playerView;

    @Override
    public void init(){
        //Playlist defPlaylist = new Playlist();
        //PlaylistManager manager = new PlaylistManager(defPlaylist);
         //Mp3Player player = new Mp3Player(manager.getAktPlaylist().getAktSong());
        //this.player = player;
       // this.manager = manager;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.playerView = new PlayerView();
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
