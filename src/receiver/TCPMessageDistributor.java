package receiver;

import java.util.Vector;

import handler.ClientConfigHandler;
import handler.PlayerHandler;
import udp.Packet;
import udp.ServerMessageDecoder;

public class TCPMessageDistributor {
	
	private int eventCode;
	
	public void distributeMessage(String msg)
	{
		System.out.println(msg);
		Vector<Packet> packets = decodeMessage(msg);
		for(Packet packet : packets)
		{
			eventCode = packet.getEvent();
			switch(eventCode)
			{
			case tcp.Event.HEART_BEAT:
				break;
			case tcp.Event.LOAD_DONE:
				break;
			case tcp.Event.PLAYER_ATTACK:
				break;
			case tcp.Event.PLAYER_GET_ITEM:
				break;
			case tcp.Event.PLAYER_MOVE:
				break;
			case tcp.Event.PLAYER_SELECT_CHART:
				break;
			case tcp.Event.WELCOM:
				ClientConfigHandler.setSelfIDAndTeam(packet);
				break;
			case tcp.Event.ROOM_FULL:
				System.out.println("room full");
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
