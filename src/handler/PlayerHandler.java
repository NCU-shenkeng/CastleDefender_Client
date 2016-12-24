package handler;

import cfg.DirectionType;
import cfg.Self;
import dom.DOM;
import udp.Packet;

import utils.Parser;

public class PlayerHandler 
{
	public static void handlerLocationChange(Packet packet)
	{
		int number = Integer.parseInt(packet.getArgs().get(0));
		int x = Integer.parseInt(packet.getArgs().get(1));
		int y = Integer.parseInt(packet.getArgs().get(2));
		DirectionType direction = Parser.parseDirection(packet.getArgs().get(3));
		boolean isAnimating = Parser.parseBoolean(packet.getArgs().get(4));
	
			
		
		//System.out.println("number " + number + " x " + x +" y " + y + " direction " + direction + " isAnimating " + isAnimating);

		
		if(number != Self.number){
			DOM.updatePlayerLocation(number, x, y);
			DOM.updatePlayerIsAnimating(number, isAnimating);
			DOM.updatePlayerFacing(number, direction);
		}
			
	}
	
	
}
