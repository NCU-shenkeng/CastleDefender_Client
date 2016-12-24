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
	Thread item;
	Thread info;
	
	SceneEngine sceneEngine;
	SpriteEngine spriteEngine;
	ItemEngine	itemEngine;
	InfoEngine infoEngine;

	
	BufferedImage[] array;
	
	public GameEngine(){
		
			sceneEngine = new SceneEngine();
			spriteEngine = new SpriteEngine();
			itemEngine= new ItemEngine();
			infoEngine = new InfoEngine();
			
			scene = new Thread(sceneEngine);
			sprite = new Thread(spriteEngine);
			item = new Thread(itemEngine);
			info = new Thread(infoEngine);
			
			scene.start();
			sprite.start();
			item.start();
			info.start();
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
			bufferG.drawImage(itemEngine.getBuffer(),0 ,0, null);	
			bufferG.drawImage(infoEngine.getBuffer(), 0, 0, null);
			
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
