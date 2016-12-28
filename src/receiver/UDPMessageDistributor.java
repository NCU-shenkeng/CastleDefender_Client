
package receiver;

import java.util.Vector;

import cfg.Keyboard;
import cfg.Self;
import dom.DOM;
import handler.CastleHandler;
import handler.ClientConfigHandler;
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
					CastleHandler.castleBuff(packet);
					break;
				case udp.Event.CASTLE_BUFF_REMOVE:
					break;
				case udp.Event.CASTLE_HP_CHANGE:
					CastleHandler.castleBlood(packet);
					break;
				case udp.Event.GAME_OVER:
					ClientConfigHandler.setWinnerOrLoser(packet);
					ClientConfigHandler.closeGame();
					Keyboard.reset();
					break;
				case udp.Event.GAME_START:
					ClientConfigHandler.startGame();
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
					PlayerHandler.handlePickSuccess(packet);
					break;
				case udp.Event.PLAYER_LOCATION_CHANGE:
					PlayerHandler.handlerLocationChange(packet);
					break;
				case udp.Event.PLAYER_REVIVE:
					PlayerHandler.handleRevive(packet);
					break;
				case udp.Event.PLAYER_SELECT_OK:
					ClientConfigHandler.addPlayer(packet);
					break;
				case udp.Event.PLAYER_TELEPORT:
					ClientConfigHandler.initPlayerLocation(packet);
					break;
				case udp.Event.CASTLE_BUFF_HP_CHANGE:
					Self.hpChangeLastTime = System.currentTimeMillis();
					Self.hpchange = true;
					System.out.println("message receive");
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
