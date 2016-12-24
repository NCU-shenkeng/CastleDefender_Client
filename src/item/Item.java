package item;

import java.awt.Point;

public class Item
{
	private int type;
	private int x;
	private int y;
	private int allTime;
	private int leftTime;
	
	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}
	
	public Point getItemXY()
	{
		return new Point(this.x, this.y);
	}
	
	public void setItemXY(Point itemxy)
	{
		this.x = (int)itemxy.getX();
		this.y = (int)itemxy.getY();
	}
}
