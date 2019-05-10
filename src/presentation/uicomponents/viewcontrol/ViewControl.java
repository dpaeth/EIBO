package presentation.uicomponents.viewcontrol;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import presentation.application.Main;
import structure.Views;

public class ViewControl extends HBox {
    private final Main application;
    private final Button playlistview;
    private final Button playerview;
    private final Button coolview;
    Button frostview;
    Button kidview;

    public ViewControl(Main application){
        this.application = application;

        this.playlistview = new Button();
        playlistview.setUserData("list");
        playlistview.setId("list");

        this.playerview = new Button();
        playerview.setUserData("player");
        playerview.setId("player");


        this.coolview = new Button();
        coolview.setUserData("cool");
        coolview.setId("cool");

        /*
        this.frostview = new Button();
        frostview.setUserData("frost");
        frostview.setId("frost");

        this.kidview = new Button();
        kidview.setUserData("kid");
        kidview.setId("kid");
        */


        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(playlistview, playerview, coolview);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        initialize();

    }

    private void initialize(){
        playlistview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.playlist));
        playerview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.player));
        coolview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.cool));
        //frostview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.frost));
        //kidview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.kid));

    }
}
