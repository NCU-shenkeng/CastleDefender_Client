package background;

public class BasicBlock 
{	
	public int type;
	public boolean	canPass;
	
	public BasicBlock(int type, boolean canPass)
	{
		this.type = type;
		this.canPass = canPass;
	}
}

