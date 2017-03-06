package main.engine;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/* hier ist meine soundklasse, sie ist mit sicherheit nicht perfekt und
 * kann gerne verbessert werden aber fürs erste kann man sie benutzen um 
 * wav oder au files zu spielen 
 */

public class Sound
{

	private AudioInputStream audio;
	private Clip clip;
	private File data;
	
	public Sound(String path) 
	{
		try
		{
			data = new File(path);
			audio = AudioSystem.getAudioInputStream(data);
			DataLine.Info info = new DataLine.Info(Clip.class, audio.getFormat());
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(audio);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void playClip()
	{
		//clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void loopClip() 
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void loopClip(int n)
	{
		clip.loop(n);
	}
	
	public void stopClip()
	{
		clip.stop();	
	}
	
	public void closeClip()
	{
		clip.close();
	}
}
