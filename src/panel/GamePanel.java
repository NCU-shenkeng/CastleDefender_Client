
package panel;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;

import javax.swing.JPanel;

import cfg.Keyboard;
import cfg.Self;
import cfg.Window;
import dom.DOM;

import graphics.BackgroundGraphics;
import receiver.TCPMessageReceiver;
import receiver.UDPMessageReceiver;
import tcp.TCPClient;
import udp.Server;

public class GamePanel extends JPanel implements KeyListener {
	
	
	private static GamePanel game = null;
	
	private GamePanel(){
		this.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
		this.setFocusable(true);
		this.requestFocus();
		this.setVisible(true);
		this.addKeyListener(this);
	}
	
	public static GamePanel getGame(){
		if(game == null)
		{
			synchronized(GamePanel.class){
				System.out.println(123);
				game = new GamePanel();
			}
		}
		return game;
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		switch (arg0.getKeyCode())
		{
			case KeyEvent.VK_UP:
				Keyboard.setUp(true);
				DOM.getSelf().getSprite().setAnimating(true);
				break;
			case KeyEvent.VK_LEFT:
				Keyboard.setLeft(true);
				DOM.getSelf().getSprite().setAnimating(true);
				break;
			case KeyEvent.VK_DOWN:
				Keyboard.setDown(true);
				DOM.getSelf().getSprite().setAnimating(true);
				break;
			case KeyEvent.VK_RIGHT:
				Keyboard.setRight(true);
				DOM.getSelf().getSprite().setAnimating(true);
				break;
			case KeyEvent.VK_A:
				Keyboard.setA(true);
				TCPClient.getInstance().send("5,"+Integer.toString(Self.number)); // pick item
				break;
			case KeyEvent.VK_SPACE:
				TCPClient.getInstance().send("4,"+Integer.toString(Self.number)); // attack
				Keyboard.setSpace(true);
				DOM.getSelf().getSprite().setAnimating(true);
				break;
			default:
				break;
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		switch (arg0.getKeyCode())
		{
			case KeyEvent.VK_UP:
				Keyboard.setUp(false);
				break;
			case KeyEvent.VK_LEFT:
				Keyboard.setLeft(false);
				break;
			case KeyEvent.VK_DOWN:
				Keyboard.setDown(false);
				break;
			case KeyEvent.VK_RIGHT:
				Keyboard.setRight(false);
				break;
			case KeyEvent.VK_SPACE:
				Keyboard.setSpace(false);
				break;
			case KeyEvent.VK_A:
				Keyboard.setA(false);
				break;
			default:
				break;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) 
	{
	}
	public void reset(){
		game = null;
	}
}