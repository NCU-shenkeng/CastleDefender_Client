package graphics;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import dom.DOM;
import dom.ItemTable;
import item.Item;
import handler.ItemHandler;
import item.ItemType;

public class ItemGraphic extends Graph
{
	private static ItemGraphic graph = null;
	private BufferedImage img[] = new BufferedImage[ItemType.NUMOFITEM];
	private static final String path[] = {"images/item/ATTACKSPEED1.bmp",
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
			  							  "images/item/TRANSPORT.bmp"};
	
	//public Graphics g;
	BackgroundGraphics background = BackgroundGraphics.getGraph();
	
	private ItemGraphic()
	{
		OpenImge(img);
		//Test();
	}
	
	public static ItemGraphic getGraph()
	{
		if(graph == null)
		{
			synchronized(ItemGraphic.class)
			{
				graph = new ItemGraphic();
				return graph;
			}
		}
		
		return graph;
	}	
	
	public void paint(Graphics g)
	{	
		ArrayList<Item> itemList = ItemTable.getItemTable().getTable();
		int blockSize = 100;
		int lowerboundX = background.getBlockX() - background.getBlockOffsetX();
		int upperboundX = background.getBlockX() + background.getBlockOffsetX();
		int lowerboundY = background.getBlockY() - background.getBlockOffsetY();
		int upperboundY = background.getBlockY() + background.getBlockOffsetY();

		for (int i = 0; i < itemList.size(); i ++)
		{
			//Point itemXY = itemList.get(i).getItemXY();
			int itemX = (int)itemList.get(i).getItemXY().x;
			int itemY = (int)itemList.get(i).getItemXY().y;
			
			if (lowerboundX <= itemX && itemX <= upperboundX)
			{
				if (lowerboundY <= itemY && itemY <= upperboundY)
				{
					int type = itemList.get(i).getType();
					int blockX = (itemX - lowerboundX) * blockSize + 25;
					int blockY = (itemY - lowerboundY) * blockSize + 50;
					
					if (type != ItemType.NOITEM)
					{
						g.drawImage(img[type], blockX, blockY, 50, 50, null);
					}
				}
			}			
		}
	}
	
	public void drawItemAtBaground(Graphics g,int i,int j,int userviewX,int userviewY,int appendX,int appendY)
	{
		ArrayList<Item> itemList = ItemTable.getItemTable().getTable();
		if (g != null)
		{
			if (itemList != null)
			{
				for (Item item : itemList)
				{
					if (item.getItemXY().getX() == i && item.getItemXY().getY() == j)
					{
						g.drawImage(img[item.getType()], userviewX + appendX, userviewY + appendY, 50, 50, null);
					}
				}
			}
		}
	}
	
	private void OpenImge (BufferedImage img[])
	{
		File file;
		
		for (int i = 0; i < ItemType.NUMOFITEM; i++)
		{
			file = new File(path[i]);
			
			try 
			{
				img[i] = ImageIO.read(file);
			} 
			catch (IOException e) 
			{
				  assert false :"OpenImge error";
				  e.printStackTrace();
			}			
		}
	}	
	
	private void Test()
	{
		ArrayList<Item> clientItem = new ArrayList<Item>();
		Random random = new Random();
		
		Point tempXY;
		
		for (int i = 0; i < 50; i++)
		{
			Item tempitem = new Item();
			tempXY = new Point(random.nextInt(50) + 5, random.nextInt(50) + 4);
			
			tempitem.setType(random.nextInt(14));
			tempitem.setItemXY(tempXY);
			
			clientItem.add(tempitem);
		}

//		tempXY = new Point(14, 50);
//		
//		tempitem.setType(random.nextInt(14));
//		tempitem.setItemXY(tempXY);
//		clientItem.add(tempitem);
		DOM.AddItemByArrayList(clientItem);
	}
}
