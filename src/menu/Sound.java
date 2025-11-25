package menu;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *  This class is for play one music in the background while the program is running.
 */
public class Sound implements Serializable{

    private transient final String musicFile;
    private transient FloatControl gainControl;

    /**
     * One-args constructor for create a Sound object.
     *
     * @param file The filename of the song.
     */
    public Sound(String file)
    {
        musicFile = "./res/" + file + ".wav";
    }

    /**
     * This method starts playing the sound based on the filepath.
     */
    public void playSound()
    {
        try {
            File soundFile = new File(musicFile);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-40);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Sets the volume of the Music.
     *
     * @param value The float value for setting the volume.
     */
    public void setVolume(Float value)
    {
        gainControl.setValue(value);
    }
}
