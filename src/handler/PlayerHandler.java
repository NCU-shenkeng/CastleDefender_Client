package handler;

import cfg.DirectionType;
import cfg.Self;
import dom.DOM;
import graphics.EffectGraphics;
import panel.GamePanel;
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
	public static void handlePickStart(Packet packet){
		int number = Integer.parseInt(packet.getArgs().get(0));
		int leftTime = Integer.parseInt(packet.getArgs().get(1));
		int fullTime = Integer.parseInt(packet.getArgs().get(2));
		
		DOM.updatePlayerIsPicking(number, true);
		DOM.updatePlayerPickingFullTime(number, fullTime);
		DOM.updatePlayerPickingLeftTime(number, leftTime);
	}
	
	public static void handlePickFail(Packet packet){
		int number = Integer.parseInt(packet.getArgs().get(0));
		
		DOM.updatePlayerIsPicking(number, false);
		
	}
	public static void handleHealthChange(Packet packet){
		int number = Integer.parseInt(packet.getArgs().get(0));
		int health = Integer.parseInt(packet.getArgs().get(1));
		
		DOM.updatePlayerHealth(number, health);
	}
	public static void handleDead(Packet packet){
		int number = Integer.parseInt(packet.getArgs().get(0));
		int reviveTime = Integer.parseInt(packet.getArgs().get(1));
		
		DOM.updatePlayerIsDead(number, true);
		DOM.updatePlayerReviveTime(number, reviveTime);
		GamePanel.getGame().removeKeyListener(GamePanel.getGame());
	}
	
	public static void handleInjury(Packet packet){
		int number = Integer.parseInt(packet.getArgs().get(0));
		DOM.updatePlayerIsDamage(number, true);
		
		Self.lastTime = System.currentTimeMillis();
	}
	public static void handleRevive(Packet packet){
		int number = Integer.parseInt(packet.getArgs().get(0));
		
		DOM.updatePlayerIsDead(number, false);
		GamePanel.getGame().addKeyListener(GamePanel.getGame());
	}
}
