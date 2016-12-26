package screen;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import cfg.Background;
import cfg.Characters;
import cfg.Selection;
import cfg.Window;
import main.GameFrame;
import receiver.TCPMessageReceiver;
import tcp.TCPClient;

public class CharacterSelectionScreen extends CustomScreen{

	private ArrayList<CustomScreen> CharacterScreenList = new ArrayList<CustomScreen>();
	private int pointer = 0;
	
	public CharacterSelectionScreen() throws IOException {
		super(Background.SCREEN_CHARACTERSELECTION[0]);
		
		for(String s : Background.SCREEN_CHARACTERSELECTION)
		{
			CustomScreen cs = new CustomScreen(s);
			cs.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
			CharacterScreenList.add(cs);
		}
			
		
		for(CustomScreen cs : CharacterScreenList)
		{
			cs.addKeyListener(this);
		}
		
		game.addBackScreen(this);
		
		TCPClient.getInstance().initTCPClient();
		TCPClient.getInstance().registReceiveAction(new TCPMessageReceiver());
		
	}
	
	@Override
	protected void handleEnter() throws IOException
	{
		switch(pointer)
		{
			case Characters.ORDINARYPEOPLE:
				game.changeScreen(new OrdinaryPeopleScreen());
				break;
			case Characters.MAGE:
				game.changeScreen(new MageScreen());
				break;
			case Characters.GUARD:
				game.changeScreen(new GuardScreen());
		}
		game.addBackScreen(this);
		kill();
	}
	@Override
	protected void handleEsc()
	{
		game.returnBackScreen();
		kill();
	}
	@Override
	protected void handleKeyCode(int keyCode) throws IOException
	{
		if(keyCode == KeyEvent.VK_ENTER)
		{
			handleEnter();
			return;
		}
		if(keyCode == KeyEvent.VK_ESCAPE)
		{
			handleEsc();
			return;
		}
		switch(keyCode)
		{
			case KeyEvent.VK_RIGHT:
				pointer++;
				break;
			case KeyEvent.VK_LEFT:
				pointer--;
				break;
		}
		if(pointer >= CharacterScreenList.size())
			pointer = 0;
		if(pointer < 0)
			pointer = CharacterScreenList.size()-1;
		
		removeAll();
		add(CharacterScreenList.get(pointer));
		game.repaint();
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
