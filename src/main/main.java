package main;

import java.io.IOException;

import javax.swing.JFrame;

import screen.EnterScreen;


public class main {
	
	public static void main(String[] args) throws IOException
	{
		JFrame game = GameFrame.getGame();
		game.add(new EnterScreen()); // initial screen
	    game.setVisible(true);
	}
	

}
