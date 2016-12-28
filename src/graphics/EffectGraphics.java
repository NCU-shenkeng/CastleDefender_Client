package graphics;

import java.awt.Graphics;

import cfg.Self;
import dom.DOM;
import player.Player;

public class EffectGraphics extends Graph{
	
private static EffectGraphics effectGraphics = null;
	
	int explosionTime = 200;

	public static EffectGraphics getGraphic(){
		if(effectGraphics == null){
			synchronized (EffectGraphics.class) {
				effectGraphics = new EffectGraphics();
			}
		}
		return effectGraphics;
	}
	
	public void drawExplosion(Graphics g,Player player){
		int x = player.getCordinateXInPanel();
		int y = player.getCordinateYInPanel();
		g.drawImage(player.getSprite().getExplosionAnimation().getSprite(),
				x-10,
				y,
				100,
				100,
				null);
	}
	
	
	public void drawPick(Graphics g , Player player , int fullTime , int leftTime)
	{
		int x = player.getCordinateXInPanel()+33;
		int y = player.getCordinateYInPanel()-45;
		
		int startAngle = ((fullTime - leftTime) * 360) / fullTime;
		int endAngle = 360 - startAngle;
		
		g.fillArc(x,y, 20, 20, (int) startAngle,(int) endAngle);
	}

	@Override
	public void paint(Graphics g) {
		for(Player player : DOM.getPlayerTable())
		{
			if(player.getSprite().getIsDamage())
			{
				Self.nowTime = System.currentTimeMillis();
				if(Self.nowTime - Self.lastTime < explosionTime)
					EffectGraphics.getGraphic().drawExplosion(g, player);
				else{
					player.getSprite().setIsDamage(false);
				}
			}
			if(player.getSprite().getIsPicking()){
				EffectGraphics.getGraphic().drawPick(g, 
													 player, 
													 player.getSprite().getPickFullTime(), 
													 player.getSprite().getPickLeftTime());
			}
		}
	}
	
	
}
