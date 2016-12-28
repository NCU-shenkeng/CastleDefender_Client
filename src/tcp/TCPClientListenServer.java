package tcp;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class TCPClientListenServer implements Runnable{
	
	private Socket socket;
	DataInputStream input;
	
	private boolean running = true;
	
	public TCPClientListenServer(Socket socket){
	
		try {	
			this.socket = socket;
			input = new DataInputStream( socket.getInputStream() );
			running = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setRunning(boolean running){
		this.running = running;
	}
	@Override
	public void run(){
		while(running)
		{
			try 
			{
					System.out.println("tcp listen");
					String msg = input.readUTF();
					TCPClient.getInstance().onReceive(msg);
			} catch (Exception e) {
				try {
					socket.close();
					running = false;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}