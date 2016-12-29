package graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cfg.Self;
import dom.CastleTable;
import dom.DOM;
import player.Player;
import utils.ImageTool;
import utils.Parser;

public class InfoGraphics extends Graph{
	BufferedImage img[] = new BufferedImage[15];
	private int list[] = new int[11];
	private int time[] = new int[11];
	private int elist[] = new int[11];
	private String Blood="";
	private String eBlood="";
	private BufferedImage info,selfinfo,enemyinfo,hpchange;
	private static InfoGraphics infoGraphic = null;
	
	private int hpChangeDelay = 3000;
	private boolean shoeEnemyBuffCD = false;
	
	public static InfoGraphics getGraphic(){
		if(infoGraphic == null){
			synchronized (InfoGraphics.class) {
				infoGraphic = new InfoGraphics();
			}
		}
		return infoGraphic;
	}
	
	private void OpenImge (BufferedImage img[])
	{
		try {
			hpchange = ImageTool.getImage("images/HP_change.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String path[] = {
						 "images/item/ATTACKSPEED1.bmp",
				  		 "images/item/ATTACKSPEED2.bmp",
				  		 "images/item/ATTACKSPEED3.bmp",
				  		 "images/item/ATTACKUP1.bmp",
				  		 "images/item/ATTACKUP2.bmp",
				  		 "images/item/ATTACKUP3.bmp",
				  		 "images/item/HEAL.bmp",
				  		 "images/item/LIFECHANGE.bmp",
				  		 "images/item/OPPORTUNITY.bmp",
				  		 "images/item/SHIELD1.bmp",
				  		 "images/item/SHIELD2.bmp",
				  		 "images/item/SHIELD3.bmp",
				  		 "images/item/STEALING.bmp",
						 "images/item/TRANSPORT.bmp",
				  		 "images/item/null.png"};
		File origFile;
		for (int i = 0; i < path.length; i++)
		{
			origFile = new File(path[i]);
			try 
			{
				img[i] = ImageIO.read(origFile);
			} 
			catch (IOException e) 
			{
				  System.out.println("OpenImge error");
				  e.printStackTrace();
			}
			catch(Exception e)
			{
				System.out.println("BufferdImage Array size error");
			}
		}
	}
	private InfoGraphics() {
		try {
			OpenImge(img);
			info = ImageTool.getImage("images/info_bar.png");
			selfinfo = ImageTool.getImage("images/info_selfCastle_bar.png");
			enemyinfo = ImageTool.getImage("images/info_enemyCastle_bar.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g) {
		drawEnemyCastleInformation(g);
		drawSelfCastleInformation(g);
		drawPlayerInformation(g);
		drawHPChagne(g);
	}
	
	
	public void drawPlayerInformation(Graphics g)
	{
		int initX = 0;
		int initY = 0;
		
		g.drawImage(info, initX,initY,250,50,null);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.BOLD,15));
		
		
		Player self = DOM.getSelf();
		String health = Parser.toString(self.getCharacter().getCurrentHP());
		String attackpower = Parser.toString(self.getCharacter().getAttackPower());
		String type = Parser.parseCharacterToChinese(self.getCharacter().getType());
		String reviveTime = Parser.toString((float)self.getReviveTime()/1000);
	
		g.drawString(health,initX + 40, initY + 30); // health
		g.drawString(attackpower,initX + 95, initY +30); // attack power
		if(self.getIsDead())
			g.drawString(reviveTime, initX +145 , initY +30);
		g.drawString(type, initX + 180, initY +30); // character type
		
	}
	
	public void drawSelfCastleInformation(Graphics g)
	{
		int panelDrawX = (cfg.Window.WIDTH / 2) - (730 / 2);
		int panelDrawY = (cfg.Window.HEIGHT - 180) + 5;
		int bloodx = (Integer.valueOf(CastleTable.getCastleTable().getCastle().getSelfCastleBlood()) > 99) ? panelDrawX + 350 : panelDrawX + 355;
		int bloody = panelDrawY + 38;
		int offset = 45;
		int itemx = panelDrawX + 123;
		int itemy = panelDrawY + 77;
		int fullcircle = 360;
		int itemsize = 38;
		int[] itemDrwaPointX =
		{ itemx, itemx + offset, itemx + (offset * 2) - 1, itemx + (offset * 3) - 1, itemx + (offset * 4) - 1,
				itemx + (offset * 5) - 2, itemx + (offset * 6) - 2, itemx + (offset * 7) - 2, itemx + (offset * 8) - 2,
				itemx + (offset * 9) - 3, itemx + (offset * 10) - 3, };
		g.drawImage(selfinfo, panelDrawX, panelDrawY, 730, 150, null);
		g.setFont(new Font("monospaced", Font.BOLD | Font.ITALIC, 20));
		g.setColor(new Color(0, 0, 0, 255));
		g.drawString(CastleTable.getCastleTable().getCastle().getSelfCastleBlood(), bloodx - 2, bloody - 2);
		g.setColor(new Color(255, 255, 255, 255));
		g.drawString(CastleTable.getCastleTable().getCastle().getSelfCastleBlood(), bloodx, bloody);
		g.setColor(new Color(255, 255, 255, 200));
		
		for(int i=0;i<11;i++)
		{
			int j = CastleTable.getCastleTable().getCastle().getCastleBuff()[i];
			int lefttime = CastleTable.getCastleTable().getCastle().getCastleBuffCDR()[i];
			try
			{
				if (lefttime > 0)
				{
					int cdrOffset = (int) (((float) (lefttime) / fullcircle) * (itemsize));
					g.fillRect(itemDrwaPointX[i], itemy + cdrOffset, itemsize, itemsize - cdrOffset);
					g.drawImage(img[j],itemDrwaPointX[i],itemy,itemsize,itemsize,null);
					
				}
			}
			catch(Exception e)
			{
				System.out.println("Not such as item");
			}
		}
	}
	
	public void drawEnemyCastleInformation(Graphics g)
	{
		int panelDrawX = (cfg.Window.WIDTH - 730) - 5;
		int panelDrawY = 0;
		int ebloodx = (Integer.valueOf(CastleTable.getCastleTable().getCastle().getEnemyCastleBlood()) > 99) ? 531 : 536;
		int ebloody = 50;
		int offset = 45;
		int eitemx = panelDrawX + 235;
		int eitemy = 12;
		int itemsize = 38;
		int[] itemDrwaPointX =
			{ eitemx, eitemx + offset, eitemx + (offset * 2), eitemx + (offset * 3), eitemx + (offset * 4),
				eitemx + (offset * 5), eitemx + (offset * 6), eitemx + (offset * 7), eitemx + (offset * 8),
				eitemx + (offset * 9), eitemx + (offset * 10)};
		g.drawImage(enemyinfo, panelDrawX, panelDrawY, 730, 150, null);

		g.setFont(new Font("monospaced", Font.BOLD | Font.ITALIC, 20));
		g.setColor(new Color(0, 0, 0, 255));
		g.drawString(CastleTable.getCastleTable().getCastle().getEnemyCastleBlood(), ebloodx - 3, ebloody - 1);
		g.setColor(new Color(255, 255, 255, 255));
		g.drawString(CastleTable.getCastleTable().getCastle().getEnemyCastleBlood(), ebloodx, ebloody);
		g.setColor(new Color(255, 255, 255, 200));
		
		for(int i=0;i<11;i++)
		{
			int eitem = CastleTable.getCastleTable().getCastle().getEnemyCastleBuff()[i];
			int lefttime = CastleTable.getCastleTable().getCastle().getEnemyBuffCDR()[i];
			try
			{
				if(lefttime > 0){
					if (shoeEnemyBuffCD )
					{
						int cdrOffset = (int) (((float) (lefttime) / 360) * (itemsize));
						g.fillRect(itemDrwaPointX[i], eitemy + cdrOffset, itemsize, itemsize - cdrOffset);
					}
					g.drawImage(img[eitem],itemDrwaPointX[i],eitemy,itemsize,itemsize,null);
				}
			}
			catch(Exception e)
			{
				System.out.println("Not such as item");
			}
			
			eitemx+=offset;
		}
	}
	public void drawHPChagne(Graphics g){
		long nowTime = System.currentTimeMillis();
		if(Self.hpchange && ((nowTime - Self.hpChangeLastTime) < hpChangeDelay)){
			g.drawImage(hpchange, 0, 300, null);
		}
		else{
			Self.hpchange = false;
		}
	}


}