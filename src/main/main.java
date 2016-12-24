package main;


import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import character.CharacterType;
import dom.DOM;
import dom.PlayerTable;
import item.Item;
import panel.GamePanel;
import panel.LosePanel;
import panel.WinPanel;
import player.Player;
import render.GameEngine;
import screen.EnterScreen;

public class main {
	
	public static void main(String[] args) throws IOException
	{
		
		DOM.addPlayer(new Player(0, CharacterType.Guard, 0, 13, 49));
		DOM.addPlayer(new Player(1, CharacterType.Guard, 1, 14, 49));
		DOM.addPlayer(new Player(2, CharacterType.Guard, 1, 16, 49));
		DOM.addPlayer(new Player(3, CharacterType.Guard, 1,50, 49));
		
		
		GameFrame.getGame().changeScreen(GamePanel.getGame());// initial screen
		GameFrame.getGame().setVisible(true);
		Thread render = new Thread(new GameEngine());
		render.start();
		
		
	}
	

}
