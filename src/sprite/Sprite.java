package sprite;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.util.EnumMap;

import java.util.Map;

import animation.Animation;
import animation.CharacterAnimation;
import animation.ExplosionAnimation;
import animation.GuardAnimation;
import animation.MageAnimation;
import animation.OrdinaryPeopleAnimation;
import cfg.ActingType;
import cfg.DirectionType;
import character.CharacterType;
import utils.ImageTool;

public class Sprite {
	
	private int x = 0;
	private int y = 0;
	private boolean isAnimating = false;
	private boolean isDamage = false;
	private boolean isPicking = false;
	private int pickFullTime = 0;
	private int pickLeftTime = 0;
	private CharacterAnimation animation;
	private ExplosionAnimation explosionAnimation;
	private DirectionType facing;
	private Map<DirectionType , BufferedImage> staticImage;

	
	public Sprite(CharacterType type, int x, int y , DirectionType facing , int delay) {
		this.x = x;
		this.y = y;
		this.facing = facing;
		setAnimation(type , facing , delay);
		setStaticImage(type);
		setExplosionAnimation();
	}

	private void setAnimation(CharacterType type , DirectionType facing , int delay)
	{
		switch(type)
		{
			case Mage:
				this.animation = new MageAnimation(facing , delay);
				break;
			case Guard:
				this.animation = new GuardAnimation(facing , delay);
				break;
			case OrdinaryPeople:
				this.animation = new OrdinaryPeopleAnimation(facing , delay);
				break;
		}
	}
	private void setExplosionAnimation(){
		this.explosionAnimation = new ExplosionAnimation();
	}
	private void setStaticImage(CharacterType type)
	{
		staticImage = new EnumMap<DirectionType , BufferedImage >(DirectionType.class);
		switch(type)
		{
			case Mage:
				this.staticImage = loadStaticImage(new File("images/character/mage/static/"));
				break;
			case Guard:
				this.staticImage = loadStaticImage(new File("images/character/guard/static/"));
				break;
			case OrdinaryPeople:
				this.staticImage = loadStaticImage(new File("images/character/ordinarypeople/static/"));
				break;
		}
	}
	private Map<DirectionType , BufferedImage> loadStaticImage(File f)
	{
		if(f== null) return null;
		Map<DirectionType , BufferedImage> res =new EnumMap<DirectionType, BufferedImage>(DirectionType.class);
		for(File image : f.listFiles())
    	{
    		switch(DirectionType.valueOf(ImageTool.stripExtension(image.getName())))
    		{
    			case north:
    				res.put(DirectionType.north, ImageTool.toBufferedImage(image));
    				break;
    			case south:
    				res.put(DirectionType.south, ImageTool.toBufferedImage(image));
    				break;
    			case east:
    				res.put(DirectionType.east, ImageTool.toBufferedImage(image));
    				break;
    			case west:
    				res.put(DirectionType.west, ImageTool.toBufferedImage(image));
    				break;
    			case north_east:
    				res.put(DirectionType.north_east, ImageTool.toBufferedImage(image));
    				break;
    			case north_west:
    				res.put(DirectionType.north_west, ImageTool.toBufferedImage(image));
    				break;
    			case south_east:
    				res.put(DirectionType.south_east, ImageTool.toBufferedImage(image));
    				break;
    			case south_west:
    				res.put(DirectionType.south_west, ImageTool.toBufferedImage(image));
    				break;
    		}
    	}
		return res;
		
	}
	

	    public void moveNorth() {this.facing = DirectionType.north; animation.setFrame(DirectionType.north);}
	    public void moveSouth() {this.facing = DirectionType.south;animation.setFrame(DirectionType.south);}
	    public void moveEast(){this.facing = DirectionType.east;animation.setFrame(DirectionType.east);}
	    public void moveWest(){this.facing = DirectionType.west;animation.setFrame(DirectionType.west);}
	    public void moveNorthEast(){this.facing = DirectionType.north_east;animation.setFrame(DirectionType.north_east);}
	    public void moveNorthWest(){this.facing = DirectionType.north_west;animation.setFrame(DirectionType.north_west);}
	    public void moveSouthEast(){this.facing = DirectionType.south_east;animation.setFrame(DirectionType.south_east);}
	    public void moveSouthWest(){this.facing = DirectionType.south_west;animation.setFrame(DirectionType.south_west);}
	    public void attack(){animation.setFrame(ActingType.attack);};
	    public void damage(){}
	    public void pick(){};
	
	
	public Animation getAnimation(){
		return this.animation;
	}
	public ExplosionAnimation getExplosionAnimation(){
		return this.explosionAnimation;
	}
	public void setAnimating(boolean isAnimating){
		this.isAnimating = isAnimating;
	}
	public boolean getIsAnimating(){
		return this.isAnimating;
		
	}
	public DirectionType getFacing(){
		return this.facing;
	}
	public void setFacing(DirectionType type){
		this.facing = type;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public void setXY(int x , int y){
		this.x = x;
		this.y = y;
	}
	public boolean getIsDamage(){
		return this.isDamage;
	}
	public void setIsDamage(boolean isDamage){
		this.isDamage = isDamage;
	}
	
	public int getPickFullTime() {
		return pickFullTime;
	}
	
	public int getPickLeftTime() {
		return pickLeftTime;
	}
	
	public boolean getIsPicking() {
		return isPicking;
	}
	
	public void setPickFullTime(int pickFullTime) {
		this.pickFullTime = pickFullTime;
	}
	public void setPicking(boolean isPicking) {
		this.isPicking = isPicking;
	}
	public void setPickLeftTime(int pickLeftTime) {
		this.pickLeftTime = pickLeftTime;
	}
	
	public BufferedImage getStaticImage(){
		switch(facing)
		{
			case north:
				return staticImage.get(DirectionType.north);
			case south:
				return staticImage.get(DirectionType.south);
			case east:
				return staticImage.get(DirectionType.east);
			case west:
				return staticImage.get(DirectionType.west);
			case north_west:
				return staticImage.get(DirectionType.north_west);
			case north_east:
				return staticImage.get(DirectionType.north_east);
			case south_west:
				return staticImage.get(DirectionType.south_west);
			case south_east:
				return staticImage.get(DirectionType.south_east);
		}
		return null;
	}
	
}
