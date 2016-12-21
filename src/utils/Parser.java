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
}
