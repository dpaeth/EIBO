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
    private int aktSong, size;
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
        this.name = file.getName();
        this.aktSong = 0;
        this.size = songs.size();
        this.shuffle = false;
        this.repeat = false;

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

    public HashMap<Integer, Mp3File> fileToMp3Hash(File[] files){
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
        if (repeat)  {
            return;
        }
        else if (shuffle) {
            aktSong = (int)(Math.random()* songs.size());

            return;
        }
        else {
            if (aktSong<(size-1)){
                aktSong++;
            } else if (aktSong == (size-1)){
                aktSong = 0;
            }
        }

    }

    public void skipToPrevSong(){
        if (repeat)  {
            return;
        }
        else if (shuffle) {
            aktSong = (int)(Math.random()* songs.size());
            return;
        }
        else {
            if (aktSong == (0)) {
                aktSong = (size - 1);
            }
            else if (aktSong<size){
                aktSong--;
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

    public void setAktSong(int i){
        this.aktSong = i;
    }

    //GETTER

    public String getName(){
        return this.name;
    }

    public Mp3File getAktSong(){
        return songs.get(aktSong);
    }

    public HashMap<Integer, Mp3File> getAll() {
        return this.songs;
    }

    public ArrayList<Track> getPlaylist() {
        return this.playlist;
    }

    public Mp3File getSong(int i){
       return songs.get(i);
    }


    public boolean isShuffle(){
        return shuffle;
    }

    public boolean isRepeat() {
        return repeat;
    }
}
