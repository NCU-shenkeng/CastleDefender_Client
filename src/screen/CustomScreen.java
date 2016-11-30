package screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.swing.JPanel;

import cfg.Window;
import main.GameFrame;
import utils.ImageTool;

public class CustomScreen extends JPanel implements KeyListener{
	
	BufferedImage background;
	protected GameFrame game ;
	
	
	public CustomScreen(String backgroundPath) throws IOException
	{
		this.game = GameFrame.getGame();
		addKeyListener(this);
		background = ImageTool.getImage(backgroundPath);
		background = ImageTool.setImageSize(background, Window.WIDTH, Window.HEIGHT);
		setBounds(0,0,Window.WIDTH , Window.HEIGHT);
		initFocus();
	}
	
	protected void handleEnter() throws Throwable{}
	protected void handleKeyCode(int keyCode) throws Throwable{}
	protected void handleEsc() throws Throwable{}
	
	protected void kill(){
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	protected void refresh(){
		this.revalidate();
		this.repaint();
	}
	
	private void initFocus()
	{
		setFocusable(true);
		requestFocus();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	private void requestFoucus()
	{
		this.requestFocus();
	}
	private void setFoucusable(boolean focusable)
	{
		this.setFocusable(focusable);
	}
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
