package panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;

import cfg.Window;
import main.GameFrame;
import screen.EnterScreen;

public class LosePanel extends JPanel implements KeyListener{

	
	public LosePanel()
	{
		this.setVisible(true);
		this.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
	}
	
	public void paintComponent(Graphics g)
	{ 	
		Image bgImage = Toolkit.getDefaultToolkit().getImage("images/loser.gif");

		super.paintComponent(g); 		 			
		g.drawImage(bgImage, 120, 50, this); 
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			GameFrame.getGame().changeScreen(new EnterScreen());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
