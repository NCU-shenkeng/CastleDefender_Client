package render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import cfg.Window;
import dom.EngineTable;
import panel.GamePanel;
import receiver.UDPMessageReceiver;
import udp.Server;
import utils.ImageTool;
public class GameEngine extends Engine{

	private static GameEngine instance;
	
	Thread scene;
	Thread sprite;
	Thread item;
	Thread info;
	Thread effect;
	
	SceneEngine sceneEngine;
	SpriteEngine spriteEngine;
	ItemEngine	itemEngine;
	InfoEngine infoEngine;
	EffectEngine effectEngine;

	
	private GameEngine(){
		
			sceneEngine = new SceneEngine();
			spriteEngine = new SpriteEngine();
			//itemEngine= new ItemEngine();
			infoEngine = new InfoEngine();
			effectEngine = new EffectEngine();
			
			EngineTable.engineTable.add(sceneEngine);
			EngineTable.engineTable.add(spriteEngine);
			//EngineTable.engineTable.add(itemEngine);
			EngineTable.engineTable.add(infoEngine);
			EngineTable.engineTable.add(effectEngine);
			EngineTable.engineTable.add(this);
			
			scene = new Thread(sceneEngine);
			sprite = new Thread(spriteEngine);
			//item = new Thread(itemEngine);
			info = new Thread(infoEngine);
			effect = new Thread(effectEngine);
			
			scene.start();
			sprite.start();
			//item.start();
			info.start();
			effect.start();

	}
	
	
	public static GameEngine getEngine(){
		if(instance == null){
			synchronized (GameEngine.class) {
				instance = new GameEngine();
			}
		}
		return instance;
	}
	@Override
	public void tick() {
	}

	@Override
	public void render() {
			buffer = new BufferedImage(Window.WIDTH, Window.HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		
			Graphics bufferG = buffer.getGraphics();
		

			bufferG.drawImage(sceneEngine.getBuffer(), 0, 0, null);
			bufferG.drawImage(spriteEngine.getBuffer(),0 ,0, null);
			//bufferG.drawImage(itemEngine.getBuffer(),0 ,0, null);	
			bufferG.drawImage(infoEngine.getBuffer(), 0, 0, null);
			bufferG.drawImage(effectEngine.getBuffer() , 0 , 0 , null);
			
			Graphics g = GamePanel.getGame().getGraphics();
			g.drawImage(buffer, 0, 0, null);
			
			bufferG.setColor(Color.black);
			bufferG.fillRect(0, 0, 1100, 700);
	}

	@Override
	public void update() {
	}
	
	public void startEngine(){
		if(instance == null) instance = getEngine();
		EngineTable.startAllEngine();
		
	}
	public void stopEngine(){
		EngineTable.stopAllEngine();
		instance = null;
	}
}
