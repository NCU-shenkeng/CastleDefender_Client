package handler;

import dom.DOM;
import item.Item;
import udp.Packet;

public class ItemHandler
{
	public static void addItem(Packet packet){
		int type = Integer.parseInt(packet.getArgs().get(0));
		int x = Integer.parseInt(packet.getArgs().get(1));
		int y = Integer.parseInt(packet.getArgs().get(2));
		
		DOM.AddItemByElement(new Item(type , x , y));
	}
	
	public static void removeItem(Packet packet){
		int x = Integer.parseInt(packet.getArgs().get(0));
		int y = Integer.parseInt(packet.getArgs().get(1));
		
		DOM.RemoveItemByElement(x, y);
	}
}
