package render;


import java.awt.Graphics;
import java.awt.image.BufferedImage;


import cfg.Keyboard;
import cfg.Window;
import dom.DOM;
import graphics.BackgroundGraphics;
import graphics.CharacterGraphic;
import graphics.EffectGraphics;
import graphics.InfoGraphics;
import graphics.ItemGraphic;
import panel.GamePanel;
import player.Player;
import tcp.TCPClient;
import utils.MessageBuilder;

public class GameEngine extends Engine{

	private static GameEngine instance;
	
//	Thread scene;
//	Thread sprite;
//	Thread item;
//	Thread info;
//	Thread effect;
//	
//	SceneEngine sceneEngine;
//	SpriteEngine spriteEngine;
//	ItemEngine	itemEngine;
//	InfoEngine infoEngine;
//	EffectEngine effectEngine;
	
	
	long lastTime = 0;
	int walkDelkay = 0;
	Thread thread;
	
	private GameEngine(){
		
//			sceneEngine = new SceneEngine();
//			spriteEngine = new SpriteEngine();
//			itemEngine= new ItemEngine();
//			infoEngine = new InfoEngine();
//			effectEngine = new EffectEngine();
			
//			EngineTable.engineTable.add(sceneEngine);
//			EngineTable.engineTable.add(spriteEngine);
//			EngineTable.engineTable.add(itemEngine);
//			EngineTable.engineTable.add(infoEngine);
//			EngineTable.engineTable.add(effectEngine);
//			EngineTable.engineTable.add(this);
//			
//			scene = new Thread(sceneEngine);
//			sprite = new Thread(spriteEngine);
//			item = new Thread(itemEngine);
//			info = new Thread(infoEngine);
//			effect = new Thread(effectEngine);
			
			//scene.start();
			//sprite.start();
			//item.start();
			//info.start();
			//effect.start();
		
		setFPS(60);
		thread = new Thread(this , "GAME ENGINE");
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
//		if(!scene.isAlive()){
//			scene = new Thread(sceneEngine);
//			scene.start();
//		}
//		else if(!sprite.isAlive()){
//			sprite = new Thread(spriteEngine);
//			sprite.start();
//		}
//		else if(!info.isAlive()){
//			info = new Thread(infoEngine);
//			info.start();
//		}
//		else if(!effect.isAlive()){
//			effect = new Thread(effectEngine);
//			effect.start();
//		}
		
		update();
	}

	@Override
	public void render() {
			buffer = new BufferedImage(Window.WIDTH, Window.HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		
			Graphics bufferG = buffer.getGraphics();
			
			
			BackgroundGraphics.getGraph().paint(bufferG);
			//ItemGraphic.getGraph().paint(bufferG);
			CharacterGraphic.getGraphic().paint(bufferG);
			InfoGraphics.getGraphic().paint(bufferG);
			EffectGraphics.getGraphic().paint(bufferG);
			
			
			Graphics g = GamePanel.getGame().getGraphics();
			g.drawImage(buffer, 0, 0, null);
		

//			bufferG.drawImage(sceneEngine.getBuffer(), 0, 0, null);
//			bufferG.drawImage(spriteEngine.getBuffer(),0 ,0, null);
//			bufferG.drawImage(itemEngine.getBuffer(),0 ,0, null);	
//			bufferG.drawImage(infoEngine.getBuffer(), 0, 0, null);
//			bufferG.drawImage(effectEngine.getBuffer() , 0 , 0 , null);
			
//			Graphics g = GamePanel.getGame().getGraphics();
//			g.drawImage(buffer, 0, 0, null);
//			
//			bufferG.setColor(Color.black);
//			bufferG.fillRect(0, 0, 1100, 700);
	}

	@Override
	public void update() {
		sceneUpdate((int)(DOM.getSelf().getCharacter().getSpeed() * 1000));
		spriteUpdate();
		effectUpdate();
	}
	
	public void startEngine(){
//		if(instance == null) instance = getEngine();
//		EngineTable.startAllEngine();
		
		if(this.getThreadStatus() || getRunningState()) return;

		this.start();
		thread.start();
		
	}
	public void stopEngine(){
		this.stop();
		instance = null;
	}
	
	public void restart(){
		thread = new Thread(this , "restart GAME ENGINE");
		this.stop();
		this.start();
		thread.start();
	}
	
	public boolean getThreadStatus(){
		return thread.isAlive();
	}
	
	private void sceneUpdate(int delay)
	{
		if(walkDelkay < 1)
		{
			if (Keyboard.isKeyBoardDown())
			{
				int xOffset = (Keyboard.getLeftInt() * -1) + Keyboard.getRightInt();
				int yOffset = (Keyboard.getUpInt() * -1) + Keyboard.getDownInt();
				
				if (xOffset != 0 || yOffset != 0)
				{
					BackgroundGraphics.getGraph().SetBlockValueWithMoveFlow(xOffset,yOffset);
					if(BackgroundGraphics.getGraph().canPass(DOM.getSelf().getSprite().getX() + xOffset,DOM.getSelf().getSprite().getY() + yOffset))
					{
						DOM.getSelf().getSprite().setXY(DOM.getSelf().getSprite().getX() + xOffset , 
														DOM.getSelf().getSprite().getY() + yOffset);
					}
					walkDelkay += delay;
					TCPClient.getInstance().send(MessageBuilder.location());
				}
			}
		}else{
			walkDelkay -= 1000/GameEngine.getEngine().getFps();
		}
	}
	
	private void spriteUpdate(){
		for(Player player : DOM.getPlayerTable()){
			if(player.getSprite().getIsAnimating()){
				player.getSprite().getAnimation().start();
				player.getSprite().getAnimation().update();
			}
			else
				player.getSprite().getAnimation().stop();
		}
		checkSelfIsKeyboardDown();
	}
	private void effectUpdate(){
		for(Player player : DOM.getPlayerTable())
		{
			if(player.getSprite().getIsDamage()){
				player.getSprite().getExplosionAnimation().start();
				player.getSprite().getExplosionAnimation().update();
			}
			else{
				player.getSprite().getExplosionAnimation().stop();
			}
		}
		
		
	}
	private void checkSelfIsKeyboardDown(){
		if(!Keyboard.isKeyBoardDown())
		{
			DOM.getSelf().getSprite().setAnimating(false);
			DOM.getSelf().getSprite().getAnimation().stop();
		}
	}
	
}
