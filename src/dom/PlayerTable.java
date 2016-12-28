package dom;

import java.util.ArrayList;
import java.util.HashMap;

import player.Player;

public class PlayerTable
{
	private static PlayerTable playerTable = null;
	private static ArrayList<Player> table = new ArrayList<Player>(4);
	
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
	public boolean isExist(int number){
		try{
			table.get(number);
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	public ArrayList<Player> getTable(){
		return this.table;
	}
	
	public void reset(){
		this.table.clear();
	}
}
