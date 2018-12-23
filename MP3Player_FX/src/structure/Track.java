package structure;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

public class Track {

    private Mp3File song;
    private boolean hasid3v1, hasid3v2;
    private ID3v1 id3v1;
    private ID3v2 id3v2;
    private String filename;
    private String title;
    private String artist;
    private String album;
    private String comment;
    private String year;
    private String track;
    private String key;
    private int genre, length, bpm;

    //Kondstruktor
    public Track(Mp3File song) {
        this.song = song;
        this.filename = song.getFilename();

        if (song.hasId3v2Tag()) {
            hasid3v2 = true;
            this.id3v2 = song.getId3v2Tag();
            this.title = id3v2.getTitle();
            this.artist = id3v2.getArtist();
            this.album = id3v2.getAlbum();
            this.comment = id3v2.getComment();

            this.year = id3v2.getYear();
            this.genre = id3v2.getGenre();
            this.track = id3v2.getTrack();
            this.length = id3v2.getLength();

            this.bpm = id3v2.getBPM();
            this.key = id3v2.getKey();

        } else hasid3v2 = false;

        if (song.hasId3v1Tag() && !hasid3v2) {
            hasid3v1 = true;
            this.id3v1 = song.getId3v1Tag();
            this.title = id3v1.getTitle();
            this.artist = id3v1.getArtist();
            this.album = id3v1.getAlbum();
            this.comment = id3v1.getComment();

            this.year = id3v1.getYear();
            this.genre = id3v1.getGenre();
            this.track = id3v1.getTrack();

        } else hasid3v1 = false;
    }

    public String getYear() {
        if (year != null) {
            return this.year;
        } else return "kein id3-Tag vorhanden";    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTrack() {
        if (track != null) {
            return this.track;
        } else return "kein id3-Tag vorhanden";    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getKey() {
        if (key != null) {
            return this.key;
        } else return "kein id3-Tag vorhanden";    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getGenre() {
        if (genre != 0) {
            return this.genre;
        } else return 0;    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBpm() {
        if (bpm != 0) {
            return this.bpm;
        } else return 0;    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public String getFilename() {
        return this.filename;
    }

    public Mp3File getSong() {
        return this.song;
    }

    public String getTitle() {
        if (title != null) {
            return this.title;
        } else return this.filename;
    }

    public String getArtist() {
        if (artist != null) {
            return this.artist;
        } else return "kein id3-Tag vorhanden";
    }

    public String getAlbum() {
        if (album != null) {
            return this.album;
        } else return "kein id3-Tag vorhanden";
    }

    public String getComment(){
        if (comment != null) {
            return this.comment;
        } else return "kein id3-Tag vorhanden";
    }

    public Image getCover() {
        if (id3v2.getAlbumImage() != null) {
            try {
                Image image =new Image(new ByteArrayInputStream(id3v2.getAlbumImage()));
                if (image != null){
                    return image;
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        } return null;
    }

    public int getLength(){
        if (length != 0) {
            return length;
        } else return 0;
    }
}




