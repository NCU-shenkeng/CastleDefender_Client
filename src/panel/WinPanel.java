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

public class WinPanel extends JPanel implements KeyListener{
	
	long lastTime = 0;
	
	public WinPanel()
	{
		this.setVisible(true);
		this.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		
		lastTime = System.currentTimeMillis();
	}
	
	public void paintComponent(Graphics g)
	{ 	
		Image bgImage = Toolkit.getDefaultToolkit().getImage("images/winner.gif");

		super.paintComponent(g); 		 			
		g.drawImage(bgImage, 120, 50, this); 
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		long nowTime = System.currentTimeMillis();
		try {
			if(nowTime - lastTime > 5000)
				GameFrame.getGame().changeScreen(new EnterScreen());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
