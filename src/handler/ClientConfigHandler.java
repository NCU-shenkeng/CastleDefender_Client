package handler;

import cfg.Self;
import dom.EngineTable;
import main.GameFrame;
import panel.LosePanel;
import panel.WinPanel;
import udp.Packet;

public class ClientConfigHandler 
{
	public static void setSelfIDAndTeam(Packet packet){
		int number = Integer.parseInt(packet.getArgs().get(0));
		int team = Integer.parseInt(packet.getArgs().get(1));
		
		Self.number = number;
		Self.team = team;
	
	}
	
	public static void setWinnerOrLoser(Packet packet){
		
		EngineTable.stopAllEngine();
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
	
}
