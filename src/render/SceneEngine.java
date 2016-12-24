package render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.text.html.parser.Parser;

import cfg.Keyboard;
import cfg.Self;
import cfg.Window;
import dom.DOM;
import graphics.BackgroundGraphics;
import tcp.TCPClient;

public class SceneEngine extends Engine{

	
	private BackgroundGraphics graph;

	
	public SceneEngine() {
		this.graph = BackgroundGraphics.getGraph();
		setFPS(3);
	}
	
	@Override
	public void tick() {
		update();
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
			int xOffset = (Keyboard.getLeftInt() * -1) + Keyboard.getRightInt();
			int yOffset = (Keyboard.getUpInt() * -1) + Keyboard.getDownInt();
			
			graph.SetBlockValue(xOffset , yOffset);
			
			if(BackgroundGraphics.getGraph().canPass(DOM.getSelf().getSprite().getX() + xOffset,DOM.getSelf().getSprite().getY() + yOffset))
			{
				DOM.getSelf().getSprite().setXY(DOM.getSelf().getSprite().getX() + xOffset , 
												DOM.getSelf().getSprite().getY() + yOffset);
			}
			String sendMsg = String.format("3,%d,%d,%d,%d,%d", 
										   Self.number , 
										   DOM.getSelf().getSprite().getX() ,
										   DOM.getSelf().getSprite().getY() ,
										   utils.Parser.directionToInt(DOM.getSelf().getSprite().getFacing()),
										   utils.Parser.parseBoolean(DOM.getSelf().getSprite().getIsAnimating()));
			System.out.println(sendMsg);
			
			TCPClient.getInstance().send(sendMsg);

	}
}