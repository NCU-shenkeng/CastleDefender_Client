package main;


import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cfg.Window;


public class GameFrame extends JFrame{
	
	private static GameFrame gameFrame = null;
	private Stack<JPanel> backList = new Stack<JPanel>();
	
	
	private GameFrame() throws IOException{
		setTitle("CastleDenfender");
		setSize(Window.WIDTH , Window.HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setLocationRelativeTo(null);
		//getContentPane().add(new EnterScreen());
	}
	
	//Singleton pattern
	public static GameFrame getGame() throws IOException{
		if(gameFrame == null)
		{
			synchronized(GameFrame.class)
			{
				if(gameFrame == null){
					gameFrame = new GameFrame();
				}
			}
		}
		return gameFrame;
	}
	
	public void addBackScreen(JPanel back)
	{
		backList.push(back);
	}
	public void returnBackScreen()
	{
		if(!backList.isEmpty())
			this.changeScreen(backList.pop());
		else
			System.out.println("back is null");
	}
	public void exit()
	{
		this.dispatchEvent(new WindowEvent(this , WindowEvent.WINDOW_CLOSING));
	}
	
	public void changeScreen(JPanel cs)
	{
		this.getContentPane().removeAll();
		this.getContentPane().add(cs);
		refresh();
		cs.requestFocus();
	}
	
	
	public void refresh()
	{
		this.repaint();
	}
}
