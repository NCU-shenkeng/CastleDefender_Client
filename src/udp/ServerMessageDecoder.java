package udp;

import java.util.Vector;

public class ServerMessageDecoder
{

	public Vector<Packet> decode(String msg)
	{
		Vector<Packet> packets = new Vector<>();
		String[] packetTable;
		String[] packet;
		int event;
		packetTable = msg.split("\n");

		for (String msgString : packetTable)
		{
			Packet temp = new Packet();
			packet = msgString.split(",");
			try
			{
				event = Integer.parseInt(packet[0]);
			} catch (Exception e)
			{
				event = 9999;
			}
			
			temp.setEvent(event);
			for (int i = 1; i < packet.length; i++)
			{
				temp.getArgs().add(packet[i]);
			}
			packets.add(temp);
		}
		return packets;
	}

}
