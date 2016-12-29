package sound;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class BGM
{
	private static BGM bgm;
	
	private HashMap<String, Clip> musics;
	private HashMap<String, String> fileList;
	private String nowPlaying = "";
	
	
	private BGM()
	{
		fileList = new HashMap<>();
		musics = new HashMap<>();
		
		fileList.put("Menu", "login.wav");
		fileList.put("Select", "character_selection.wav");
		fileList.put("Gaming", "in_game.wav");
		fileList.put("Win", "win.wav");
		fileList.put("Lose", "lose.wav");
		
		for (Map.Entry<String, String> item : fileList.entrySet())
		{
			try{
				File mp3 = new File("BGM/" + item.getValue());
			    AudioInputStream audioInputStream =
			        AudioSystem.getAudioInputStream(mp3);
			    Clip clip = AudioSystem.getClip();
			    clip.open(audioInputStream);
			    musics.put(item.getKey(), clip);
			}
			catch(Exception ex)
			{
				System.out.println(String.format("Load BGM Fail: %s %s", item.getKey(),item.getValue()));
				ex.printStackTrace();
			}
		}
		
	}
	
	public static BGM getBGM()
	{
		if (bgm == null)
		{
			bgm = new BGM();
		}
		return bgm;
	}
	
	public void playBGM(String name)
	{
		for (Map.Entry<String, Clip> music : musics.entrySet())
		{
			music.getValue().stop();
			music.getValue().setFramePosition(0);
		}
		if (musics.containsKey(name))
		{
			musics.get(name).loop(999);
			nowPlaying = name;
		}
	}
	
	public void stopBGM()
	{
		for (Map.Entry<String, Clip> music : musics.entrySet())
		{
			music.getValue().stop();
		}
	}
	
	public String getNowPlaying()
	{
		return nowPlaying;
	}
}
