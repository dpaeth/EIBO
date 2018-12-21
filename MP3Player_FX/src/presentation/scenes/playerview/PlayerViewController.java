package presentation.scenes.playerview;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import presentation.application.Main;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.songinfo.SongInfo;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;
import structure.Track;

public class PlayerViewController {

    private PlayerView view;
    private PlayerControl playerControl;
    private BottomPanel bottomPanel;
    private TopPanel topPanel;
    private Mp3Player player;
    private Playlist defPlaylist;
    private PlaylistManager manager;
    private PlayerContent playerContent;
    private Main application;
    private boolean isPlaying;
    private Button switchPlayer;
    private Button switchList;
    private Cover cover;
    private SongInfo songinfo;
    SimpleObjectProperty song;

    public PlayerViewController(Mp3Player player, Playlist defPlaylist,PlaylistManager manager,Main application){

        this.player = player;
        this.defPlaylist = defPlaylist;
        this.manager = manager;
        this.view = new PlayerView(defPlaylist,player,manager);
        this.bottomPanel = view.bottomPanel;
        this.topPanel = view.topPanel;
        this.playerControl = bottomPanel.playercontrol;
        this.topPanel = view.topPanel;
        this.switchList = view.topPanel.switchList;
        this.switchPlayer = topPanel.switchPlayer;
        this.playerContent = view.playerContent;
        this.cover = playerContent.cover;
        this.songinfo = playerContent.songinfo;
        this.application = application;
        this.isPlaying = player.isPlaying();
        this.song = playerContent.cover.actSong;
        initialize();
    }

    public void initialize(){
        playerControl.play.addEventHandler(ActionEvent.ACTION, e -> {

            if (!isPlaying) {
                new Thread() {
                    public void run() {
                        player.play();
                        isPlaying = true;
                    }
                }.start();
            }
        });
        playerControl.stop.addEventHandler(ActionEvent.ACTION, e -> {
            player.stop();
        });
        playerControl.skip.addEventHandler(ActionEvent.ACTION, e -> skipPlay());
        playerControl.back.addEventHandler(ActionEvent.ACTION, e -> backPlay());
        switchPlayer.addEventFilter(ActionEvent.ACTION, e -> application.switchView("PLAYER"));
        switchList.addEventFilter(ActionEvent.ACTION, e -> application.switchView("LIST"));
        song.addListener(new ChangeListener<Track>() {
            @Override
            public void changed(ObservableValue<? extends Track> observableValue, Track old, Track t1) {
                playerContent.songinfo.setInfo(t1);
                playerContent.cover.setCover(t1);
            }
        });




    }
    public void skipPlay() {
        if(isPlaying) {
            player.stop();
        }
        defPlaylist.skipToNextSong();
        song.set(defPlaylist.getSong(defPlaylist.getAktSongIndex()));
        player.play();
    }

    public void backPlay() {
        if(isPlaying) {
            player.stop();
        }
        defPlaylist.skipToPrevSong();
        song.set(defPlaylist.getSong(defPlaylist.getAktSongIndex()));
        player.play();
    }

    public PlayerView getView() {
        return view;
    }


}

