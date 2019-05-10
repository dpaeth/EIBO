package structure;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.*;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.chart.*;
import javafx.scene.media.*;
import javafx.util.Duration;
import sources.ColorThief;
import java.io.File;
import java.util.Objects;

public class Mp3Player {

    private MediaPlayer player;
    private Playlist aktPlaylist;
    private Track aktSong;
    private final double DROPDOWN = -0.5;
    private static final double INTERVAL = 0;

    long prevTime = System.currentTimeMillis();

    private final IntegerProperty aktSongIndex = new SimpleIntegerProperty();
    private final IntegerProperty playlistSize = new SimpleIntegerProperty();
    private final IntegerProperty aktSongLength = new SimpleIntegerProperty();

    private final BooleanProperty shuffle = new SimpleBooleanProperty();
    private final BooleanProperty repeat = new SimpleBooleanProperty();
    private final BooleanProperty playing = new SimpleBooleanProperty();

    private final StringProperty aktSongName = new SimpleStringProperty();
    private final StringProperty aktPlaylistName = new SimpleStringProperty();

    private final DoubleProperty volume = new SimpleDoubleProperty();
    private final DoubleProperty position = new SimpleDoubleProperty();
    private final DoubleProperty duration = new SimpleDoubleProperty();

    private final IntegerProperty r1 = new SimpleIntegerProperty();
    private final IntegerProperty g1 = new SimpleIntegerProperty();
    private final IntegerProperty b1 = new SimpleIntegerProperty();
    private final IntegerProperty r2 = new SimpleIntegerProperty();
    private final IntegerProperty g2 = new SimpleIntegerProperty();
    private final IntegerProperty b2 = new SimpleIntegerProperty();
    private final IntegerProperty r3 = new SimpleIntegerProperty();
    private final IntegerProperty g3 = new SimpleIntegerProperty();
    private final IntegerProperty b3 = new SimpleIntegerProperty();


    private final StringProperty album = new SimpleStringProperty();
    private final StringProperty year = new SimpleStringProperty();
    private final StringProperty key = new SimpleStringProperty();
    private final StringProperty genre = new SimpleStringProperty();
    private final IntegerProperty bpm = new SimpleIntegerProperty();
    private final StringProperty track = new SimpleStringProperty();


    private final IntegerProperty turn = new SimpleIntegerProperty();
    private SpektrumListener spektrumListener;
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis(-40,40,10);
    private final BarChart<String, Number> spektrum = new BarChart<>(xAxis,yAxis);
    private int bands;
    private XYChart.Data[] series1Data1;
    private XYChart.Data[] series1Data2;

    public Mp3Player(Playlist aktPlaylist){
        this.aktPlaylist = aktPlaylist;
        this.aktPlaylistName.setValue(aktPlaylist.getName());

        loadSong(0);

        this.playing.setValue(false);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1),
                ae -> refreshPos()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        spektrumListener = new SpektrumListener();
        player.setAudioSpectrumListener(spektrumListener);
        turn.setValue(0);
    }

    private void loadSong(int index){
        spektrum.getData().clear();
        this.aktSong = aktPlaylist.getSong(index);
        this.aktSongIndex.setValue(index);
        this.aktSongName.setValue(aktSong.getFilename());
        this.playlistSize.setValue(aktPlaylist.getSize());
        this.player = new MediaPlayer(new Media(new File(aktSong.getFilename()).toURI().toString()));
        player.setVolume(volume.get());
        this.bands = player.getAudioSpectrumNumBands();
        player.volumeProperty().bind(volume);

        this.album.setValue(aktSong.getAlbum());
        this.year.setValue(aktSong.getYear());
        this.key.setValue(aktSong.getKey());
        this.genre.setValue(aktSong.getGenre());
        this.bpm.setValue(aktSong.getBpm());
        this.track.setValue(aktSong.getTrack());



        this.position.setValue(0);
        refreshPos();
        loadSpektrum();
        loadDomColor();
    }

    private void loadDomColor() {
        if (aktSong.getCover()!=null){
            int[][] domColors = ColorThief.getPalette(SwingFXUtils.fromFXImage(aktSong.getCover(), null), 3);
            r1.setValue(domColors[0][0]);
            g1.setValue(domColors[0][1]);
            b1.setValue(domColors[0][2]);
            r2.setValue(domColors[1][0]);
            g2.setValue(domColors[1][1]);
            b2.setValue(domColors[1][2]);
            r3.setValue(domColors[2][0]);
            g3.setValue(domColors[2][1]);
            b3.setValue(domColors[2][2]);
        }


    }

    public void loadSpektrum() {
        this.aktSongLength.setValue(duration.getValue());
        spektrumListener = new SpektrumListener();
        player.setAudioSpectrumListener(spektrumListener);

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1Data1 = new XYChart.Data[bands];
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series1Data2 = new XYChart.Data[bands];

        for (int i = 0; i < 64; i++) {
            series1Data1[i] = new XYChart.Data<>(Integer.toString(i + 1), 50);
            series1.getData().add(series1Data1[i]);
        }
        for (int i = 0; i < 64; i++) {
            series1Data2[i] = new XYChart.Data<>(Integer.toString(i + 1), 50);
            series2.getData().add(series1Data2[i]);
        }
        spektrum.getData().addAll(series1,series2);
    }

    private class SpektrumListener implements AudioSpectrumListener {
        float sum;
        @Override
        public void spectrumDataUpdate(double timestamp, double duration, float[] magnitudes, float[] phases) {
            Platform.runLater(() -> {
                    for (int i = 0; i < 64; i++) {
                        series1Data1[i].setYValue(magnitudes[i] + 60);
                        series1Data2[i].setYValue(-(magnitudes[i]+60));
                            sum +=(float) series1Data1[i].getYValue();
                    }
                if (sum > 900) {
                            turn.setValue(sum);
                        }
                sum = 0;
            });
        }
    }

    public IntegerProperty getR1(){
        return this.r1;
    }
    public IntegerProperty getG1(){
        return this.g1;
    }
    public IntegerProperty getB1(){
        return this.b1;
    }
    public IntegerProperty getR2(){
        return this.r2;
    }
    public IntegerProperty getB2(){
        return this.b2;
    }
    public IntegerProperty getG2(){
        return this.g2;
    }
    public IntegerProperty getR3(){
        return this.r3;
    }
    public IntegerProperty getB3(){
        return this.b3;
    }
    public IntegerProperty getG3(){
        return this.g3;
    }

    public BarChart<String, Number> getSpektrum(){
        spektrum.getData().clear();
        return this.spektrum;
    }

    public void play(){
        player.play();
        playing.setValue(true);
    }

    public BooleanProperty isPlaying() {
        return this.playing;
    }

    public void seek(double s){
        player.seek(Duration.seconds(s));
    }
    public void play(double ms){
        player.setStartTime(new Duration(ms));
        player.play();
        playing.setValue(true);
    }

    public void playTrack(int index){
        player.pause();
        playing.setValue(false);

        loadSong(index);

        player.play();
        playing.setValue(true);
    }

    public void pause() {
        player.pause();
        playing.setValue(false);
    }

    public void stop() {
        playing.setValue(false);
        player.stop();
    }

    public void skip() {
        player.pause();
        playing.setValue(false);

        int s = aktSongIndex.get();
        int size = playlistSize.get();
        if (!repeat.getValue()){
            if (!shuffle.getValue()) {
                if (s == (size-1)) s = 0;
                else s++;
            } else {
                s = (int)(Math.random()*size);
            }
        }

        loadSong(s);

        player.play();
        playing.setValue(true);

    }

    public void back() {
        player.pause();
        playing.setValue(false);

        int s = aktSongIndex.get();
        int size = playlistSize.get();
        if (!repeat.getValue()){
            if (s == 0) s = (size-1);
            else s--;
        }

        loadSong(s);

        player.play();
        playing.setValue(true);
    }

    public void shuffle(){
        if (shuffle.getValue()) shuffle.setValue(false);
        else shuffle.setValue(true);
    }

    public void repeat(){
        if (repeat.getValue()) repeat.setValue(false);
        else repeat.setValue(true);
    }

    private void refreshPos(){
        if (player.totalDurationProperty().get()!=null) this.duration.setValue(player.getTotalDuration().toSeconds());
        player.totalDurationProperty().addListener((observableValue, d, t1) -> duration.setValue(player.getTotalDuration().toSeconds()));
        player.currentTimeProperty().addListener((observableValue, ov, nv) -> {
            long currTime = System.currentTimeMillis();
            if (Double.compare((currTime - prevTime), 250) > 0) {
                position.setValue(player.getCurrentTime().toSeconds());
                prevTime = currTime;
            }
        });
        player.setOnEndOfMedia(() ->this.skip());
    }

    public int getAktSongLength() {
        return aktSongLength.get();
    }

    public Track getAktTrack(){
        return aktPlaylist.getSong(aktSongIndex.get());
    }

    public MediaPlayer getAudioPlayer(){
        return this.player;
    }

    public IntegerProperty turn() {
        return turn;
    }

    public IntegerProperty aktSongLengthProperty() {
        return aktSongLength;
    }

    public IntegerProperty aktSongIndexProperty() {
        return aktSongIndex;
    }

    public BooleanProperty shuffleProperty(){
        return shuffle;
    }

    public BooleanProperty repeatProperty(){
        return repeat;
    }

    public DoubleProperty volumeProperty() {
        return this.volume;
    }

    public StringProperty aktSongNameProperty(){
        return aktSongName;
    }

    public void setAktPlaylist(Playlist aktPlaylist){
        this.aktPlaylist = aktPlaylist;
        this.aktPlaylistName.setValue(aktPlaylist.getName());
        this.playlistSize.setValue(aktPlaylist.getSize());

        loadSong(0);

        this.playing.setValue(false);
    }

    public void setVolume(double value) {
        this.player.setVolume(value);
    }

    public double getVolume(){
        return this.player.getVolume();
    }

    public DoubleProperty getPosition(){
        return this.position;
    }
    public DoubleProperty getDuration(){
        return this.duration;
    }

    public StringProperty getAlbum(){
        return this.album;
    }
    public StringProperty getYear(){
        return this.year;
    }
    public StringProperty getKey(){
        return this.key;
    }
    public StringProperty getGenre(){
        return this.genre;
    }
    public IntegerProperty getBpm(){
        return this.bpm;
    }
    public StringProperty getTrack(){
        return this.track;
    }
}


