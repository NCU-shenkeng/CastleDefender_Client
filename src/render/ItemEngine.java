package render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import cfg.Window;
import graphics.ItemGraphic;

public class ItemEngine extends Engine
{
	private ItemGraphic graph;
	private BufferedImage doneImage;
	
	public ItemEngine()
	{
		this.graph = ItemGraphic.getGraph();
		setFPS(3);
	}
	
	@Override
	public void tick()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render()
	{
		BufferedImage buffer = new BufferedImage(Window.WIDTH, Window.HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();
		graph.paint(g);
		doneImage = buffer;
		buffer = null;
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public BufferedImage getBuffer()
	{
		return this.doneImage;
	}	
	
}
