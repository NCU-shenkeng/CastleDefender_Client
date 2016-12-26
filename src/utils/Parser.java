package utils;

import cfg.DirectionType;
import character.CharacterType;

public class Parser {
	public static boolean parseBoolean(String input){
		return(input.equals("1") ? true : false);
	}
	public static DirectionType parseDirection(String input)
	{
		switch(input)
		{
			case "0":
				return DirectionType.north;
			case "1":
				return DirectionType.north_west;
			case "2":
				return DirectionType.west;
			case "3":
				return DirectionType.south_west;
			case "4":
				return DirectionType.south;
			case "5":
				return DirectionType.south_east;
			case "6":
				return DirectionType.east;
			case "7":
				return DirectionType.north_east;
		}
		return null;
	}
	public static String parseCharacterToChinese(CharacterType character){
		switch(character)
		{
		case Mage:
			return "½åªÌ";
		case Guard:
			return "¨Í½Ã";
		case OrdinaryPeople:
			return "¥­¥Á";
		}
		return null;
		
	}
	public static String toString(int input){
		return Integer.toString(input);
	}
	public static int parseDirectionToInt(DirectionType direction){
		switch(direction)
		{
			case north:
				return 0;
			case north_west:
				return 1;
			case west:
				return 2;
			case south_west:
				return 3;
			case south:
				return 4;
			case south_east:
				return 5;
			case east:
				return 6;
			case north_east:
				return 7;
		}
		return -1;
	}
	public static int parseBoolean(boolean bool){
		return (bool ? 1 : 0);
	}
	
	public static CharacterType parseCharacterType(int character){
		switch(character)
		{
			case 0:
				return CharacterType.Guard;
			case 1:
				return CharacterType.Mage;
			case 2:
				return CharacterType.OrdinaryPeople;
		}
		return null;
	}
}
