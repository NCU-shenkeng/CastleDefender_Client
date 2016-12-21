package receiver;

public class MessageReceiver implements ReceiveAction{

	MessageDistributor distributor;
	public MessageReceiver() {
		distributor = new MessageDistributor();
	}
	
	@Override
	public void run(String msg) {
		distributor.distributeMessage(msg);
	}
	
}
