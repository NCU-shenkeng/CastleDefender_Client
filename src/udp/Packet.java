package udp;

import java.util.ArrayList;

public class Packet
{
	private int event = 9999;
	private ArrayList<String> args;
	
	public Packet()
	{
		args = new ArrayList<>();
	}
	
	public int getEvent()
	{
		return event;
	}
	
	public void setEvent(int event)
	{
		this.event = event;
	}
	
	public ArrayList<String> getArgs()
	{
		return args;
	}
	
}
