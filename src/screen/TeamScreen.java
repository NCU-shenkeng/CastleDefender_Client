package screen;

import java.awt.event.KeyEvent;
import java.io.IOException;

import cfg.Background;
import main.GameFrame;

public class TeamScreen extends CustomScreen{

	public TeamScreen() throws IOException {
		super(Background.SCREEN_TEAM);
	}
	
	
	@Override
	protected void handleEnter()
	{
		game.returnBackScreen();
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
			handleEnter();
		kill();
	}
}
