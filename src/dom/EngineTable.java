package dom;

import java.util.ArrayList;

import render.Engine;

public class EngineTable {
	
	
	public static ArrayList<Engine> engineTable = new ArrayList<Engine>();
	
	public static void stopAllEngine(){
		for(Engine engine : engineTable)
			engine.stop();
	}
}
