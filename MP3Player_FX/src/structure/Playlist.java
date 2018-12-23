package structure;

import com.mpatric.mp3agic.Mp3File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Playlist {
    private String name;
    private ArrayList<Track> songs;
    private int aktSong, size;


    //KONSTRUKTOREN
    public Playlist() {
        //Erstellt default-list mit allen vorhandenen Songs in default Directory
        songs = new ArrayList<>();

        File file = new File("default2.m3u");
        File directory = new File("MP3Player_FX/songs");
        File[] content = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));

        if (content != null) Arrays.sort(content);
        //songs = fileToMp3Hash(content);
        songs = fileToTrackArrayList(content);

        try {
            file.createNewFile();
            writePlaylist(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = file.getName();
        this.size = songs.size();
    }

    public Playlist(String playlistname, String songdirectory) {
        songs = new ArrayList<>();

        File file = new File(playlistname);
        File directory = new File(songdirectory);
        File[] content = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));

        if (content != null) Arrays.sort(content);
        songs = fileToTrackArrayList(content);

        try {
            file.createNewFile();
            writePlaylist(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = file.getName();
        this.size = songs.size();

    }

    //ERSTELLEN + FÜLLEN DER DATEI
    private void writePlaylist(File file) throws IOException {
        if (songs != null) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Track song : songs) {
                writer.write(song.getFilename());
                writer.newLine();
            }
            writer.close();
        }
    }

    private ArrayList<Track> fileToTrackArrayList(File[] files) {
        //HashMap<Integer, Mp3File> content = new HashMap<>();
        ArrayList<Track> content = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                try {
                    content.add(new Track(new Mp3File(file)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return content;
        }
        return null;
    }


    //GETTER
    public String getName() {
        return this.name;
    }

    public int getAktSongNumber(){
        return aktSong;
    }

    public Track getAktSong() {
        return songs.get(aktSong);
    }

    public ArrayList<Track> getAll() {
        return this.songs;
    }

    public ArrayList<Track> getSongs() {
        return this.songs;
    }

    public ArrayList<String> getSongNames() {
        ArrayList<String> songnames = new ArrayList<>();
        if (songs != null) {
            for (int i = 0; i < size; i++) {
                songnames.add(songs.get(i).getTitle());
            }
            return songnames;
        }
        return null;
    }

    public String getSongName(int i) {
        return songs.get(i).getFilename();
    }

    public Mp3File getMp3(int i) {
        return songs.get(i).getSong();
    }

    public Track getSong(int i) {
        return songs.get(i);
    }

    int getSize() {
        return songs.size();
    }

}
