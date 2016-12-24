package cfg;

import org.omg.PortableServer.ServantActivator;

public class Keyboard {
	private static boolean UP = false;
	private static boolean DOWN = false;
	private static boolean LEFT = false;
	private static boolean RIGHT = false;
	private static boolean SPACE = false;
	
	
	public static boolean getSpace(){
		return SPACE;
	}
	
	public static boolean getUpLeft(){
		return (UP && LEFT);
	}
	public static boolean getUpRightt(){
		return (UP && RIGHT);
	}
	public static boolean getDownLeft(){
		return (DOWN && LEFT);
	}
	public static boolean getDownRight(){
		return (DOWN && RIGHT);
	}
	public static boolean getLeft(){
		return (LEFT);
	}
	public static boolean getRight(){
		return (RIGHT);
	}
	public static boolean getUp(){
		return (UP);
	}
	public static boolean getDown(){
		return (DOWN);
	}
	
	public static int getUpInt(){
		return UP ? 1 : 0;
	}
	public static int getLeftInt(){
		return LEFT ? 1 : 0;
	}
	public static int getDownInt(){
		return DOWN ? 1 : 0;
	}
	public static int getRightInt(){
		return RIGHT ? 1 : 0;
	}
	
	public static void setLeft(boolean b){
		LEFT = b;
	}
	public static void setRight(boolean b){
		RIGHT = b;
	}
	public static void setUp(boolean b){
		UP = b;
	}
	public static void setDown(boolean b){
		DOWN = b;
	}
	public static void setSpace(boolean b){
		SPACE = b;
	}
	
	public static boolean isKeyBoardDown(){
		return (UP || RIGHT || DOWN || LEFT || SPACE);
	}
	
}
