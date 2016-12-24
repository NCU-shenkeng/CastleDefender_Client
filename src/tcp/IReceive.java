package tcp;

import java.net.Socket;

public interface IReceive {
	public void onReceive(String msg);
	public void afterReceive(Socket client);
}
