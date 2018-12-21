package structure;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

import java.io.IOException;

public class Mp3Player {

    SimpleMinim minim = new SimpleMinim(true);
    SimpleAudioPlayer audioPlayer;
    Track aktSongTrack;
    int aktSongIndex;
    Playlist playlist;
    boolean isPlaying;

    public Mp3Player(Playlist playlist){
        this.playlist = playlist;
        aktSongIndex = playlist.getAktSongIndex();
        aktSongTrack = playlist.getSong(aktSongIndex);
        isPlaying = false;

    }

    public void setAktSongTrack (int aktSongIndex){
        this.aktSongTrack = playlist.getSong(aktSongIndex);
    }

    public void play(){
        if(isPlaying) {
            stop();
            audioPlayer.rewind();
        }
        this.audioPlayer = minim.loadMP3File(playlist.getSong(playlist.getAktSongIndex()).song.getFilename());
        audioPlayer.play();
        isPlaying = true;

    }

/*
    public void play(Mp3File song) {
        this.aktSong = song;
        this.audioPlayer = minim.loadMP3File(aktSong.getFilename());
        audioPlayer.play();
    }
*/

    /*public void play(int aktSongIndex) {
        this.audioPlayer = minim.loadMP3File(aktSongIndex.);
        audioPlayer.play();
    }*/

    public void pause() {
        audioPlayer.pause();
        isPlaying = false;
    }

    public void stop() {
        audioPlayer.pause();
        audioPlayer.rewind();
        isPlaying = false;
    }

    public void info() {
        System.out.println("Dateiname: " + aktSongTrack.getSong().getFilename());
            System.out.println("Länge: " + aktSongTrack.getSong().getLengthInSeconds() + " Sekunden");
            System.out.println("Bitrate: " + aktSongTrack.song.getBitrate() + " kbps");
            if (aktSongTrack.getSong().hasId3v1Tag()) {
                ID3v1 id3v1Tag = aktSongTrack.song.getId3v1Tag();
                System.out.println("Track: " + id3v1Tag.getTrack());
                System.out.println("Artist: " + id3v1Tag.getArtist());
                System.out.println("Title: " + id3v1Tag.getTitle());
                System.out.println("Album: " + id3v1Tag.getAlbum());
                System.out.println("Year: " + id3v1Tag.getYear());
                System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
                System.out.println("Comment: " + id3v1Tag.getComment());
            } else System.out.println("keine Id3v1 Tags vorhanden!");
            if (aktSongTrack.getSong().hasId3v2Tag()) {
                ID3v2 id3v2tag = aktSongTrack.song.getId3v2Tag();
                System.out.println("BPM "+id3v2tag.getBPM());
                System.out.println("Key "+id3v2tag.getKey());

            }

    }

    public byte[] getCover() throws IOException {
        if (aktSongTrack.getSong().hasId3v2Tag()) {
            ID3v2 id3v2tag = aktSongTrack.song.getId3v2Tag();
            byte[] b = id3v2tag.getAlbumImage();

            return b;
        }
        return null;
    }


    public void volume(float value) {
        audioPlayer.setGain(value);
    }

    /*public String getAktSongInfo(){
        return aktSong.getFilename();
    }*/

    public boolean isPlaying() {
        return isPlaying;
    }
}


