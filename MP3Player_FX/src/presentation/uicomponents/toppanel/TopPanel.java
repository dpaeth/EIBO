package presentation.uicomponents.toppanel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class TopPanel extends HBox {

    private String toggleString;
    public Button switchPlayer;
    public Button switchList;


    public TopPanel() {

        //this.setAlignment(Pos.TOP_CENTER);
        this.setMinSize(1000, 60);
        this.setMaxHeight(60);
        this.setStyle("-fx-background-color: #2E2E30;");

        switchPlayer = new Button("PLAYER");
        switchPlayer.setUserData("PLAYER");
        switchList = new Button("LIST");


        this.getChildren().addAll(switchPlayer,switchList);



    }

    public String getToggleString() {
        return toggleString;
    }

}
