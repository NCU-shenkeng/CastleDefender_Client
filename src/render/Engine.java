package render;

import java.awt.image.BufferedImage;

import cfg.Window;

public abstract class Engine implements Runnable
{
	
	private boolean running = true;
	private long fps = 60;
	private long ms = 1000 / fps;
	
	protected BufferedImage buffer;

	public Engine()
	{
		buffer = new BufferedImage(Window.WIDTH, Window.HEIGHT,BufferedImage.TYPE_INT_ARGB);
	}
	
	@Override
	public void run()
	{
		long lastTime = System.currentTimeMillis();
		
		while(running)
		{
			long now = System.currentTimeMillis();
			if(lastTime + ms <= now){
				lastTime = now;
				tick();
			}
				
			render();
		}
	}
	
	public abstract void tick();
	public abstract void render();
	public abstract void update();
	
	public boolean getRunningState(){
		return running;
	}
	public void setFPS(int fps){
		this.fps = fps;
		ms = 1000 / fps;
	}
	public void stop(){
		this.running = false;
	}
}
