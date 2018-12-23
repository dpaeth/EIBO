package presentation.uicomponents.toppanel;

import javafx.scene.layout.StackPane;
import presentation.uicomponents.viewcontrol.ViewControl;

/**
 * @author Dorian Paeth
 * @author Julian Gräber
 * UI-Komponente - seitliche Panel des Players
 */

public class TopPanel extends StackPane {

    private String toggleString;
    private ViewControl viewcontrol;

    //Konstruktor des Panels
    public TopPanel() {

        this.setMinSize(1000, 60);
        this.setMaxHeight(60);
        this.setStyle("-fx-background-color: #3E3E3E;");

        this.viewcontrol = new ViewControl();
        this.getChildren().addAll(viewcontrol);
    }

    //GETTER
    public ViewControl getViewcontrol(){
        return this.viewcontrol;
    }

}
