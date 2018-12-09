package presentation.uicomponents.cover;

import com.mpatric.mp3agic.ID3v2;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;
import java.io.ByteArrayInputStream;
import javafx.scene.image.Image;

public class Cover extends StackPane {
    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;
    private Image img;

    public Cover(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {
        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;
        ImageView iv1 = new ImageView();

        try {
            if (defPlaylist.getAktSong().hasId3v2Tag()){
                ID3v2 id3v2Tag = defPlaylist.getAktSong().getId3v2Tag();
                byte[] cover = id3v2Tag.getAlbumImage();

                if (cover!=null) {
                    img = new Image(new ByteArrayInputStream(cover));
                }
                iv1.setImage(img);
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        this.setPadding(new Insets(5));
        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(iv1);




    }

}
