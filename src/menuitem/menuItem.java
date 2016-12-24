package menuitem;

import java.awt.image.BufferedImage;
import java.io.IOException;

import utils.ImageTool;

public class menuItem {
	
	public String name;
	public BufferedImage icon;
	public String imageType;
	boolean isSelected;
	
	public menuItem(String name , String imageType ,boolean isSelected) throws IOException
	{
		this.name = name;
		this.isSelected = isSelected;
		this.imageType = imageType;
		handleSelect();
	}
	
	void handleSelect() throws IOException
	{
		String path = "images/menuitem/";
		if(this.isSelected)
			this.icon = ImageTool.getImage(path + this.name + "_selected" + this.imageType);	
		else
			this.icon = ImageTool.getImage(path + this.name  + this.imageType);
	}
	
	public void setSelected (boolean selected) throws IOException
	{
		this.isSelected = selected;
		handleSelect();
	}
	
	
}