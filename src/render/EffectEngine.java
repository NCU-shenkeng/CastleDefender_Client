package render;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import cfg.Self;
import cfg.Window;
import dom.DOM;
import graphics.EffectGraphics;
import player.Player;

public class EffectEngine extends Engine{
	
	
	int explosionTime = 200;

	public EffectEngine() {
		setFPS(2);
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
			if(player.getSprite().getIsDamage())
			{
				Self.nowTime = System.currentTimeMillis();
				if(Self.nowTime - Self.lastTime < explosionTime)
					EffectGraphics.getGraphic().drawExplosion(graphics, player);
				else{
					player.getSprite().setIsDamage(false);
				}
			}
			if(player.getSprite().getIsPicking()){
				EffectGraphics.getGraphic().drawPick(graphics, 
													 player, 
													 player.getSprite().getPickFullTime(), 
													 player.getSprite().getPickLeftTime());
			}
		}
		doneImage = buffer;
	}

	@Override
	public void update() 
	{
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

}
