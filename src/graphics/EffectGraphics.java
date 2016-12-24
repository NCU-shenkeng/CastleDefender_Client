package graphics;

import java.awt.Graphics;

import player.Player;

public class EffectGraphics {
	
private static EffectGraphics effectGraphics = null;
	
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
		int x = player.getCordinateXInPanel()+30;
		int y = player.getCordinateYInPanel()-20;
		
		fullTime /= 1000;
		leftTime /= 1000;
		
		int startAngle = ((fullTime - leftTime) * 360) / fullTime;
		int endAngle = 360 - startAngle;
		
		g.fillArc(x,y, 20, 20, (int) startAngle,(int) endAngle);
	}
	
	
}
