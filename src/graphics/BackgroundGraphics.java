package graphics;

import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import background.BasicBlockType;
import background.Map;
import cfg.Self;
import dom.DOM;
import main.GameFrame;
import player.Player;
public class BackgroundGraphics extends Graph
{

	public Graphics g;
	private int BlockX;
	private int BlockY;
	private Map map;
	private BufferedImage img[] = new BufferedImage[BasicBlockType.NUMOFBLOCK];
	private final String path[] = {"images/backgound/ROCK.bmp",
			 					   "images/backgound/SAND1.bmp",
			 					   "images/backgound/SAND2.bmp",
			 					   "images/backgound/GRASS1.bmp",
			 					   "images/backgound/GRASS2.bmp",
			 					   "images/backgound/BLACK.bmp",
			 					   "images/backgound/FLOOR1.bmp",
			 					   "images/backgound/FLOOR2.bmp",
			 					   "images/backgound/DRAIN.bmp",
			 					   "images/backgound/CASTLE_A1.png",
			 					   "images/backgound/CASTLE_A2.png",
			 					   "images/backgound/CASTLE_A3.png",
			 					   "images/backgound/CASTLE_A4.png",
			 					   "images/backgound/CASTLE_A5.png",
			 					   "images/backgound/CASTLE_A6.png",
			 					   "images/backgound/CASTLE_A7.png",
			 					   "images/backgound/CASTLE_A8.png",
			 					   "images/backgound/CASTLE_A9.png"};
	
	int userViewWidth = 1100;
	int userViewHigh = 700;
	int delay = 20; //milliseconds
	int walkDelay = 200;
	int walkCD = 0;
	public int[] Key = {0, 0, 0, 0};
	
	private static BackgroundGraphics graph = null;
	
	private BackgroundGraphics() throws IOException
	{
		map = new Map();
		map.CreateMap();
		OpenImge(img);
		InitLocation();
	}
	
	public static BackgroundGraphics getGraph(){
		if(graph == null)
		{
			synchronized(BackgroundGraphics.class){
				try {
					graph = new BackgroundGraphics();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return graph;
			}
		}
		return graph;
	}
	
	public void SetBlockValue(int offsetX, int offsetY)
	{
		if (map.scene[BlockX + offsetX][BlockY + offsetY].canPass)
		{
			this.BlockX = BlockX + offsetX;
			this.BlockY = BlockY + offsetY;
		}

	}
	
	public void setBlockX(int blockX){
		this.BlockX = blockX;
	}
	public void setBlockY(int blockY){
		this.BlockY = blockY;
	}
	
	public boolean canPass(int x , int y){
		return map.scene[x][y].canPass;
	}
	
	public void paint(Graphics g)
	{
		int blockSize = 100;
		int blockWidthOffset = ((userViewWidth / blockSize) / 2);
		int blockhighOffset = ((userViewHigh / blockSize) / 2);
	    int userviewX = 0;
	    int userviewY = 0;
	    
		for (int i = BlockX - blockWidthOffset; i <= BlockX + blockWidthOffset; i++)
		{
		  for (int j = BlockY - blockhighOffset; j <= BlockY + blockhighOffset; j++)
		  {
			  if (map.scene[i][j].type >= BasicBlockType.CASTLE_A1)
				  g.drawImage(img[map.scene[i][j].type], userviewX, userviewY, 100, 100, null);
			  else
				  g.drawImage(img[map.scene[i][j].type], userviewX, userviewY, 100, 100, null);
			  
			  userviewY = userviewY + blockSize;
		  }
		  userviewX = userviewX + blockSize;
		  userviewY = 0;
		}	
	}
	public void moveRight(){
		this.SetBlockValue(1, 0);
	}
	public void moveLeft(){
		this.SetBlockValue(-1, 0);
	}
	public void moveUp(){
		this.SetBlockValue(0, -1);
	}
	public void moveBot(){
		this.SetBlockValue(0, 1);
	}
	
	public int getBlockX(){
		return this.BlockX;
	}
	public int getBlockY(){
		return this.BlockY;
	}
	
	public int getBlockOffsetX(){
		return ((userViewWidth / 100) / 2);
	}
	
	public int getBlockOffsetY(){
		return ((userViewHigh / 100) / 2);
	}
	
	
	public boolean inMap(Player player)
	{
		int playerX = player.getSprite().getX();
		int playerY = player.getSprite().getY();
		int centerX = getBlockX();
		int centerY = getBlockY();
		int offsetX = getBlockOffsetX();
		int offsetY = getBlockOffsetY();
		
		if(playerX < centerX+offsetX+1 && playerX > centerX - offsetX-1 &&
		   playerY < centerY+offsetY+1  && playerY > centerY - offsetY-1)
			return true;
		return false;
	}
	
	private void InitLocation()
	{
		try{
			this.BlockX = DOM.getSelf().getSprite().getX();
			this.BlockY = DOM.getSelf().getSprite().getY();
		}
		catch(Exception e){
			this.BlockX = 13;
			this.BlockY = 49;
		}
		
	}
	
	private void OpenImge (BufferedImage img[])
	{
		File origFile;
		
		for (int i = 0; i < BasicBlockType.NUMOFBLOCK; i++)
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
		}
	}
}
