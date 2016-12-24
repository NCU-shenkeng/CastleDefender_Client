package screen;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import cfg.Background;
import cfg.Selection;
import cfg.Window;
import main.GameFrame;

public class InstructionsScreen extends CustomScreen{
	
	private ArrayList<CustomScreen> instructionScreenList = new ArrayList<CustomScreen>();
	int pointer = 0 ;
	
	
	public InstructionsScreen()  throws IOException {
		super(Background.SCREEN_INSTRUCTIONS[0]);
		
		for(String icon : Background.SCREEN_INSTRUCTIONS)
		{
			CustomScreen cs = new CustomScreen(icon);
			cs.setBounds(0,0, Window.WIDTH , Window.HEIGHT);
			instructionScreenList.add(cs);
		}
			
		
		for(CustomScreen cs : instructionScreenList)
		{
			cs.addKeyListener(this);
		}
	}
	
	@Override
	protected void handleEnter()
	{
		game.returnBackScreen();
		kill();
	}
	
	@Override
	protected void handleKeyCode(int keyCode) throws Throwable
	{
		if(keyCode == KeyEvent.VK_ENTER)
		{
			handleEnter();
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
		if(pointer >= instructionScreenList.size())
			pointer = 0;
		if(pointer < 0)
			pointer = Selection.list.length-1;

		removeAll();
		add(instructionScreenList.get(pointer));
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
