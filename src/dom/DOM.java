package dom;

import java.util.ArrayList;

import cfg.DirectionType;
import cfg.Self;
import graphics.BackgroundGraphics;
import item.Item;
import player.Player;

public class DOM {

	public static Player getPlayer(int number)
	{
		return PlayerTable.getPlayerTable().getTable().get(number);
	}
	public static Player getSelf()
	{
		return getPlayer(Self.number);
	}
	
	public static ArrayList<Player> getPlayerTable(){
		return PlayerTable.getPlayerTable().getTable();
	}
	public static void addPlayer(Player player){
		PlayerTable.getPlayerTable().getTable().add(player);
	}
	public static void updatePlayerLocation(int number , int x , int y)
	{
		Player player = getPlayer(number);
		player.getSprite().setXY(x, y);
	}
	public static void updatePlayerFacing(int number,DirectionType type)
	{
		Player player = getPlayer(number);
		player.getSprite().setFacing(type);
	}
	public static void updatePlayerIsAnimating(int number , boolean bool)
	{
		Player player = getPlayer(number);
		player.getSprite().setAnimating(bool);
	}
	public static void updatePlayerIsPicking(int number , boolean bool){
		Player player = getPlayer(number);
		player.getSprite().setPicking(bool);
	}
	public static void updatePlayerPickingLeftTime(int number , int leftTime){
		Player player = getPlayer(number);
		player.getSprite().setPickLeftTime(leftTime);
	}
	public static void updatePlayerPickingFullTime(int number , int fullTime){
		Player player = getPlayer(number);
		player.getSprite().setPickFullTime(fullTime);
	}
	public static void AddItemByArrayList(ArrayList<Item> itemList)
	{
		for (int i = 0; i < itemList.size(); i ++)
		{
			ItemTable.getItemTable().getTable().add(itemList.get(i));
		}
	}
	
	public static void AddItemByElement(Item item)
	{
		ItemTable.getItemTable().getTable().add(item);
	}	
	
	public static void RemoveItemByArrayList(ArrayList<Item> itemList)
	{
		
		ArrayList<Item> list = ItemTable.getItemTable().getTable();
		for (int i = 0; i < list.size(); i ++)
		{
			for (int j = 0; j < list.size(); j ++)
			{
				if (list.get(i) == list.get(j))
					list.remove(i);
			}
		}		
	}
	
	public static void RemoveItemByElement(int x , int y)
	{
		ArrayList<Item> list = ItemTable.getItemTable().getTable();
		Item item = new Item(x,y);
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).equals(item))
				list.remove(i);
		}
	}
}