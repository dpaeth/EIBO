package structure;

import java.util.HashMap;

public class PlaylistManager {

    private Playlist aktuell;
    private HashMap<String, Playlist> playlists = new HashMap<>();

/*
    public Backend.PlaylistManager (){
        this.aktuell = new Backend.Playlist();
    }
*/

    //KONSTRUKTOREN

    public  PlaylistManager (Playlist playlist){
        this.aktuell=playlist;
        this.playlists.put(playlist.getName(), playlist);
    }

    //GETTER

    public Playlist getPlaylist (String playlistname){
        for (int i=0; i<playlists.size(); i++) {
            if (playlists.get(i).toString().equalsIgnoreCase(playlistname)) {
                return playlists.get(i);
            }
        }
        return null;
    }

    public Playlist getAktPlaylist(){
        return this.aktuell;
    }

    public void settingsInfo(){
        String s, r;
        if (aktuell.isShuffle()) s="ON";
        else s="OFF";
        if (aktuell.isRepeat()) r="ON";
        else r="OFF";
        System.out.println("Aktuelle Einstellungen: Repeat: "+r+" | "+"Shuffle: "+s);
    }

    public void playlistInfo(){
        System.out.println("Aktuelle Backend.Playlist: "+aktuell.getName());
    }

    public void songInfo(){
        System.out.println("Aktueller Song: "+aktuell.getAktSong().getSong().getFilename());
    }



    public void savePlaylist(Playlist playlist){
        this.playlists.put(playlist.getName(), playlist);
    }

    public void deletePlaylist(Playlist playlist){
        this.playlists.remove(playlist);
    }
}
