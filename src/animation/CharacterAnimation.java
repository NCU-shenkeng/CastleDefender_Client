package animation;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import cfg.ActingType;
import cfg.DirectionType;
import utils.ImageTool;

public class CharacterAnimation extends Animation{
	
	private Map<DirectionType , List<Frame> > allMovingFrames= new EnumMap<DirectionType , List<Frame> >(DirectionType.class);
	private Map<ActingType , List<Frame> > allActingFrames= new EnumMap<ActingType , List<Frame> >(ActingType.class);
	
	public CharacterAnimation(DirectionType direction , String path , int delay){
		super();
		File move = new File(path +"/move");
		File act = new File(path + "/act");
    	loadMovingFrames(move);
    	loadActingFrames(act);
    	setFrame(direction);
    	setDelay(delay);
    	init();
	}

    private void loadMovingFrames(File f)
    {
    	for(File directory : f.listFiles())
    	{
    		System.out.println("load" + directory.getName());
    		switch(DirectionType.valueOf(directory.getName()))
    		{
    			case north:
    				allMovingFrames.put(DirectionType.north, getFrames(directory));
    				break;
    			case south:
    				allMovingFrames.put(DirectionType.south, getFrames(directory));
    				break;
    			case east:
    				allMovingFrames.put(DirectionType.east, getFrames(directory));
    				break;
    			case west:
    				allMovingFrames.put(DirectionType.west, getFrames(directory));
    				break;
    			case north_east:
    				allMovingFrames.put(DirectionType.north_east, getFrames(directory));
    				break;
    			case north_west:
    				allMovingFrames.put(DirectionType.north_west, getFrames(directory));
    				break;
    			case south_east:
    				allMovingFrames.put(DirectionType.south_east, getFrames(directory));
    				break;
    			case south_west:
    				allMovingFrames.put(DirectionType.south_west, getFrames(directory));
    				break;
    		}
    	}
    }
    private void loadActingFrames(File f)
    {
    	if(!f.isDirectory()) return;
    	for(File directory : f.listFiles())
    	{
    		switch(ActingType.valueOf(directory.getName()))
    		{
    			case attack:
    				allActingFrames.put(ActingType.attack, getFrames(directory));
    				break;
    			case damage:
    				allActingFrames.put(ActingType.damage, getFrames(directory));
    				break;
    			case pick:
    				allActingFrames.put(ActingType.pick , getFrames(directory));
    				break;
    			case dead:
    				allActingFrames.put(ActingType.dead, getFrames(directory));
    				break;
			default:
				break;
    		}
    	}
    }   
    private List<Frame> getFrames(File directory)
    {
    	List<Frame> res = new ArrayList<Frame>();
    	if(!directory.isDirectory()) return null;
    	for(File image : directory.listFiles())
    	{
    		try 
    		{
				BufferedImage buffer = ImageTool.toBufferedImage(ImageIO.read(image));
				Frame frame = new Frame(buffer , 1);
				res.add(frame);
			} 
    		catch (IOException e)
    		{
			}
    	}
    	return res;
    }
    
    
    public void setFrame(DirectionType direction)
    {

    	switch(direction)
    	{
	    	case north:
				setFrames(allMovingFrames.get(DirectionType.north));
				break;
			case south:
				setFrames(allMovingFrames.get(DirectionType.south));
				break;
			case east:
				setFrames(allMovingFrames.get(DirectionType.east));
				break;
			case west:
				setFrames(allMovingFrames.get(DirectionType.west));
				break;
			case north_east:
				setFrames(allMovingFrames.get(DirectionType.north_east));
				break;
			case north_west:
				setFrames(allMovingFrames.get(DirectionType.north_west));
				break;
			case south_east:
				setFrames(allMovingFrames.get(DirectionType.south_east));
				break;
			case south_west:
				setFrames(allMovingFrames.get(DirectionType.south_west));
				break;
    	}
    }
    public void setFrame(ActingType action)
    {
    	switch(action)
    	{
	    	case attack:
				setFrames(allActingFrames.get(ActingType.attack));
				break;
			case pick:
				setFrames(allMovingFrames.get(ActingType.pick));
				break;
			case dead:
				setFrames(allMovingFrames.get(ActingType.dead));
				break;
    	}
    }
    
}
