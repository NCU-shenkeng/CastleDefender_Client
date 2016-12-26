package receiver;

import java.net.Socket;
import java.util.Vector;

import handler.PlayerHandler;
import tcp.IReceive;
import udp.Packet;

public class TCPMessageReceiver implements IReceive{

	TCPMessageDistributor distributor;
	
	public TCPMessageReceiver() {
		distributor = new TCPMessageDistributor();
	}
	
	@Override
	public void onReceive(String msg) {
			//System.out.println(msg);
			distributor.distributeMessage(msg);
	}

	@Override
	public void afterReceive(Socket client) {
	}

}
