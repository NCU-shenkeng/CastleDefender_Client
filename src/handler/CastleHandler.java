package handler;

import java.util.ArrayList;

import cfg.Self;
import dom.DOM;
import udp.Packet;

public class CastleHandler {
	public static class buffArray {
		public int typeID;
		public int time;
		public int fulltime;
	}
	public static void castleBuff(Packet packet) {
		ArrayList<buffArray> temp = new ArrayList<>();
		for (int i = 1; i < packet.getArgs().size(); i+=3) {
			try {
				buffArray newBuff = new buffArray();
				newBuff.typeID = Integer.valueOf(packet.getArgs().get(i));
				newBuff.time = Integer.valueOf(packet.getArgs().get(i+1));
				newBuff.fulltime = Integer.valueOf(packet.getArgs().get(i+2));
				
				temp.add(newBuff);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		DOM.UpdateCasltleBuffList(Integer.valueOf(packet.getArgs().get(0)), temp);
	}
	
	public static void castleBlood(Packet packet) {
		DOM.UpdateCastleBlood(Integer.valueOf(packet.getArgs().get(0)), packet.getArgs().get(1));
	}

	public static void castleHPChange(Packet packet){
		Self.hpchange = true;
	}

}
