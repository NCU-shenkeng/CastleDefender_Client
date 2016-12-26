package screen;

import java.awt.event.KeyEvent;
import java.io.IOException;

import cfg.Background;
import cfg.Characters;
import cfg.Self;
import main.GameFrame;
import tcp.TCPClient;
import utils.MessageBuilder;

public class GuardScreen extends CustomScreen{

	private int character;
	public GuardScreen() throws IOException 
	{
		super(Background.SCREEN_GUARD);
		character = Characters.GUARD;
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
		TCPClient.getInstance().send(MessageBuilder.characterType(0));
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
