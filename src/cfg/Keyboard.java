package cfg;

import org.omg.PortableServer.ServantActivator;

public class Keyboard {
	private static boolean UP = false;
	private static boolean DOWN = false;
	private static boolean LEFT = false;
	private static boolean RIGHT = false;

	
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
	
	public static boolean isKeyBoardDown(){
		return (UP || RIGHT || DOWN || LEFT);
	}
	
}
