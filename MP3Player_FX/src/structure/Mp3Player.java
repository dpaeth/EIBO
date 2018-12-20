package structure;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Mp3Player {

    SimpleMinim minim = new SimpleMinim(true);
    SimpleAudioPlayer audioPlayer;
    Mp3File aktSong;

    public Mp3Player(Mp3File firstSong){
        this.aktSong = firstSong;
    }

    public void setAktSong(Mp3File song){
        this.aktSong = song;
    }

    public void play(){
        this.audioPlayer = minim.loadMP3File(aktSong.getFilename());
        audioPlayer.play();
    }

/*
    public void play(Mp3File song) {
        this.aktSong = song;
        this.audioPlayer = minim.loadMP3File(aktSong.getFilename());
        audioPlayer.play();
    }
*/

    public void play(String filename) {
        this.audioPlayer = minim.loadMP3File(filename);
        audioPlayer.play();
    }

    public void pause() {
        audioPlayer.pause();
    }

    public void stop() {
        audioPlayer.pause();
        audioPlayer.rewind();
    }

    public void info() {
        System.out.println("Dateiname: " + aktSong.getFilename());
            System.out.println("Länge: " + aktSong.getLengthInSeconds() + " Sekunden");
            System.out.println("Bitrate: " + aktSong.getBitrate() + " kbps");
            if (aktSong.hasId3v1Tag()) {
                ID3v1 id3v1Tag = aktSong.getId3v1Tag();
                System.out.println("Track: " + id3v1Tag.getTrack());
                System.out.println("Artist: " + id3v1Tag.getArtist());
                System.out.println("Title: " + id3v1Tag.getTitle());
                System.out.println("Album: " + id3v1Tag.getAlbum());
                System.out.println("Year: " + id3v1Tag.getYear());
                System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
                System.out.println("Comment: " + id3v1Tag.getComment());
            } else System.out.println("keine Id3v1 Tags vorhanden!");
            if (aktSong.hasId3v2Tag()) {
                ID3v2 id3v2tag = aktSong.getId3v2Tag();
                System.out.println("BPM "+id3v2tag.getBPM());
                System.out.println("Key "+id3v2tag.getKey());

            }

    }

    public byte[] getCover() throws IOException {
        if (aktSong.hasId3v2Tag()) {
            ID3v2 id3v2tag = aktSong.getId3v2Tag();
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

}


