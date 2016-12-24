package handler;

import cfg.Self;
import udp.Packet;

public class ClientConfigHandler {
	public static void setSelfIDAndTeam(Packet packet){
		int number = Integer.parseInt(packet.getArgs().get(0));
		int team = Integer.parseInt(packet.getArgs().get(1));
		
		Self.number = number;
		Self.team = team;
	
	}
	
}
