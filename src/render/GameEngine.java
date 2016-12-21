package render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import cfg.Window;
import panel.GamePanel;
import utils.ImageTool;
public class GameEngine extends Engine{

	
	Thread scene;
	Thread sprite;
	
	SceneEngine sceneEngine;
	SpriteEngine spriteEngine;
	
	BufferedImage[] array;
	
	public GameEngine(){
		
			sceneEngine = new SceneEngine();
			spriteEngine = new SpriteEngine();
			
			scene = new Thread(sceneEngine);
			sprite = new Thread(spriteEngine);
			
			
			scene.start();
			sprite.start();
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
			buffer = new BufferedImage(Window.WIDTH, Window.HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		
			Graphics bufferG = buffer.getGraphics();
		
			bufferG.drawImage(sceneEngine.getBuffer(), 0, 0, null);
			bufferG.drawImage(spriteEngine.getBuffer(),0 ,0, null);
			
			Graphics g = GamePanel.getGame().getGraphics();
			g.drawImage(buffer, 0, 0, null);
			
			bufferG.setColor(Color.black);
			bufferG.fillRect(0, 0, 1100, 700);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
