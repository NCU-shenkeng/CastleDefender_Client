package animation;


import cfg.DirectionType;

public class GuardAnimation extends CharacterAnimation{

	public GuardAnimation(DirectionType direction , int delay) {
		super(direction, "images/character/guard/", delay);
	}
	
}
