package structure;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Verwaltet alle Playlists, die das Programm benötigt, in einer ObservableArrayList
 */
public class PlaylistManager {
    private final IntegerProperty aktPlaylist = new SimpleIntegerProperty();
    private final ObservableList<Playlist> playlists = FXCollections.observableArrayList(new ArrayList<>());

    /**
     * Standardplaylist wird dem Konstruktor mitgegeben
     * @param playlist Standardplaylist
     */
    public  PlaylistManager (Playlist playlist){
        this.playlists.add(playlist);
        //this.aktPlaylist.setValue(0);
    }

    public void addPlaylist (Playlist playlist){
        playlists.add(playlist);
    }

    public Playlist getPlaylist (int index){
        return playlists.get(index);
    }

    public void setAktPlaylist (int index){
        aktPlaylist.setValue(index);
    }

    public Playlist getAktPlaylist(){
        return this.playlists.get(aktPlaylist.get());
    }

    public ArrayList<String> getPlaylistNames(){
        ArrayList<String> playlistnames = new ArrayList<>();
        for (Playlist playlist : playlists) {
            playlistnames.add(playlist.getName());
        }
        return playlistnames;
    }

    public IntegerProperty aktPlaylistProperty() {
        return aktPlaylist;
    }

    /*
    public void savePlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }
    public void deletePlaylist(Playlist playlist){
        this.playlists.remove(playlist);
    }
    */
}
