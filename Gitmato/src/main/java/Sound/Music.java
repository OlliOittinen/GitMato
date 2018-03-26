package Sound;

import javax.sound.sampled.*;
import java.io.File;

public class Music {

    private Clip clip;

    public static Music backgroundMusic = new Music("Off Limits.wav");
    public static Music fasterPowerup = new Music("faster.wav");
    public static Music laserShot = new Music("lasershot.wav");
    public static Music death = new Music("Death.wav");
    public static Music addLife = new Music("getlife.wav");
    public static Music shield = new Music("shields.wav");
    public static Music bombs = new Music("bombs.wav");
    public static Music slowerPowerup = new Music("slower.wav");
    public static Music reverse = new Music("confusion.wav");
    public static Music snack = new Music("snack.wav");
    public static Music loseLife = new Music("lostlife.wav");


    public Music(String fileName) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/"+(fileName)).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }

                }.start();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void stop() {
        if (clip == null) {
            return;
        }
        clip.stop();
    }

    public void loop() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            int frame = clip.getFramePosition();
                            clip.setFramePosition(frame);
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isActive() {

        return clip.isActive();
    }
}
