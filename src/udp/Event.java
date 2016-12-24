package udp;

public class Event
{ // int [.....]
	public static final int PLAYER_SELECT_OK = 1;// playerID,teamID
	public static final int GAME_START = 2;//
	public static final int PLAYER_HP_CHANGE = 3;// playerID,HP
	public static final int PLAYER_ATTACK = 4;// playerID
	public static final int PLAYER_INJURY = 5;// playerID
	public static final int PLAYER_LOCATION_CHANGE = 6;// playerID,x,y,面向,TP
	public static final int PLAYER_DEAD = 7;// playerID
	public static final int PLAYER_REVIVE = 8;// playerID
	public static final int PLAYER_ITEM_START = 9;// playerID , lefttime , fulltime
	public static final int PLAYER_ITEM_SUCCESS = 10;// playerID
	public static final int PLAYER_ITEM_FAIL = 11;// playerID
	public static final int MAP_ITEM_ADD = 12;// type,x,y
	public static final int MAP_ITEM_REMOVE = 13;// x,y
	public static final int CASTLE_HP_CHANGE = 14;// teamID,HP
	public static final int CASTLE_BUFF_ADD = 15;// teamID,itemType,time (棄用)
	public static final int CASTLE_BUFF_REMOVE = 16;// teamID,itemType,time (棄用)
	public static final int GAME_OVER = 17;// winTeamID
	public static final int CASTLE_BUFF_LIST = 18;// teamID [type time]...

	private Event()
	{

	}
}
