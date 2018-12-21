package structure;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Playlist {
    private String name;
    private HashMap<Integer, Mp3File> songs;

    private ArrayList<Track> playlist;
    private int aktSongIndex, size;
    private Track song;
    private boolean shuffle, repeat;

    //KONSTRUKTOREN

    public Playlist (){
        //Erstellt default-list mit allen vorhandenen Songs in default Directory

        playlist = new ArrayList<Track>();
        File file = new File("default.m3u");
        File directory = new File("MP3Player_FX/songs/");
        File[] content = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
        if (content!=null) Arrays.sort(content);
        songs = fileToMp3Hash(content);

        for(int i = 0; i < songs.size(); i++) {
            playlist.add(new Track(songs.get(i)));
        }
        try {
            createFile(file);
            writePlaylist(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = file.getName();
        aktSongIndex = 0;
        song = playlist.get(aktSongIndex);
        size = playlist.size();
        shuffle = false;
        repeat = false;

    }

    /*
    public Backend.list (String name, String directory){

        //Erstellt neue Backend.list mit gegebenem Namen und Verzeichnis
        this.file = new File(name);
        this.directory = new File(directory);

        try {
            createFile(file);
            writePlaylist();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    */

    //ERSTELLEN + FÜLLEN DER DATEI

    public void createFile(File file) throws IOException{
        file.createNewFile();

    }

    public void writePlaylist(File file) throws IOException{
        if (songs !=null){
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i=0; i<songs.size(); i++){
                writer.write(songs.get(i).toString());
                writer.newLine();
            }
            writer.close();
        }
    }

    public HashMap<Integer,Mp3File> fileToMp3Hash(File[] files){
        HashMap<Integer, Mp3File> content = new HashMap<>();
        for (int i=0; i<files.length; i++){
            try {
                content.put(i, new Mp3File(files[i]));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    public void skipToNextSong(){
        if (shuffle) {
            aktSongIndex = (int)(Math.random()* songs.size());
            return;
        }
        else {
            if (aktSongIndex <(size-1)){
                aktSongIndex++;
            } else if (aktSongIndex == (size-1)){
                aktSongIndex = 0;
            }
        }

    }

    public void skipToPrevSong(){
        if (shuffle) {
            aktSongIndex = (int)(Math.random()* songs.size());
            return;
        }
        else {
            if (aktSongIndex == (0)) {
                aktSongIndex = (size - 1);
            }
            else if (aktSongIndex <size){
                aktSongIndex--;
            }
        }
    }

    //SETTER + TOGGLER

    public void toggleShuffle(){
        if (!shuffle) shuffle = true;
        else shuffle = false;
    }

    public void toggleRepeat(){
        if (!repeat) repeat = true;
        else repeat = false;
    }

    public void setAktSongIndex(int i){
        this.aktSongIndex = i;
    }

    //GETTER

    public String getName(){
        return this.name;
    }

    public Track getSong(int aktSongIndex){
        return playlist.get(aktSongIndex);
    }
    public int getAktSongIndex() {
        return this.aktSongIndex;
    }
    public Track getAktSong(){return this.song;}

    public HashMap<Integer, Mp3File> getAll() {
        return this.songs;
    }

    public ArrayList<Track> getPlaylist() {
        return this.playlist;
    }

    public void setSong(int aktSongIndex) {
        this.song = playlist.get(aktSongIndex);
    }

    public boolean isShuffle(){
        return shuffle;
    }

    public boolean isRepeat() {
        return repeat;
    }
}
