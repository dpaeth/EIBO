package presentation.uicomponents.sidepanel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SidePanel extends VBox {
    Parent choose;

    public SidePanel(){
        this.setMinSize(30, 610);
        this.setMaxWidth(30);
        choose = null;
        try {
            choose = FXMLLoader.load(this.getClass().getResource("./choose.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        };
        this.getChildren().addAll(choose);
    }
}
