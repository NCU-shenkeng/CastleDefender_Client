package monitor;

import render.GameEngine;

public class Monitor implements Runnable{
	private static Monitor instance = null;
	
	
	private boolean running = false;
	public static Monitor getInstance(){
		if(instance == null){
			synchronized (Monitor.class) {
				instance = new Monitor();
			}
		}
		return instance;
	}
	
	public void start(){
		this.running = true;
		new Thread(this , "monitor").start();
	}
	public void stop(){
		this.running = false;
	}

	private Monitor(){}

	@Override
	public void run() {
		while(running)
		{
			if(!GameEngine.getEngine().getThreadStatus() && running){
				GameEngine.getEngine().restart();
			}
				
		}
	}
}