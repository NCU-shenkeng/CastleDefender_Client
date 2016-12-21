package screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import cfg.Background;
import cfg.Selection;
import main.GameFrame;

public class MenuScreen extends CustomScreen implements KeyListener
{
	private JLabel[] labelList = new JLabel[Selection.list.length];
	private int pointer = 0;
	
	public MenuScreen() throws IOException 
	{
		super(Background.SCREEN_SELECTION);
		setLayout(null);
		generateSelection();
	}
	
	private void generateSelection()
	{
		for(int i = 0 ; i < Selection.list.length ; i++)
		{
			JLabel l = new JLabel(new ImageIcon(Selection.list[i].icon));
			int w = 450;
			int h = 200 + i*100;
			l.setBounds(w, h , Selection.list[i].icon.getWidth(), Selection.list[i].icon.getHeight());
			labelList[i] = l;
		}
		
		for(JLabel l : labelList)
			add(l);
		game.refresh();
		
	}
	
	private void removeSelection()
	{
		for(JLabel l : labelList)
		{
			this.remove(l);
			l = null;
		}
	}
	
	@Override
	protected void handleEnter() throws Throwable
	{
		String name = Selection.list[pointer].name;
		switch(name)
		{
			case "entergame":
				game.changeScreen(new CharacterSelectionScreen());
				break;
			case "instruction":
				game.changeScreen(new InstructionsScreen());
				break;
			case "team":
				game.changeScreen(new TeamScreen());
				break;
			case "exit":
				game.exit();
				break;
		}
		game.addBackScreen(this);
	}
	
	@Override
	protected void handleKeyCode(int keyCode) throws Throwable
	{
		if(keyCode == KeyEvent.VK_ENTER)
		{
			handleEnter();
			return;
		}
		Selection.list[pointer].setSelected(false);
		switch(keyCode)
		{
			case KeyEvent.VK_UP:
				pointer--;
				break;
			case KeyEvent.VK_DOWN:
				pointer++;
				break;
				
		}
		if(pointer >= Selection.list.length)
			pointer = 0;
		if(pointer < 0)
			pointer = Selection.list.length-1;
		Selection.list[pointer].setSelected(true);
		
		removeSelection();
		generateSelection();
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		try 
		{
			handleKeyCode(arg0.getKeyCode());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
