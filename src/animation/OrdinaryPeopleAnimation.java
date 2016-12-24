package animation;


import cfg.DirectionType;

public class OrdinaryPeopleAnimation extends CharacterAnimation{

	public OrdinaryPeopleAnimation(DirectionType direction , int delay) {
		super(direction, "images/character/ordinarypeople", delay);
	}
}
