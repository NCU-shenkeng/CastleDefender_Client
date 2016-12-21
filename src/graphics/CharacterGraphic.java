package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import cfg.Self;
import dom.DOM;
import dom.PlayerTable;
import player.Player;
import utils.ImageTool;
import utils.Parser;

public class CharacterGraphic extends Graph
{
	
	private static CharacterGraphic characterGraphic = null;
	private BufferedImage info;
	public static CharacterGraphic getGraphic(){
		if(characterGraphic == null){
			synchronized (CharacterGraphic.class) {
				characterGraphic = new CharacterGraphic();
			}
		}
		return characterGraphic;
	}
	private  CharacterGraphic() {
		try {
			info = ImageTool.getImage("images/info_bar.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
	}
	
	public void drawStaticPlayer(Graphics g , Player player)
	{
		this.drawPlayer(g, player, player.getSprite().getStaticImage());
	}
	
	public void drawAnimatingPlayer(Graphics g ,Player player)
	{
		if(!player.equals(DOM.getSelf()))
		{
			setPlayerMovingFrame(player);
			this.drawPlayer(g, player, player.getSprite().getAnimation().getSprite()); // draw other
		}
		else 
			this.drawPlayer(g, player, player.getSprite().getAnimation().getSprite()); //draw self
	}
	private void drawPlayer(Graphics g , Player player , BufferedImage image)
	{
		drawFigure(g, player, image);
		drawHealth(g, player);
		drawInformation(g);
	}
	private void drawFigure(Graphics g , Player player , BufferedImage image){
		if(player.getNumber() == Self.number)//draw self
		{
			g.drawImage(image, // image
			player.getCordinateXInPanel(), //location x
			player.getCordinateYInPanel(), //location y
			100, //width
			100, //height
			null);
		} 
		else // draw other
		{
			if(BackgroundGraphics.getGraph().inMap(player))
			{
				g.drawImage(image, 
						player.getCordinateXInPanel(),
						player.getCordinateYInPanel(), 
						100, 
						100, 
						null);
			}
		}
	}
	private void drawHealth(Graphics g , Player player)
	{	
		if(player.getCharacter().getCurrentHP() ==0) return;
		int x = player.getCordinateXInPanel();
		int y = player.getCordinateYInPanel();
		g.setColor(player.getTeam() == Self.team ? new Color(50, 205, 50) : Color.red);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(7));
		int health = 100 / player.getCharacter().getMaxHP();
		g2.drawLine(x,
		    		y+100,
		    		x+(health*player.getCharacter().getCurrentHP()),
		    		y+100);  
		/*g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.WHITE);
		g2.drawRect(x, y + 100, 100, 5);*/
		
	}
	private void drawInformation(Graphics g){
			g.drawImage(info, 0,0,250,50,null);
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.BOLD,15));
			
			
			Player self = DOM.getSelf();
			String health = Parser.toString(self.getCharacter().getCurrentHP());
			String attackpower = Parser.toString(self.getCharacter().getAttackPower());
			String type = Parser.parseCharacterToChinese(self.getCharacter().getType());
			String reviveTime = Parser.toString(self.getReviveTime());
			
			g.drawString(health,40, 30); // health
			g.drawString(attackpower,95, 30); // attack power
			//if(self.IsDead())
				g.drawString(reviveTime, 145 , 30);
			g.drawString(type, 180, 30); // character type
			
	}
	private void setPlayerMovingFrame(Player player){
		switch(player.getSprite().getFacing())
		{
		case north:
			player.getSprite().moveNorth();
			break;
		case north_east:
			player.getSprite().moveNorthEast();
			break;
		case north_west:
			player.getSprite().moveNorthWest();
			break;
		case south:
			player.getSprite().moveSouth();
			break;
		case south_east:
			player.getSprite().moveSouthEast();
			break;
		case south_west:
			player.getSprite().moveSouthWest();
			break;
		case east:
			player.getSprite().moveEast();
			break;
		case west:
			player.getSprite().moveWest();
			break;
		}
	}
	
	
}


