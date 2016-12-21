package dom;

import cfg.DirectionType;
import player.Player;

public class DOM {
	
	private static int selfID = 0;
	public static Player getPlayer(int number)
	{
		return PlayerTable.getPlayerTable().getTable().get(number);
	}
	public static Player getSelf()
	{
		return getPlayer(selfID);
	}
	public static void updatePlayerLocation(int number , int x , int y)
	{
		Player player = getPlayer(number);
		player.getSprite().setXY(x, y);
	}
	public static void updatePlayerFacing(int number,DirectionType type)
	{
		Player player = getPlayer(number);
		player.getSprite().setFacing(type);
	}
	public static void updatePlayerIsAnimating(int number , boolean bool)
	{
		Player player = getPlayer(number);
		player.getSprite().setAnimating(bool);
	}
}
