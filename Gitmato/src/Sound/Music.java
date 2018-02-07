package Sound;

import javax.sound.sampled.*;

public class Music {

    private Clip clip;

    public static Music sound1 = new Music("Arcade_Funk.wav");
    public static Music sound2 = new Music("Running In The 90s (Hardcore Rave Edit).wav");

    public Music(String fileName) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Music.class.getResource(fileName));
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
