
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
		GameFrame.getGame().changeScreen(new EnterScreen());// initial screen
		GameFrame.getGame().setVisible(true);
	}
}
