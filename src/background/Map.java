package background;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Map 
{
	int UserBlockWidth = (11 - 1) / 2;
	int UserBlockHigh = (7 - 1) / 2;
	int BasicBlockWidth = 50;
	int BasicBlockHigh = 50;
	//public BasicBlock[][] scene = new BasicBlock[BasicBlockWidth + UserBlockWidth * 2][BasicBlockHigh + UserBlockHigh * 2];
	public BasicBlock[][] scene = new BasicBlock[60][56];
	
	public Map()
	{
		
	}
	
	public void CreateMap() throws IOException
	{	  
//		Initialize
	  
		FileReader fr = new FileReader("images/backgound/mapfile.txt");
		BufferedReader br = new BufferedReader(fr);
		String line;
		int i = 0;
		int j = 0;
		while ((line = br.readLine()) != null)
		{
			for ( i = 0; i < line.length(); i++)
			{
//				System.out.println("[" + i + "," + j + "] = " + line.charAt(i));
				
				int blocktype = Character.getNumericValue(line.charAt(i));
				
				if (blocktype == BasicBlockType.SAND1 ||
					blocktype == BasicBlockType.SAND2 ||
					blocktype == BasicBlockType.FLOOR1 ||
					blocktype == BasicBlockType.FLOOR2)
					scene[i][j] = new BasicBlock(blocktype, true);
				else
					scene[i][j] = new BasicBlock(blocktype, false);
			}
			j ++;
		}
		
		fr.close();
	}
}
