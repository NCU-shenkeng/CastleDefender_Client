package render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import cfg.Window;
import graphics.InfoGraphics;

public class InfoEngine extends Engine{
	@Override
	public void tick() {
	
	}

	@Override
	public void render() {
		buffer = new BufferedImage(Window.WIDTH, Window.HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();
		
		
		InfoGraphics.getGraphic().drawPlayerInformation(g);
		InfoGraphics.getGraphic().drawSelfCastleInformation(g);
		InfoGraphics.getGraphic().drawEnemyCastleInformation(g);
		
		doneImage = buffer;

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
