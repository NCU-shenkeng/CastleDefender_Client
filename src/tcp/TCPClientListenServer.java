package tcp;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class TCPClientListenServer implements Runnable{
	
	private Socket socket;
	DataInputStream input;
	
	private static boolean running = true;
	
	public TCPClientListenServer(Socket socket){
	
		try {	
			this.socket = socket;
			input = new DataInputStream( socket.getInputStream() );
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
				if(socket.isConnected() &&(input.available() > 0)){
					String msg = input.readUTF();
					TCPClient.getInstance().onReceive(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}
}