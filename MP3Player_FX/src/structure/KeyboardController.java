package structure;

import de.hsrm.mi.prog.util.StaticScanner;

public class KeyboardController {
    private String[] line;
    private Commands command;
    private Mp3Player player;
    private PlaylistManager manager;


    public KeyboardController(PlaylistManager manager, Mp3Player player){
        this.player = player;
        this.manager = manager;
    }

    public void commands(){
        System.out.print("Mögliche Eingaben: ");
        for (Commands command:Commands.values()){
            System.out.print(command.toString()+", ");
        }
        System.out.print("\n");

    }

    public void info(){
        manager.playlistInfo();
        manager.songInfo();
        manager.settingsInfo();
    }

    public void start(){
        System.out.println("Backend.KeyboardController gestartet.");
        System.out.println();
        info();
        commands();


        do {
            do {
                input();
            } while(command==null);
            run(command, line);
        } while(!command.equals(Commands.quit));
        player.stop();
        System.out.println("MP3 Player ausgeschaltet.");
    }

    public void input(){
        String input = StaticScanner.nextString();
        line = input.split(" ");

        try{
            command = Commands.valueOf(line[0]);
        } catch (IllegalArgumentException iae){
            System.out.println("Befehl nicht gefunden, bitte erneut versuchen.");
        }
    }

    public void run(Commands command, String[] line){
        if (line.length > 2) {
            System.out.println("Befehl darf nicht aus mehr als zwei Wörtern bestehen.");
        } else if (line.length == 1) {
            switch (command){
                case play:
                    player.play(); break;
                case pause:
                    player.pause();break;
                case stop:
                    player.stop();break;
                case info:
                    player.info();break;
                case skip:
                    manager.getAktPlaylist().skipToNextSong();
                    manager.songInfo();
                    break;
                case shuffle:
                    manager.getAktPlaylist().toggleShuffle();
                    manager.settingsInfo();
                    break;
                case repeat:
                    manager.getAktPlaylist().toggleRepeat();
                    manager.settingsInfo();
                    break;
                case back:
                    manager.getAktPlaylist().skipToPrevSong();
                    manager.songInfo();
                    break;
                case settings:
                    info();break;
                case commands:
                    commands();break;
            }
        } else if (line.length == 2)
            switch (command){
                //case play: player.play("songs/" + line[1] + ".mp3"); break;
                case volume: player.volume(Float.parseFloat(line[1])); break;
                //case set:
            }
    }




}
