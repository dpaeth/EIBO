package presentation.uicomponents.viewcontrol;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import presentation.application.Main;
import structure.Views;

public class ViewControl extends HBox {
    Main application;
    Button playlistview, playerview, graphicsview, walkview;

    public ViewControl(Main application){
        this.application = application;

        this.playlistview = new Button();
        playlistview.setUserData("list");
        playlistview.setId("list");

        this.playerview = new Button();
        playerview.setUserData("player");
        playerview.setId("player");

        this.graphicsview = new Button();
        graphicsview.setUserData("graph");
        graphicsview.setId("graph");

        this.walkview = new Button();
        walkview.setUserData("walk");
        walkview.setId("walk");


        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(playlistview, playerview, graphicsview, walkview);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        initialize();

    }

    public void initialize(){
        playlistview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.playlist));
        playerview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.player));
        graphicsview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.graphics));
        walkview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.walk));

    }
}
