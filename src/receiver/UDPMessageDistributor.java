package receiver;

import java.util.Vector;

import handler.ItemHandler;
import handler.PlayerHandler;
import player.Player;
import udp.Packet;
import udp.ServerMessageDecoder;

public class UDPMessageDistributor {
	
	private int eventCode;
	
	
	
	public void distributeMessage(String message)
	{
		Vector<Packet> packets = decodeMessage(message);
		for(Packet packet : packets)
		{
			eventCode = packet.getEvent();
			switch(eventCode)
			{
				case udp.Event.CASTLE_BUFF_ADD:
					break;
				case udp.Event.CASTLE_BUFF_LIST:
					break;
				case udp.Event.CASTLE_BUFF_REMOVE:
					break;
				case udp.Event.CASTLE_HP_CHANGE:
					break;
				case udp.Event.GAME_OVER:
					break;
				case udp.Event.GAME_START:
					break;
				case udp.Event.MAP_ITEM_ADD:
					ItemHandler.addItem(packet);
					break;
				case udp.Event.MAP_ITEM_REMOVE:
					ItemHandler.removeItem(packet);
					break;
				case udp.Event.PLAYER_ATTACK:
					break;
				case udp.Event.PLAYER_DEAD:
					PlayerHandler.handleDead(packet);
					break;
				case udp.Event.PLAYER_HP_CHANGE:
					PlayerHandler.handleHealthChange(packet);
					break;
				case udp.Event.PLAYER_INJURY:
					PlayerHandler.handleInjury(packet);
					break;
				case udp.Event.PLAYER_ITEM_FAIL:
					PlayerHandler.handlePickFail(packet);
					break;
				case udp.Event.PLAYER_ITEM_START:
					PlayerHandler.handlePickStart(packet);
					break;
				case udp.Event.PLAYER_ITEM_SUCCESS:
					break;
				case udp.Event.PLAYER_LOCATION_CHANGE:
					PlayerHandler.handlerLocationChange(packet);
					break;
				case udp.Event.PLAYER_REVIVE:
					PlayerHandler.handleRevive(packet);
					break;
				case udp.Event.PLAYER_SELECT_OK:
					break;
					
			}
		}
		
	}
	
	private Vector<Packet> decodeMessage(String message)
	{
		if(message == null) throw new NullPointerException();
		ServerMessageDecoder decoder = new ServerMessageDecoder();
		Vector<Packet> res = decoder.decode(message);
		return res;
	}
}
