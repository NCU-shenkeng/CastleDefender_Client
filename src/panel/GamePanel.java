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
	private static UDPMessageReceiver UDPReceiver;
	private static TCPMessageReceiver TCPReceiver;
	
	private GamePanel(){
		this.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
		this.setFocusable(true);
		this.requestFocus();
		this.setVisible(true);
		this.addKeyListener(this);
		UDPReceiver = new UDPMessageReceiver();
		TCPReceiver = new TCPMessageReceiver();
		Server.initUDPServer(); // run the client
		Server.getUDPUS().setReceiveAction(UDPReceiver); // regist receiver

		TCPClient.getInstance().initTCPClient();
		TCPClient.getInstance().registReceiveAction(TCPReceiver);
	}
	
	public static GamePanel getGame(){
		if(game == null)
		{
			synchronized(GamePanel.class){
				game = new GamePanel();
			}
		}
		return game;
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		DOM.getSelf().getSprite().setAnimating(true);
		switch (arg0.getKeyCode())
		{
			case KeyEvent.VK_UP:
				Keyboard.setUp(true);
				break;
			case KeyEvent.VK_LEFT:
				Keyboard.setLeft(true);
				break;
			case KeyEvent.VK_DOWN:
				Keyboard.setDown(true);
				break;
			case KeyEvent.VK_RIGHT:
				Keyboard.setRight(true);
				break;
			case KeyEvent.VK_A:
				Keyboard.setSpace(true);
				System.out.println("4,"+Integer.toString(Self.number));
				TCPClient.getInstance().send("4,"+Integer.toString(Self.number));
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
				BackgroundGraphics.getGraph().Key[0] = 0;
				Keyboard.setUp(false);
				break;
			case KeyEvent.VK_LEFT:
				BackgroundGraphics.getGraph().Key[1] = 0;
				Keyboard.setLeft(false);
				break;
			case KeyEvent.VK_DOWN:
				BackgroundGraphics.getGraph().Key[2] = 0;
				Keyboard.setDown(false);
				break;
			case KeyEvent.VK_RIGHT:
				BackgroundGraphics.getGraph().Key[3] = 0;
				Keyboard.setRight(false);
				break;
			case KeyEvent.VK_SPACE:
				Keyboard.setSpace(false);
				break;
			case KeyEvent.VK_A:
				Keyboard.setSpace(false);
				break;
			default:
				break;
		}
		
		if(!Keyboard.isKeyBoardDown())
		{
			DOM.getSelf().getSprite().setAnimating(false);
			DOM.getSelf().getSprite().getAnimation().stop();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}
}
