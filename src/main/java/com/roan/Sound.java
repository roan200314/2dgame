package com.roan;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundUrl = new URL[30];

    public Sound() {
        soundUrl[0] = getClass().getResource("/sound/themeSong.wav");
        soundUrl[1] = getClass().getResource("/sound/doorSound.wav");
        soundUrl[2] = getClass().getResource("/sound/chestUnlocked.wav");
        soundUrl[3] = getClass().getResource("/sound/swordSlash.wav");
        soundUrl[4] = getClass().getResource("/sound/pickedUpKey.wav");
        soundUrl[5] = getClass().getResource("/sound/endingSound.wav");

    }
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
        }
    }

    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }

}
