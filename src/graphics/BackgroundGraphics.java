package graphics;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import background.BackgroundImagePath;
import background.BasicBlockType;
import background.Map;
import cfg.Self;
import dom.DOM;
import main.GameFrame;
import player.Player;
import render.GameEngine;
public class BackgroundGraphics extends Graph
{

	public Graphics g;
	private int BlockX;
	private int BlockY;
	private Map map;
	private BufferedImage img[] = new BufferedImage[BasicBlockType.NUMOFBLOCK];

	int userViewWidth = 1100;
	int userViewHigh = 700;
	int delay = 20; //milliseconds
	int walkDelay = 200;
	int walkCD = 0;
	public int[] Key = {0, 0, 0, 0};
	private boolean locationChange;
	private int fullFram = 0;
	private int fram = 0;
	private int OBlockX = 0;
	private int OBlockY = 0;
	
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
	
	public void SetBlockValueWithMoveFlow(int offsetX, int offsetY)
	{

		OBlockX = BlockX;
		OBlockY = BlockY;
		
		if (fram < 1)
		{
			locationChange = true;
		}
		
		SetBlockValue(offsetX, offsetY);
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
		// System.out.print("X:" + BlockX + " Y:" + BlockY + " ");
		int userviewX = 0;
		int userviewY = 0;
		int appendX = 0;
		int appendY = 0;
		int appendLoadX = 0;
		int appendLoadY = 0;
		int blockSize = 100;
		int blockWidthOffset = ((userViewWidth / blockSize) / 2);
		int blockhighOffset = ((userViewHigh / blockSize) / 2);
		float moveBlockSize = (blockSize / (float)((DOM.getSelf().getCharacter().getSpeed()*1000) / (float)(1000 / GameEngine.getEngine().getFps())));
		int appendLoadLeft = 0;
		int appendLoadRight = 0;
		int appendLoadTop = 0;
		int appendLoadDown = 0;
		int moveYOffset = 0;

		if (locationChange)
		{
			fullFram = (int)((float)(DOM.getSelf().getCharacter().getSpeed()*1000) / (float)(1000 / GameEngine.getEngine().getFps()));
			System.out.println(String.format("movesize %s fullFram %s fram %s speed %s fps %s calc %s",moveBlockSize,fullFram,fram,DOM.getSelf().getCharacter().getSpeed(),GameEngine.getEngine().getFps(),(int)((float)(DOM.getSelf().getCharacter().getSpeed()*1000) / (float)(1000 / GameEngine.getEngine().getFps()))));
			fram = fullFram;
		}

		if (fram > 0)
		{
			
			locationChange = false;
			appendLoadX = BlockX - OBlockX;
			appendLoadY = BlockY - OBlockY;
			appendLoadLeft = 0;
			appendLoadRight = 0;
			moveYOffset = 0;
			
			if (appendLoadX < 0)
			{
				userviewX -= blockSize;
				appendLoadLeft = appendLoadX;
				appendLoadRight = -appendLoadX;
			}else if (appendLoadX > 0) {
				userviewX -= blockSize;
				appendLoadLeft = -appendLoadX;
				appendLoadRight = appendLoadX;
			}
			
			if ((BlockX - blockWidthOffset + appendLoadLeft) < 0)
			{
				userviewX = 0;
				appendLoadLeft = 0;
			}
			if ((BlockX + blockWidthOffset + appendLoadRight) >= map.scene.length)
			{
				appendLoadRight = 0;
			}
			
			if (appendLoadY < 0)
			{
				appendLoadTop = 0;
				appendLoadDown = -appendLoadY;
			}else if (appendLoadY > 0) {
				userviewY -= blockSize;
				moveYOffset = userviewY;
				appendLoadTop = -appendLoadY;
				appendLoadDown = appendLoadY;
			}
			
			if ((BlockY - blockhighOffset + appendLoadTop) < 0)
			{
				appendLoadTop = 0;
			}
			if ((BlockY + blockhighOffset + appendLoadDown) >= map.scene[0].length)
			{
				appendLoadDown = 0;
			}
			
			appendX = (int)(moveBlockSize * (fram * appendLoadX));
			appendY = (int)(moveBlockSize * (fram * appendLoadY));
			System.out.println("fram:" + fram + " appendX:" + appendX + " appendY:" + appendY + " appendLoadX:" + appendLoadX + " appendLoadY:" + appendLoadY + " " + BlockX + " " + BlockY + " " + OBlockX + " " + OBlockY);
			fram--;
		}else{
			appendLoadLeft = 0;
			appendLoadRight = 0;
			appendLoadTop = 0;
			appendLoadDown = 0;
			moveYOffset = 0;
		}
		for (int i = BlockX - blockWidthOffset + appendLoadLeft; i <= BlockX + blockWidthOffset + appendLoadRight; i++)
		{
			for (int j = BlockY - blockhighOffset + appendLoadTop; j <= BlockY + blockhighOffset + appendLoadDown; j++)
			{
				if (map.scene[i][j].type >= BasicBlockType.CASTLE_A1)
					g.drawImage(img[BasicBlockType.GRASS1], userviewX + appendX, userviewY + appendY, 100, 100, null);

				g.drawImage(img[map.scene[i][j].type], userviewX + appendX, userviewY + appendY, 100, 100, null);
				
				ItemGraphic.getGraph().drawItemAtBaground(g, i, j, userviewX + 25, userviewY + 25, appendX, appendY);
				
				g.setColor(Color.WHITE);
				g.drawRect (userviewX + appendX, userviewY + appendY, 100, 100);
				g.drawString(String.format("(%s,%s)", i,j), userviewX + appendX + 2, userviewY + appendY - 5);

				userviewY = userviewY + blockSize;
			}
			userviewX = userviewX + blockSize;
			userviewY = moveYOffset;
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
			origFile = new File("images/backgound/" + BackgroundImagePath.path[i] + ".bmp");
			
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
