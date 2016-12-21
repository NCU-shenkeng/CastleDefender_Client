package animation;


import cfg.DirectionType;

public class MageAnimation extends CharacterAnimation{

	public MageAnimation(DirectionType direction, int delay) {
		super(direction, "images/character/mage", delay);
	}
	
	protected void move(){}

}
