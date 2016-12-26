package animation;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import cfg.DirectionType;
import dom.DOM;
import player.Player;
import utils.ImageTool;

public class CharacterAnimation extends Animation{
	
	private Map<DirectionType , List<Frame> > allMovingFrames= new EnumMap<DirectionType , List<Frame> >(DirectionType.class);
	private Map<DirectionType , List<Frame> > allAttackingFrames= new EnumMap<DirectionType , List<Frame> >(DirectionType.class);
	
	public CharacterAnimation(DirectionType direction , String path , int delay){
		super();
		File move = new File(path +"/move");
		File act = new File(path + "/act/attack");
    	loadMovingFrames(move);
    	loadAttackingFrames(act);
    	setMovingFrame(direction);
    	setDelay(delay);
    	init();
	}

    private void loadMovingFrames(File f)
    {
    	for(File directory : f.listFiles())
    	{
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
    private void loadAttackingFrames(File f)
    {
    	if(!f.isDirectory()) return;
    	for(File directory : f.listFiles())
    	{
    		switch(DirectionType.valueOf(directory.getName()))
    		{
    			case north:
    				allAttackingFrames.put(DirectionType.north, getFrames(directory));
    				break;
    			case west:
    				allAttackingFrames.put(DirectionType.west, getFrames(directory));
    				break;
    			case south:
    				allAttackingFrames.put(DirectionType.south , getFrames(directory));
    				break;
    			case east:
    				allAttackingFrames.put(DirectionType.east, getFrames(directory));
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
    
    
    public void setMovingFrame(DirectionType direction)
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
    public void setAttackFrame()
    {
    	Player self = DOM.getSelf();
    	switch(self.getSprite().getFacing())
    	{
    		case east:
    			setFrames(allAttackingFrames.get(DirectionType.east));
    			break;
    		case west:
    			setFrames(allAttackingFrames.get(DirectionType.west));
    			break;
    		case north:
    			setFrames(allAttackingFrames.get(DirectionType.north));
    			break;
    		case south:
    			setFrames(allAttackingFrames.get(DirectionType.south));
    			break;
    	}
    }
    
}
