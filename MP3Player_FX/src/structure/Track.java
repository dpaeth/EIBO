package structure;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.Mp3File;

public class Track {

    Mp3File song;
    String name;
    String interpret;
    String album;
    ID3v1 id3v1tag;

    public Track(Mp3File song) {
        this.song = song;
        this.id3v1tag = song.getId3v1Tag();
        this.name = id3v1tag.getTitle();
        this.interpret = id3v1tag.getArtist();
        this.album = id3v1tag.getAlbum();
    }

    public String getName() {
        return name;
    }
    public String getInterpret() {
        return interpret;
    }

    public String getAlbum() {
        return album;
    }
    public Mp3File getSong() {
        return song;
    }
}
