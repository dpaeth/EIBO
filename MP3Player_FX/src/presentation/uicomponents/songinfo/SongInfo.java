package presentation.uicomponents.songinfo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;
import structure.Track;

public class SongInfo extends VBox {

    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;
    private Label songname;
    private Label artistname;
    private Label albumname;
    public Track aktTrack;

    public SongInfo (Playlist defPlaylist, Mp3Player player, PlaylistManager manager){
        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;
        this.aktTrack = defPlaylist.getAktSong();

        songname = new Label();
        songname.setId("song");

        artistname = new Label();
        artistname.setId("artist");

        albumname = new Label();
        albumname.setId("playlist");
        setInfo(aktTrack);


        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPadding(new Insets(20));
        this.getChildren().addAll(songname, artistname, albumname);



    }
    public void setInfo(Track aktTrack) {
        songname.setText(aktTrack.getName());
        artistname.setText(aktTrack.getInterpret());
        albumname.setText(aktTrack.getAlbum());
    }
}