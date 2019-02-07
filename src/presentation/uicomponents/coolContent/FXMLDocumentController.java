package presentation.uicomponents.coolContent;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FXMLDocumentController {
    @FXML
    private ImageView rotateImage;
    private ImageView arrowleft;
    private ImageView arrowright;
    private Timeline rotate;
    private String direct;

    public FXMLDocumentController() {
        this.rotate = new Timeline();
        rotate.setCycleCount(Timeline.INDEFINITE);
    }

    public void rotateImageWalkRight() {
        if(direct == "left") {
            rotate.stop();
        }
        Timeline rotateRight = new Timeline();
        rotateRight.setCycleCount(Timeline.INDEFINITE);
        direct = "right";
        DoubleProperty r = rotateImage.rotateProperty();
        rotateRight.getKeyFrames().addAll(
                new KeyFrame(new Duration(0), new KeyValue(r, 0)),
                new KeyFrame(new Duration(23000), new KeyValue(r, -360))
        );
        rotateRight.play();
    }
    public void rotateImageWalkLeft() {
        if(direct == "right") {
            rotate.stop();
        }
        direct = "left";
        DoubleProperty r = rotateImage.rotateProperty();
        rotate.getKeyFrames().addAll(
                new KeyFrame(new Duration(0), new KeyValue(r, 0)),
                new KeyFrame(new Duration(23000), new KeyValue(r, +360))
        );
        rotate.play();
    }
}
