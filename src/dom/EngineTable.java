package dom;

import java.util.ArrayList;

import render.Engine;

public class EngineTable {
	
	
	public static ArrayList<Engine> engineTable = new ArrayList<Engine>();
	public static ArrayList<Thread> threadTable = new ArrayList<Thread>();
	
	public static void stopAllEngine(){
		for(Engine engine : engineTable){
			engine.stop();
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void startAllEngine(){
		for(Engine engine : engineTable){
			engine.start();
		}
			
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
