package presentation.scenes.playerview;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.*;
import presentation.application.Main;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.coolContent.CoolContent;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playlistcontent.PlaylistContent;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.PlaylistManager;
import structure.Views;

public class PlayerView extends BorderPane {
    private final Mp3Player player;

    private final TopPanel topPanel;
    private final BottomPanel bottomPanel;


    private final PlaylistContent playlistContent;
    private final PlayerContent playerContent;
    private final CoolContent coolContent;

    private final IntegerProperty r1 = new SimpleIntegerProperty();
    private final IntegerProperty g1 = new SimpleIntegerProperty();
    private final IntegerProperty b1 = new SimpleIntegerProperty();



    public PlayerView(Mp3Player player, PlaylistManager manager, Main application) {
        this.player = player;

        this.playerContent = new PlayerContent(player);
        this.playlistContent = new PlaylistContent(player, manager);
        this.coolContent = new CoolContent(player);

        this.topPanel = new TopPanel(application);
        this.bottomPanel = new BottomPanel(player);

        //this.domColorPalette = player.getDomColorPalette();
        /*
        this.r = domColorPalette.get(0);
        this.g = domColorPalette.get(1);
        this.b = domColorPalette.get(2);

        topPanel.setStyle("-fx-background-color: rgb("+r+", "+g+", "+b+")");
        bottomPanel.setStyle("-fx-background-color: rgb("+r+", "+g+", "+b+")");

*/

        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        initialize();
        switchCenter(Views.playlist);
        this.setTop(topPanel);
        this.setBottom(bottomPanel);
    }

    private void initialize() {
        this.r1.bind(player.getR1());
        this.g1.bind(player.getG1());
        this.b1.bind(player.getB1());

        player.aktSongIndexProperty().addListener((observableValue, ov, nv) -> {
            topPanel.setStyle("-fx-background-color: rgb("+r1.get()+", "+g1.get()+", "+b1.get()+")");
            bottomPanel.setStyle("-fx-background-color: rgb("+r1.get()+", "+g1.get()+", "+b1.get()+")");
        });


    }


    public synchronized void switchCenter(Views view){
        switch (view){
            case player:
                if (this.getCenter()!=playerContent){
                    centerProperty().setValue(playerContent);
                }
                break;
            case playlist:
                if (this.getCenter()!=playlistContent){
                    centerProperty().setValue(playlistContent);
                }
                break;
            case cool:
                if (this.getCenter()!=coolContent) {
                    centerProperty().setValue(coolContent);
                    centerProperty().get().toBack();
                }
                break;
        }

    }

    public synchronized void switchContent(Views view){
        switch (view){
            case player: {
                if (this.getCenter() != this.playerContent)
                    this.setCenter(playerContent);
            }
            case playlist: {
                if (this.getCenter() != this.playlistContent)
                    this.setCenter(playlistContent);
            }
            case cool: {
                if (this.getCenter() != this.coolContent)
                    this.setCenter(coolContent);
            }
        }
    }

    public Pane getBottomPanel(){
        return this.bottomPanel;
    }


    /*

    public synchronized void switchContent(Views view){

        switch (view) {
            case playlist:
                if (this.getCenter()!=playlistContent){
                    this.setCenter(playlistContent);
                }
            case player:
                if (this.getCenter()!=playerContent){
                    this.setCenter(playerContent);
                }
        }
    }
    */

}
