package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	private BufferedImage info,selfinfo,enemyinfo;
	private static InfoGraphics infoGraphic = null;
	
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
	public void paint(Graphics g) {}
	
	
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
		String reviveTime = Parser.toString(self.getReviveTime());
		
		g.drawString(health,initX + 40, initY + 30); // health
		g.drawString(attackpower,initX + 95, initY +30); // attack power
		if(self.getIsDead())
			g.drawString(reviveTime, initX +145 , initY +30);
		g.drawString(type, initX + 180, initY +30); // character type
		
	}
	
	public void drawSelfCastleInformation(Graphics g)
	{
		int bloodx=(Integer.valueOf(CastleTable.getCastleTable().getCastle().getSelfCastleBlood()) > 99)?528:533;
		int bloody=605;
		int offset=45;
		int itemx=305;
		int itemy=647;
		int cdx=312;
		int cdy=655;
		int fullcircle = 360;
		int itemsize=30;
		int cdsize=20;
		g.drawImage(selfinfo, 180, 565, 730,150, null);
		g.setFont(new Font("monospaced", Font.BOLD|Font.ITALIC , 20));
		g.setColor(new Color(220, 255, 200, 200));
		g.drawString(CastleTable.getCastleTable().getCastle().getSelfCastleBlood(), bloodx, bloody);  //自己Blood要dom 來提供這邊只是假的
		g.setColor(new Color(255, 255, 255, 200));
		for(int i=0;i<11;i++)
		{
			int j = CastleTable.getCastleTable().getCastle().getCastleBuff()[i]; //list[] 是dom提供的 改好把list刪掉拿dom的就好
			int lefttime = CastleTable.getCastleTable().getCastle().getCastleBuffCDR()[i]; //time[] 同上 下面缺角跟圓算數學 time[]存要缺的角度 
			try
			{
				

				if(lefttime  > 0)
				{
					g.drawImage(img[j],itemx,itemy,itemsize,itemsize,null);
				
					//System.out.println(String.format("cd %s %s",lefttime,fullcircle-lefttime));
					g.fillArc(cdx,cdy, cdsize, cdsize, lefttime, fullcircle-lefttime);
				}
					
				
			}
			catch(Exception e)
			{
				System.out.println("Not such as item");
			}
			itemx+=offset;
			cdx+=offset;
		}
	}
	public void drawEnemyCastleInformation(Graphics g)
	{
		int ebloodx=(Integer.valueOf(CastleTable.getCastleTable().getCastle().getEnemyCastleBlood()) > 99)?531:536;
		int ebloody=50;
		int offset=45;
		int eitemx=610;
		int eitemy=15;
		int itemsize=30;
		g.drawImage(enemyinfo, 370, 0, 730,150, null);
		
		g.setFont(new Font("monospaced", Font.BOLD|Font.ITALIC , 20));
		g.setColor(new Color(220, 255, 200, 200));
		g.drawString(CastleTable.getCastleTable().getCastle().getEnemyCastleBlood(), ebloodx, ebloody); //eblood敵人城堡血量 同上
		g.setColor(new Color(255, 255, 255, 200));
		for(int i=0;i<11;i++)
		{
			int eitem = CastleTable.getCastleTable().getCastle().getEnemyCastleBuff()[i]; //elist[]敵人buff  DOM提供 待修改
			int lefttime = CastleTable.getCastleTable().getCastle().getEnemyBuffCDR()[i];
			try
			{
				if(lefttime > 0)
				g.drawImage(img[eitem],eitemx,eitemy,itemsize,itemsize,null);
			}
			catch(Exception e)
			{
				System.out.println("Not such as item");
			}
			
			eitemx+=offset;
		}
	}
}