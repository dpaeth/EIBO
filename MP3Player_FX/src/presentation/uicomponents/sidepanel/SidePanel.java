package presentation.uicomponents.sidepanel;

import javafx.scene.layout.VBox;

/**
 * @author Dorian Paeth
 * @author Julian Gräber
 * UI-Komponente - seitliche Panel des Players
 */

public class SidePanel extends VBox {

    public SidePanel(){
        this.setMinSize(30, 610);
        this.setMaxWidth(30);
        this.setStyle("-fx-background-color: #717171;");
    }
}
