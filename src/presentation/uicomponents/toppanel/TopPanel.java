package presentation.uicomponents.toppanel;

import javafx.scene.layout.StackPane;
import presentation.application.Main;
import presentation.uicomponents.viewcontrol.ViewControl;

public class TopPanel extends StackPane {

    private final ViewControl viewcontrol;

    public TopPanel(Main application) {
        this.setMinSize(1000, 45);
        this.setMaxHeight(45);
        this.viewcontrol = new ViewControl(application);
        this.getChildren().addAll(viewcontrol);
        this.setId("toppanel");
        this.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());
    }

    public ViewControl getViewcontrol(){
        return this.viewcontrol;
    }
}
