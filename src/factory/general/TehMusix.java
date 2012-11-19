/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.general;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 *@brief plays teh musix for da lolz
 * @author Roy YiWei Zheng
 */
public class TehMusix {

    private AudioClip song; // Sound player
    private URL songPath; // Sound path

    TehMusix(String filename) {
        try {
            
          //  songPath = new URL(getCodeBase(), filename); // Get the Sound URL
            song = Applet.newAudioClip(songPath); // Load the Sound
        } catch (Exception e) {
        } // Satisfy the catch
    }

    public void playSound() {
        song.loop(); // Play 
    }

    public void stopSound() {
        song.stop(); // Stop
    }

    public void playSoundOnce() {
        song.play(); // Play only once
    }
}
