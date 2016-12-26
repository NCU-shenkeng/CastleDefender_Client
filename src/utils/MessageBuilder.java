package utils;

import cfg.Self;
import dom.DOM;

public class MessageBuilder {

	public static String location(){
		 String res = String.format("3,%d,%d,%d,%d,%d", 
				   Self.number , 
				   DOM.getSelf().getSprite().getX() ,
				   DOM.getSelf().getSprite().getY() ,
				   utils.Parser.parseDirectionToInt(DOM.getSelf().getSprite().getFacing()),
				   utils.Parser.parseBoolean(DOM.getSelf().getSprite().getIsAnimating()));
		 return res;
	}
	public static String characterType(int type){
		 String res = String.format("1,%d,%d",
				 					Self.number,
				 					type);
		 return res;
	}
}
