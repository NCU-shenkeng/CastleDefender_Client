
package render;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import cfg.Keyboard;
import cfg.Window;
import dom.DOM;
import dom.PlayerTable;
import graphics.CharacterGraphic;
import player.Player;

public class SpriteEngine extends Engine{	
	
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
	
		for(Player player : DOM.getPlayerTable())
		{		
			if(player.getIsDead()){
				CharacterGraphic.getGraphic().drawDeadPlayer(graphics, player);
				continue;
			}
			if(player.getSprite().getIsAnimating())
				CharacterGraphic.getGraphic().drawAnimatingPlayer(graphics, player);
			else 
				CharacterGraphic.getGraphic().drawStaticPlayer(graphics, player);
		}
		doneImage = buffer;
	}

	@Override
	public void update() {}
	
}