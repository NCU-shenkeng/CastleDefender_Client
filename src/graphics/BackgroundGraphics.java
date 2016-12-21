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
import main.GameFrame;
import player.Player;
public class BackgroundGraphics extends Graph implements KeyListener, ActionListener{

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
		this.BlockX = 4 + 9;
		this.BlockY = 3 + 46;		
	}
	
	private void OpenImge (BufferedImage img[])
	{
		String path[] = {"images/backgound/ROCK.bmp",
						 "images/backgound/SAND1.bmp",
						 "images/backgound/SAND2.bmp",
						 "images/backgound/GRASS1.bmp",
						 "images/backgound/GRASS2.bmp",
						 "images/backgound/BLACK.bmp",
						 "images/backgound/FLOOR1.bmp",
						 "images/backgound/FLOOR2.bmp",
						 "images/backgound/DRAIN.bmp"};
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

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		switch (arg0.getKeyCode())
		{
			case KeyEvent.VK_UP:
				//SetBlockValue(0, -1);
				Key[0] = 1;
			try {
				GameFrame.getGame().refresh();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case KeyEvent.VK_LEFT:
				//SetBlockValue(-1, 0);
				Key[1] = 1;
				break;
			case KeyEvent.VK_DOWN:
				//SetBlockValue(0, 1);
				Key[2] = 1;
				break;
			case KeyEvent.VK_RIGHT:
				//SetBlockValue(1, 0);
				Key[3] = 1;
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		switch (arg0.getKeyCode())
		{
			case KeyEvent.VK_UP:
				//SetBlockValue(0, -1);
				Key[0] = 0;
				break;
			case KeyEvent.VK_LEFT:
				//SetBlockValue(-1, 0);
				Key[1] = 0;
				break;
			case KeyEvent.VK_DOWN:
				//SetBlockValue(0, 1);
				Key[2] = 0;
				break;
			case KeyEvent.VK_RIGHT:
				//SetBlockValue(1, 0);
				Key[3] = 0;
				break;
			default:
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(walkCD > 0)
		{
			walkCD -= 10;
		}
		else
		{
			if(Key[1] + Key[3] + Key[0] + Key[2] != 0)
			{
				SetBlockValue((Key[1] * -1)+(Key[3]), (Key[0] * -1)+(Key[2]));
				walkCD += walkDelay;
			}
		}

	}

}
