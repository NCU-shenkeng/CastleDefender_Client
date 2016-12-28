package dom;

import java.util.ArrayList;

import castle.Castle;
import cfg.DirectionType;
import cfg.Self;
import graphics.BackgroundGraphics;
import handler.CastleHandler;
import handler.CastleHandler.buffArray;
import item.Item;
import player.Player;

public class DOM {

	public static Player getPlayer(int number)
	{
		return PlayerTable.getPlayerTable().getTable().get(number);
	}
	public static Player getSelf()
	{
		return getPlayer(Self.number);
	}
	
	public static ArrayList<Player> getPlayerTable(){
		return PlayerTable.getPlayerTable().getTable();
	}
	public static void addPlayer(Player player){
		if(!PlayerTable.getPlayerTable().isExist(player.getNumber())){
			PlayerTable.getPlayerTable().getTable().add(player.getNumber(),player);
			System.out.println("add player : " + player.getNumber());
		}
	}
	public static void updatePlayerHealth(int number , int health){
		Player player = getPlayer(number);
		player.getCharacter().setCurrentHP(health);
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
	public static void updatePlayerIsPicking(int number , boolean bool){
		Player player = getPlayer(number);
		player.getSprite().setPicking(bool);
	}
	public static void updatePlayerIsDamage(int number , boolean bool){
		Player player = getPlayer(number);
		player.getSprite().setIsDamage(bool);
	}
	public static void updatePlayerPickingLeftTime(int number , int leftTime){
		Player player = getPlayer(number);
		player.getSprite().setPickLeftTime(leftTime);
	}
	public static void updatePlayerPickingFullTime(int number , int fullTime){
		Player player = getPlayer(number);
		player.getSprite().setPickFullTime(fullTime);
	}
	
	public static void updatePlayerIsDead(int number , boolean isDead){
		Player player = getPlayer(number);
		player.getSprite().setAnimating(false);
		player.setIsDead(isDead);
		if(isDead) player.getCharacter().setCurrentHP(0);
	}
	public static void updatePlayerReviveTime(int number , int reviveTime){
		Player player = getPlayer(number);
		player.setReviveTime(reviveTime);
	}
	public static void AddItemByArrayList(ArrayList<Item> itemList)
	{
		for (int i = 0; i < itemList.size(); i ++)
		{
			ItemTable.getItemTable().getTable().add(itemList.get(i));
		}
	}
	
	public static void AddItemByElement(Item item)
	{
		ItemTable.getItemTable().getTable().add(item);
	}	
	
	public static void RemoveItemByArrayList(ArrayList<Item> itemList)
	{
		
		ArrayList<Item> list = ItemTable.getItemTable().getTable();
		for (int i = 0; i < list.size(); i ++)
		{
			for (int j = 0; j < list.size(); j ++)
			{
				if (list.get(i) == list.get(j))
					list.remove(i);
			}
		}		
	}
	
	public static void RemoveItemByElement(int x , int y)
	{
		ArrayList<Item> list = ItemTable.getItemTable().getTable();
		Item item = new Item(x,y);
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).equals(item))
				list.remove(i);
		}
	}
	
	public static void UpdateCasltleBuffList(int index,ArrayList<CastleHandler.buffArray> args){
		try {
			Castle castle = CastleTable.getCastleTable().getCastle();
			
			int buffID[] = new int[11];
			int CDR[] = new int[11];
			for (int i = 0; (i < args.size()) && (i < 11); i++) {
				buffID[i] = args.get(i).typeID;
				CDR[i] = ((args.get(i).fulltime - args.get(i).time) * 360) / args.get(i).fulltime;
			}
//			System.out.print(String.format("Self %s index %s :", Self.team, index));
//			for (int i : buffID) {
//				System.out.print(i + " ");
//			}
//			System.out.print("\n");
			
			if(Self.team == index){
//				System.out.println("Self casle");
				castle.setCastleBuff(buffID);
				castle.setCastleBuffCDR(CDR);
			}else{
//				System.out.println("Enemy casle");
				castle.setEnemyCastleBuff(buffID);
				castle.setEnemyBuffCDR(CDR);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void UpdateCastleBlood(int index,String args) {
		try {
			Castle castle = CastleTable.getCastleTable().getCastle();
			
			if(Self.team == index){
				//System.out.println("Self casle " + args);
				castle.setSelfCastleBlood(args);
			}else{
				//System.out.println("Enemy casle " + args);
				castle.setEnemyCastleBlood(args);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void reset(){
		Self.number = 0;
		Self.team = 0;
		CastleTable.getCastleTable().reset();
		ItemTable.getItemTable().reset();
		PlayerTable.getPlayerTable().reset();
	}
}