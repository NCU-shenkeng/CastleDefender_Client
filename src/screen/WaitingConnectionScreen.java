package screen;

import java.io.IOException;

import cfg.Background;
import main.GameFrame;
import receiver.UDPMessageReceiver;
import udp.Server;

public class WaitingConnectionScreen extends CustomScreen{

	public WaitingConnectionScreen() throws IOException {
		super(Background.SCREEN_WAITING_CONNECTION);
		
		Server.getUDPUS().initUDPServer();
		Server.getUDPUS().setReceiveAction(new UDPMessageReceiver());
	}
}
