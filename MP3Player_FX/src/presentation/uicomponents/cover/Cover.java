package presentation.uicomponents.cover;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import structure.Track;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author Dorian Paeth
 * @author Julian Gräber
 * UI-Komponente - Cover
 */

public class Cover extends StackPane {
    private Track aktTrack;
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    private Image defImage;
    ImageView iv1 = new ImageView();

    //Konstruktor des Covers
    public Cover(Track aktTrack) {
        this.aktTrack = aktTrack;
        this.image.setValue(aktTrack.getCover());

        try {
            BufferedImage def = ImageIO.read(new File("MP3Player_FX/src/assets/images/nocover.jpg"));
            this.defImage = SwingFXUtils.toFXImage(def, null);
        } catch (Exception e){
            e.printStackTrace();
        }

        if (image.get() == null) {
            image.setValue(defImage);
            iv1.setImage(image.getValue());
        } else {
            iv1.setImage(image.getValue());
        }

        iv1.setFitHeight(350);
        iv1.setFitWidth(350);

        this.getChildren().addAll(iv1);
    }

    //Cover wird gesetzt
    public void setImage(Image img){
        image.setValue(img);
        iv1.setImage(image.getValue());
    }

    //falls kein Cover vorhanden, StandardCover setzen
    public void setDefImage(){
        image.setValue(defImage);
        iv1.setImage(image.getValue());
    }
}
