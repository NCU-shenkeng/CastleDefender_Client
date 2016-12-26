package render;

import java.awt.image.BufferedImage;

import graphics.ItemGraphic;

public class ItemEngine extends Engine
{
	private ItemGraphic graph;
	private BufferedImage doneImage;
	
	public ItemEngine()
	{
		this.graph = ItemGraphic.getGraph();
		//setFPS(30);
	}
	
	@Override
	public void tick()
	{
//		BufferedImage buffer = new BufferedImage(Window.WIDTH, Window.HEIGHT,BufferedImage.TYPE_INT_ARGB);
//		Graphics g = buffer.getGraphics();
//		graph.paint(g);
//		doneImage = buffer;
//		buffer = null;
	}
	
	@Override
	public void render()
	{
		
		
	}
	@Override
	public void update() {
	}
	
	public BufferedImage getBuffer()
	{
		return this.doneImage;
	}	
	
}
