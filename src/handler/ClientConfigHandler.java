
package handler;

import java.io.IOException;

import javax.swing.text.html.parser.Parser;

import cfg.DirectionType;
import cfg.Self;
import character.CharacterType;
import dom.DOM;
import dom.EngineTable;
import graphics.BackgroundGraphics;
import main.GameFrame;
import panel.GamePanel;
import panel.LosePanel;
import panel.WinPanel;
import player.Player;
import render.Engine;
import render.GameEngine;
import tcp.TCPClient;
import udp.Packet;
import udp.Server;

public class ClientConfigHandler 
{
	public static void setSelfIDAndTeam(Packet packet){
		int number = Integer.parseInt(packet.getArgs().get(0));
		int team = Integer.parseInt(packet.getArgs().get(1));
		
		Self.number = number;
		Self.team = team;
		
		System.out.println(Self.number);
	}
	
	
	public static void setWinnerOrLoser(Packet packet){
		
		GameEngine.getEngine().stopEngine();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int number = Integer.parseInt(packet.getArgs().get(0));
		try
		{
			if(number == Self.team)
				GameFrame.getGame().changeScreen(new WinPanel());
			else
				GameFrame.getGame().changeScreen(new LosePanel());
		}
		catch(Exception e){
			
		}			
	}
	public static void addPlayer(Packet packet)
	{
		int number = Integer.parseInt(packet.getArgs().get(0));
		int team = Integer.parseInt(packet.getArgs().get(1));
		CharacterType type = utils.Parser.parseCharacterType(Integer.parseInt(packet.getArgs().get(2)));
		
		DOM.addPlayer(new Player(number, type, team, 50, 11));
	}
	public static void initPlayerLocation(Packet packet){
		int number = Integer.parseInt(packet.getArgs().get(0));
		int x = Integer.parseInt(packet.getArgs().get(1));
		int y = Integer.parseInt(packet.getArgs().get(2));
		DirectionType facing = utils.Parser.parseDirection(packet.getArgs().get(3));
		
		
		if(number == Self.number){
			BackgroundGraphics.getGraph().setBlockX(x);
			BackgroundGraphics.getGraph().setBlockY(y);
		}
		
		DOM.updatePlayerLocation(number, x, y);
		DOM.updatePlayerFacing(number, facing);
	}
	public static void startGame(){
		try 
		{
			GameFrame.getGame().changeScreen(GamePanel.getGame());
			GameEngine.getEngine().startEngine();
			new Thread(GameEngine.getEngine()).start();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	public static void closeGame(){
		DOM.reset();
		TCPClient.getInstance().reset();
		Server.getUDPUS().reset();
		GamePanel.getGame().reset();
	}
}