package screen;

import java.io.IOException;

import cfg.Background;
import main.GameFrame;

public class WaitingConnectionScreen extends CustomScreen{

	public WaitingConnectionScreen() throws IOException {
		super(Background.SCREEN_WAITING_CONNECTION);
	}
}
