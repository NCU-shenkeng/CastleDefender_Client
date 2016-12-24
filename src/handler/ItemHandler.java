package handler;

import java.util.ArrayList;
import item.Item;

public class ItemHandler
{
	private static ItemHandler itemHandler;
	private ArrayList<Item> clientItem = new ArrayList<Item>();
	
	private ItemHandler()
	{		
	}
	
	public static ItemHandler getItemHandler()
	{
		if (itemHandler == null)
			itemHandler = new ItemHandler();
		
		return itemHandler;
	}
	
	//TODO Input need to match server side
	public void AddItemByArrayList(ArrayList<Item> itemList)
	{
		for (int i = 0; i < itemList.size(); i ++)
		{
			clientItem.add(itemList.get(i));
		}
	}
	
	public void AddItemByElement(Item item)
	{
		this.clientItem.add(item);
	}	
	
	public void RemoveItemByArrayList(ArrayList<Item> itemList)
	{
		for (int i = 0; i < clientItem.size(); i ++)
		{
			for (int j = 0; j < itemList.size(); j ++)
			{
				if (clientItem.get(i) == itemList.get(j))
					clientItem.remove(i);
			}
		}		
	}
	
	public void RemoveItemByElement(Item item)
	{
		for (int i = 0; i < clientItem.size(); i++)
		{
			if (clientItem.get(i) == item)
				clientItem.remove(i);
		}
	}
	
	public ArrayList<Item> getClientItem()
	{
		return clientItem;
	}
}
