package dom;

import java.util.ArrayList;

import handler.ItemHandler;
import item.Item;

public class ItemTable {
	private static ItemTable itemTable;
	
	private static ArrayList<Item> clientItem = new ArrayList<Item>();
	
	public static ItemTable getItemTable()
	{
		if (itemTable== null)
			itemTable = new ItemTable();
		
		return itemTable;
	}
	
	public ArrayList<Item> getTable(){
		return clientItem;
	}
}
