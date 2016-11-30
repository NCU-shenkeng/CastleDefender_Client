package screen;

import java.awt.event.KeyEvent;
import java.io.IOException;

import cfg.Background;
import cfg.Characters;
import main.GameFrame;

public class OrdinaryPeopleScreen extends CustomScreen{
	
	int character;
	public OrdinaryPeopleScreen() throws IOException {
		super(Background.SCREEN_ORDINARYPEOPLE);
		character = Characters.ORDINARYPEOPLE;
	}
	
	@Override
	protected void handleEsc()
	{
		game.returnBackScreen();
		kill();
	}
	@Override
	protected void handleEnter() throws IOException
	{
		game.changeScreen(new WaitingConnectionScreen());
		kill();
	}
	@Override
	protected void handleKeyCode(int keyCode) throws IOException
	{
		switch(keyCode)
		{
			case KeyEvent.VK_ESCAPE:
				handleEsc();
				break;
			case KeyEvent.VK_ENTER:
				handleEnter();
				break;
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		try 
		{
			handleKeyCode(arg0.getKeyCode());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	

}
