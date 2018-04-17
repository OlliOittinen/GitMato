/*
import Sound.Music;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MusicTest {

    @Test
    public void playAll() {
        Music.backgroundMusic.loop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(Music.backgroundMusic.isActive());
        Music.backgroundMusic.stop();
        assertTrue(!Music.backgroundMusic.isActive());

        Music.fasterPowerup.loop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(Music.fasterPowerup.isActive());
        Music.fasterPowerup.stop();

        Music.slowerPowerup.loop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(Music.slowerPowerup.isActive());
        Music.slowerPowerup.stop();

        Music.loseLife.loop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(Music.loseLife.isActive());
        Music.loseLife.stop();

        Music.reverse.loop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(Music.reverse.isActive());
        Music.reverse.stop();

        Music.shield.play();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(Music.shield.isActive());
        Music.shield.stop();

    }
}
*/
