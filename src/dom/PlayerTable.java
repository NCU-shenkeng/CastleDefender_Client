package dom;

import java.util.ArrayList;

import player.Player;

public class PlayerTable
{
	private static PlayerTable playerTable = null;
	private static ArrayList<Player> table =new ArrayList<Player>();
	
	public static PlayerTable getPlayerTable()
	{
		if(playerTable == null)
		{
			synchronized (PlayerTable.class) 
			{
				playerTable = new PlayerTable();
			}
		}
		return playerTable;
	}
	
	public ArrayList<Player> getTable(){
		return this.table;
	}
}
