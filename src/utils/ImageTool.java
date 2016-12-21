package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;


public class ImageTool {
	
	
	public static BufferedImage getImage(String path) throws IOException
	{
		return ImageIO.read(new File(path));
	}
	
	public static BufferedImage toBufferedImage(File f)
	{
		try {
			return ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage setImageSize(BufferedImage image , int newW , int newH)
	{
		newH-=40; // due to the function bar 
		Image tmp = image.getScaledInstance(newW, newH, 4);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return toBufferedImage(dimg);
	}
	
	public static Image setImageSize(Image image , int newW , int newH){
		
		return image.getScaledInstance(newW, newW, Image.SCALE_DEFAULT);
	    
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	private static int getFileCount(String path , FilenameFilter filter){
		int res = 0;
		File dir = new File(path);
		if(!dir.isDirectory()) return -1;
		for(File f : dir.listFiles(filter)){
			res++;
		}
		return res;
	}
	
	public static BufferedImage[] toBufferedImageArray(String path){
		File dir = new File(path);
		ImageFilter filter = new ImageFilter();
		BufferedImage[] res = new BufferedImage[getFileCount(path , filter)];
		if(dir.isDirectory())
		{
			int index = 0;
			for(File f : dir.listFiles(filter))
			{
				try {
					res[index] = toBufferedImage(ImageIO.read(f));
					index++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	
	public static ArrayList<BufferedImage> toBufferedImageList(String path){
		ArrayList<BufferedImage> res = new ArrayList<BufferedImage>();
		File directory = new File(path);
    	if(!directory.isDirectory()) return null;
    	for(File image : directory.listFiles())
    	{
    		try 
    		{
				BufferedImage buffer = ImageTool.toBufferedImage(ImageIO.read(image));
				res.add(buffer);
			} 
    		catch (IOException e)
    		{
			}
    	}
    	return res;
	}
	public static String stripExtension (String str) {
        // Handle null case specially.

        if (str == null) return null;

        // Get position of last '.'.

        int pos = str.lastIndexOf(".");

        // If there wasn't any '.' just return the string as is.

        if (pos == -1) return str;

        // Otherwise return the string, up to the dot.

        return str.substring(0, pos);
    }
	public static class ImageFilter implements FilenameFilter{
		@Override
		public boolean accept(File dir, String name) {
			if(name.endsWith(".png"))
				return true;
			return false;
		}
		
	}
	
	
}
