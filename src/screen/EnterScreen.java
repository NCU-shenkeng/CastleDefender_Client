package screen;

import java.awt.event.KeyEvent;
import java.io.IOException;

import cfg.Background;
import main.GameFrame;

public class EnterScreen extends CustomScreen{
	
	public EnterScreen() throws IOException {
		super(Background.SCREEN_ENTER);
		game.addBackScreen(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0 != null)
			try 
			{
				game.changeScreen(new MenuScreen());
				kill();
			} 
		    catch (IOException e) 
		    {
				e.printStackTrace();
			} catch (Throwable e) 
		    {
				e.printStackTrace();
			}
	}

}
