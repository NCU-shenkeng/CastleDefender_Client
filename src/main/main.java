package main;


import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import character.CharacterType;
import dom.DOM;
import dom.PlayerTable;
import panel.GamePanel;
import player.Player;
import render.GameEngine;
import screen.EnterScreen;

public class main {
	
	public static void main(String[] args) throws IOException
	{
	   
		JFrame game = GameFrame.getGame();
		
		DOM.addPlayer(new Player(0, CharacterType.Guard, 0, 13, 49));
		DOM.addPlayer(new Player(1, CharacterType.Guard, 1, 14, 49));
		DOM.addPlayer(new Player(2, CharacterType.Guard, 1, 16, 49));
		DOM.addPlayer(new Player(3, CharacterType.Guard, 1,50, 49));
		
		game.add(GamePanel.getGame()); // initial screen
		game.setVisible(true);
		Thread render = new Thread(new GameEngine());
		render.start();
	}
	

}
