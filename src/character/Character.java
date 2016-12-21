package character;


public class Character {
	
	CharacterType type;
	int maxHP;
	int currentHP;
	double speed;
	int pickTime;
	int attackPower;
	boolean isDead;
	int reviveTime;
	
	public Character(CharacterType type ,int maxHP , double speed ,int pickTime ,int attackPower){
		this.type = type;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.speed = speed;
		this.pickTime = pickTime;
		this.attackPower = attackPower;
	}
	
	public int getMaxHP() {
		return maxHP;
	}
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	public int getCurrentHP() {
		return currentHP;
	}
	public void setCurrentHP(int currentHP) {
		this.currentHP += currentHP;
		if(this.currentHP <=0)this.currentHP =0;
		if(this.currentHP >= maxHP) this.currentHP = maxHP;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public int getPickTime() {
		return pickTime;
	}
	public void setPickTime(int pickTime) {
		this.pickTime = pickTime;
	}
	
	public int getAttackPower(){
		return this.attackPower;
	}
	public void setAttackPower(int attackPower){
		this.attackPower = attackPower;
	}
	public CharacterType getType(){
		return this.type;
	}
	
	
}
