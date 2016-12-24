package cfg;

import java.io.IOException;

import menuitem.menuItem;

public class Selection{
	
	
	public final static menuItem[] list = getList();
	
	private static menuItem[] getList()
	{
		try
		{
			menuItem[] list = {new menuItem("entergame",".png",true),
							 new menuItem("instruction",".png",false),
							 new menuItem("team",".png",false),
							 new menuItem("exit",".png",false)};
			return list;
		}
		catch(IOException e){
			
		}
		return null;
	}


}