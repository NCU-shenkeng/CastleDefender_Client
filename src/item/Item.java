package item;

import java.awt.Point;

public class Item
{
	private int type;
	private int x;
	private int y;
	
	public Item (int type , int x , int y){
		this.type = type;
		this.x = x;
		this.y = y;
	}
	public Item(int x , int y){
		this.x = x;
		this.y =y;
	}
	public Item(){}
	
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
	
	@Override
	public boolean equals(Object object){
		if(object == null) return false;
		final Item o = (Item) object;
		if(this.x == o.x && this.y == o.y){return true;}
		return false;
	}
}
