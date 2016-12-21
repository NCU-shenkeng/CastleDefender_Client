package render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import cfg.Window;
import graphics.BackgroundGraphics;

public class SceneEngine extends Engine{

	
	private BackgroundGraphics graph;
	private BufferedImage doneImage;
	
	public SceneEngine() {
		this.graph = BackgroundGraphics.getGraph();
		setFPS(3);
	}
	
	@Override
	public void tick() {
		if(graph.Key[1] + graph.Key[3] + graph.Key[0] + graph.Key[2] != 0)
		{
			graph.SetBlockValue((graph.Key[1] * -1)+(graph.Key[3]), (graph.Key[0] * -1)+(graph.Key[2]));
		}
		
	}

	@Override
	public void render() {
		BufferedImage buffer = new BufferedImage(Window.WIDTH, Window.HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = buffer.getGraphics();
		graph.paint(g);
		doneImage = buffer;
		buffer = null;
	}

	@Override
	public void update() {
		
	}
	
	public BufferedImage getBuffer(){
		return this.doneImage;
	}
	
}