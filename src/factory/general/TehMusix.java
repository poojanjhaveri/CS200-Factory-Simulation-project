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
     * 
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
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
           System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
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
                @SuppressWarnings("unused")
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
    catch (Exception exc)
	{
	    exc.printStackTrace(System.out);
	}
    }
    public static void main(String[] args)
    {
	TehMusix s = new TehMusix("bg.wav");
	(new Thread(s)).start();
		//MakeSound.play("bg.wav");
    }
}