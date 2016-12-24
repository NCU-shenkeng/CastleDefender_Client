package castle;

public class Castle {

	String Enemy_Castle_Blood="300";
	String Self_Castle_Blood="300";
	int[] SelfCastleBuff = new int[11];
	int[] SelfCastleBuffCDR = new int[11];
	int [] EnemyCastleBuff = new int[11];
	
	public Castle()
	{
		
	}
	public void setEnemyCastleBlood(String blood)
	{
		Enemy_Castle_Blood = blood;
	}
	public String getEnemyCastleBlood()
	{
		return Enemy_Castle_Blood;
	}
	public void setSelfCastleBlood(String blood)
	{
		Self_Castle_Blood = blood;
	}
	public String getSelfCastleBlood()
	{
		return Self_Castle_Blood;
	}
	public void setCastleBuff(int[] buff)
	{
		for(int i = 0; i <= 10 ; i++)
		{
			SelfCastleBuff[i] = buff[i];
		}
	}
	public int[] getEnemyCastleBuff()
	{
		return EnemyCastleBuff;
	}
	public void setEnemyCastleBuff(int[] buff)
	{
		for(int i = 0; i <= 10 ; i++)
		{
			SelfCastleBuff[i] = buff[i];
		}
	}
	public int[] getCastleBuff()
	{
		return SelfCastleBuff;
	}
	public void setCastleBuffCDR(int[] cd)
	{
		for(int i = 0;i <=10; i++)
		{
			SelfCastleBuffCDR[i] = cd[i];
		}
	}
	public int[] getCastleBuffCDR()
	{
		return SelfCastleBuffCDR;
	}
	
}


