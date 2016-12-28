package graphics;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import cfg.Keyboard;
import cfg.Self;
import dom.DOM;

import player.Player;


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
	private  CharacterGraphic() {}

	public void paint(Graphics g) {
		
		for(Player player : DOM.getPlayerTable())
		{		
			if(player.getIsDead()){
				CharacterGraphic.getGraphic().drawDeadPlayer(g, player);
				continue;
			}
			if(player.getSprite().getIsAnimating())
				CharacterGraphic.getGraphic().drawAnimatingPlayer(g, player);
			else 
				CharacterGraphic.getGraphic().drawStaticPlayer(g, player);
		}

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
		{
			setSelf();
			this.drawPlayer(g, player, player.getSprite().getAnimation().getSprite()); // draw other
		}
	}

	public void drawDeadPlayer(Graphics g , Player player){
		drawFigure(g, player, player.getSprite().getDeadImage());
	}
	
	private void drawPlayer(Graphics g , Player player , BufferedImage image)
	{
		drawFigure(g, player, image);
		drawHealth(g, player);
	}
	private void drawFigure(Graphics g , Player player , BufferedImage image){
		if(player.getNumber() == Self.number)//draw self
		{
			g.drawImage(image, // image
			player.getCordinateXInPanel()+8, //location x
			player.getCordinateYInPanel()-25, //location y
			80, //width
			100, //height
			null);
		} 
		else // draw other
		{
			if(BackgroundGraphics.getGraph().inMap(player))
			{
				g.drawImage(image, 
						player.getCordinateXInPanel()+3,
						player.getCordinateYInPanel()-25, 
						80, 
						100, 
						null);
			}
		}
	}
	private void drawHealth(Graphics g , Player player)
	{	
		if(player.getCharacter().getCurrentHP() ==0) return;
		int x = player.getCordinateXInPanel();
		int y = player.getCordinateYInPanel()-25;
		g.setColor(player.getTeam() == Self.team ? new Color(50, 205, 50) : Color.red);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(7));
		int health = 100 / player.getCharacter().getMaxHP();
		g2.drawLine(x,
		    		y+100,
		    		x+(health*player.getCharacter().getCurrentHP()),
		    		y+100);  
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.WHITE);
		g2.drawRect(x-5, y + 97, 108, 6);
		
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
	private void setSelf(){
		Player self = DOM.getSelf();
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
		else if(Keyboard.getSpace())
			self.getSprite().attack();
	}
	
}