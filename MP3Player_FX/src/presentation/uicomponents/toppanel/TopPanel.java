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

    private final ToggleGroup switchview;
    private String toggleString;


    public TopPanel() {

        //this.setAlignment(Pos.TOP_CENTER);
        this.setMinSize(1000, 60);
        this.setMaxHeight(60);
        this.setStyle("-fx-background-color: #2E2E30;");

        switchview  = new ToggleGroup();
        ToggleButton switchPlayer = new ToggleButton("PLAYER");
        switchPlayer.setUserData("PLAYER");
        switchPlayer.setToggleGroup(switchview);
        switchPlayer.setSelected(true);
        ToggleButton switchList = new ToggleButton("LIST");
        switchList.setToggleGroup(switchview);
        switchList.setUserData("VIEW");


        this.getChildren().addAll(switchPlayer,switchList);


        switchview.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if(t1 == null) {
                    toggleString = "PLAYER";
                } else {
                    toggleString = switchview.getSelectedToggle().getUserData().toString();
                }
            }
        });

    }

    public String getToggleString() {
        return toggleString;
    }

}
