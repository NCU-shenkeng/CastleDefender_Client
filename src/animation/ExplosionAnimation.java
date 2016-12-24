package animation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import utils.ImageTool;

public class ExplosionAnimation extends Animation{
	
	private List<Frame> explosionFrame;
	
	public ExplosionAnimation(){
		explosionFrame = new ArrayList<Frame>();
		loadExplosionFrames();
		setFrames(explosionFrame);
	}
	private void loadExplosionFrames(){
		System.out.println(123);
		File f = new File("images/explosion/");
		for(File file : f.listFiles()){
			explosionFrame.add(new Frame(ImageTool.toBufferedImage(file) , 7));
		}
	}
	
}
