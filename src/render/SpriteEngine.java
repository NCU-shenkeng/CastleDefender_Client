package render;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import cfg.Keyboard;
import cfg.Window;
import dom.PlayerTable;
import graphics.CharacterGraphic;
import player.Player;

public class SpriteEngine extends Engine{	
	
	
	private BufferedImage doneImage;
	
	public SpriteEngine(){
		setFPS(100);
	}

	@Override
	public void tick() {
		update();
		
	}

	@Override
	public void render() {
		buffer = new BufferedImage(Window.WIDTH, Window.HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = buffer.getGraphics();
		
		Player self = PlayerTable.getPlayerTable().getTable().get(0);
		if(Keyboard.getDownLeft())
			self.getSprite().moveSouthWest();
		else if(Keyboard.getDownRight())
			self.getSprite().moveSouthEast();
		else if(Keyboard.getUpLeft())
			self.getSprite().moveNorthWest();
		else if(Keyboard.getUpRightt())
			self.getSprite().moveNorthEast();
		else if(Keyboard.getUp())
			self.getSprite().moveNorth();
		else if(Keyboard.getDown())
			self.getSprite().moveSouth();
		else if(Keyboard.getLeft())
			self.getSprite().moveWest();
		else if(Keyboard.getRight())
			self.getSprite().moveEast();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Player player : PlayerTable.getPlayerTable().getTable())
		{
			if(player.getSprite().getIsAnimating())
				CharacterGraphic.getGraphic().drawAnimatingPlayer(graphics, player);			
			else 
				CharacterGraphic.getGraphic().drawStaticPlayer(graphics, player);
				
		}
		doneImage = buffer;
		
	}

	@Override
	public void update() {
		
		for(Player player : PlayerTable.getPlayerTable().getTable()){
			if(player.getSprite().getIsAnimating()){
				player.getSprite().getAnimation().start();
				player.getSprite().getAnimation().update();
			}
			else
				player.getSprite().getAnimation().stop();
		}
	
	}
	
	public BufferedImage getBuffer(){
		return doneImage;
	}

}
