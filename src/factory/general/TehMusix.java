package factory.general;

import javax.sound.sampled.*;
import java.io.*;

   import java.io.File;
import java.io.IOException;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
@brief plays sounds
@author YiWei Roy Zheng & Help from the Internet
 */
public class TehMusix implements Runnable{

    String filename;

    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    AudioInputStream audioStream;
    AudioFormat audioFormat;
    SourceDataLine sourceLine;

    public TehMusix(String fname)
    {
	this.filename = fname;
    }
    public void run()
    {
	this.playSound();
    }
    /**
     * This function was taken from StackOverflow
     * @param filename the name of the file that is going to be played
     *
     */
    public void playSound(){

        String strFilename = this.filename;

        try {
            soundFile = new File(strFilename);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            this.audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
           System.exit(1);
        }

        this.audioFormat = this.audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }
        sourceLine.drain();
        sourceLine.close();
    }
    public static void play(String filename)
    {
    try
	{
	    Clip clip = AudioSystem.getClip();
	    clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	    clip.start();
	}
    catch (Exception ex)
	{
	    ex.printStackTrace();
	}
    }
    public static void main(String[] args)
    {
	TehMusix s = new TehMusix("bg.wav");
	(new Thread(s)).start();
		//MakeSound.play("bg.wav");
    }
}