
package receiver;

public class UDPMessageReceiver implements ReceiveAction{

	UDPMessageDistributor distributor;
	public UDPMessageReceiver() {
		distributor = new UDPMessageDistributor();
	}
	
	@Override
	public void run(String msg) {
		distributor.distributeMessage(msg);
	}
}
