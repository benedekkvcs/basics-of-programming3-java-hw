package main;

import menu.Sound;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Sound sound = new Sound("icantstop");
        sound.playSound();

        SwingUtilities.invokeLater(() -> {
            Control control = null;
            try {
                control = new Control(sound);
            } catch (IOException | FontFormatException e) {
                throw new RuntimeException(e);
            }
            Thread controlThread = new Thread(control);
            controlThread.start();
        });
    }
}