package structure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PlaylistManager {

    private int aktPlaylist;
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private ObservableList<Playlist> lists = FXCollections.observableArrayList(playlists);

    //KONSTRUKTOR
    public PlaylistManager (Playlist playlist){
        this.playlists.add(playlist);
        this.aktPlaylist = 0;
    }

    //Hinzufügen der Playlist
    public void addPlaylist (Playlist playlist){
        this.playlists.add(playlist);
    }

    //GETTER
    public Playlist getPlaylist (String playlistname){
        for (Playlist playlist : playlists) {
            if (playlist.toString().equalsIgnoreCase(playlistname)) {
                return playlist;
            }
        }
        return null;
    }

    public Playlist getPlaylist (int index){
        return playlists.get(index);
    }

    public Playlist getAktPlaylist(){
        return this.playlists.get(aktPlaylist);
    }

    public String getAktPlaylistName(){
        return this.playlists.get(aktPlaylist).getName();
    }

    public ArrayList<String> getPlaylistNames(){
        ArrayList<String> playlistnames = new ArrayList<>();
        if (playlists!=null){
            for (int i=0; i<playlists.size(); i++){
                playlistnames.add(playlists.get(i).getName());
            }
            return playlistnames;
        }
        return null;
    }

    public ObservableList<Playlist> getLists(){
        return this.lists;
    }

    //SETTER
    public void setAktPlaylist (int index){
        this.aktPlaylist = index;
    }

    public void savePlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }

    public void deletePlaylist(Playlist playlist){
        this.playlists.remove(playlist);
    }
}
