package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import dom.DOM;
import player.Player;
import utils.ImageTool;
import utils.Parser;

public class InfoGraphics extends Graph{
	
	private BufferedImage info;
	private static InfoGraphics infoGraphic = null;
	
	public static InfoGraphics getGraphic(){
		if(infoGraphic == null){
			synchronized (InfoGraphics.class) {
				infoGraphic = new InfoGraphics();
			}
		}
		return infoGraphic;
	}
	
	private InfoGraphics() {
		try {
			info = ImageTool.getImage("images/info_bar.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g) {}
	
	
	public void drawPlayerInformation(Graphics g)
	{
		int initX = 0;
		int initY = 611;
		
		g.drawImage(info, initX,initY,250,50,null);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.BOLD,15));
		
		
		Player self = DOM.getSelf();
		String health = Parser.toString(self.getCharacter().getCurrentHP());
		String attackpower = Parser.toString(self.getCharacter().getAttackPower());
		String type = Parser.parseCharacterToChinese(self.getCharacter().getType());
		String reviveTime = Parser.toString(self.getReviveTime());
		
		g.drawString(health,initX + 40, initY + 30); // health
		g.drawString(attackpower,initX + 95, initY +30); // attack power
		if(self.getIsDead())
			g.drawString(reviveTime, initX +145 , initY +30);
		g.drawString(type, initX + 180, initY +30); // character type
		
	}
	
	public void drawSelfCastleInformation(Graphics g){
	}
	public void drawEnemyCastleInformation(Graphics g){}
}
