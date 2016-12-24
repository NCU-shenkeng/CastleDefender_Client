package player;


import cfg.DirectionType;
import cfg.Self;
import character.Character;
import character.CharacterType;
import character.Guard;
import character.Mage;
import character.OrdinaryPeople;
import graphics.BackgroundGraphics;
import sprite.Sprite;

public class Player {
	
	int number;
	Sprite sprite;
	Character character;
	int team;
	boolean isDead;
	int reviveTime;
	
	
	public Player(int number , CharacterType type , int team , int x , int y){
		this.number = number;
		this.team = team;
		setCharacter(type);
		setSprite(type ,x , y);
	}
	
	public void setTeam(int team){
		this.team = team;
	}
	public int getTeam(){
		return this.team;
	}
	public Sprite getSprite(){
		return this.sprite;
	}
	
	private void setCharacter(CharacterType type)
	{
		switch(type)
		{
			case Mage:
				this.character = new Mage();
				break;
			case Guard:
				this.character = new Guard();
				break;
			case OrdinaryPeople:
				this.character = new OrdinaryPeople();
				break;
			
		}
	}
	private void setSprite(CharacterType type , int x ,int y ){
		this.sprite = new Sprite(type , x , y , DirectionType.south ,7) ; // default facing north and the delay is 7
	}
	
	public Character getCharacter(){
		return this.character;
	}
	public int getNumber(){
		return this.number;
	}
	
	public int getCordinateXInPanel(){
		int res = 0;
		if(this.getNumber() == Self.number) return BackgroundGraphics.getGraph().getBlockOffsetX() * 100;
		if(this.getSprite().getX() > BackgroundGraphics.getGraph().getBlockX())
			res = BackgroundGraphics.getGraph().getBlockOffsetX()+(this.getSprite().getX() - BackgroundGraphics.getGraph().getBlockX());
		else
			res = BackgroundGraphics.getGraph().getBlockOffsetX()-(BackgroundGraphics.getGraph().getBlockX()-this.getSprite().getX());
		return (res * 100);
	}
	public int getCordinateYInPanel(){
		int res = 0;
		if(this.getNumber() == Self.number)return  BackgroundGraphics.getGraph().getBlockOffsetY()*100;
		if(this.getSprite().getY() > BackgroundGraphics.getGraph().getBlockY())
			res = BackgroundGraphics.getGraph().getBlockOffsetY()+(this.getSprite().getY() - BackgroundGraphics.getGraph().getBlockY());
		else
			res = BackgroundGraphics.getGraph().getBlockOffsetY()-(BackgroundGraphics.getGraph().getBlockY()-this.getSprite().getY());
		return res * 100;
	}
	
	public int getReviveTime(){
		return this.reviveTime;
	}
	public void setReviveTime(int reviveTime){
		this.reviveTime = reviveTime;
	}
	
	public boolean getIsDead(){
		return isDead;
	}
	public void setIsDead(boolean isDead){
		this.isDead = isDead;
	}
}
