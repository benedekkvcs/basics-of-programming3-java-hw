package menu;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class SoundTest
{
    @Test
    public void testConstructor()
    {
        Sound sound = new Sound("testFile");
        assertNotNull(sound);
    }

    @Test
    public void testPlaySound()
    {
        Sound sound = new Sound("testFile");
        try {
            sound.playSound();
        } catch (Exception e) {
            fail("Exception thrown during playSound: " + e.getMessage());
        }
    }

    @Test
    public void testSetVolumeOutOfRange()
    {
        Sound sound = new Sound("testFile");
        assertThrows(NullPointerException.class, () -> sound.setVolume(1.5f));
    }

    @Test
    public void testPlaySoundWithInvalidFile()
    {
        Sound sound = new Sound("nonExistentFile");
        try {
            sound.playSound();
        } catch (Exception e) {
            fail("Expected FileNotFoundException was not thrown" + e.getMessage());
        }
    }
}
