package dom;

import castle.Castle;

public class CastleTable {
	private static CastleTable castleTable;
	
	private static Castle castleItem = new Castle();
	
	public static CastleTable getCastleTable()
	{
		if (castleTable== null)
			castleTable = new CastleTable();
		
		return castleTable;
	}
	
	public Castle getCastle(){	
		return castleItem;
	}
	
	public void reset(){
		castleItem = null;
		castleItem = new Castle();
	}
}
