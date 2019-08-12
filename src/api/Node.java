package api;


import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public abstract class Node {
	
	protected int port;
	protected InetAddress address;
	protected Protocol protocol;
	protected boolean started = false;
	
	protected Node(){
		
	}
	
	/**
	 * @param port the receiving messages server will be started on this port
	 * @param Protocol indicates the protocol will be used in communication
	 * @return an instance for communication
	 * @throws IOException
	 */
	public static Node build(int port, Protocol Protocol) throws IOException {
		Node node = null;
		switch (Protocol) {
		
		case TCP:	
	
			node = new NodeTCP(port);
			break;
			
		case UDP:
		
			node = new NodeUDP(port);	
			break;
		
		default:
			return null;
		}
		node.port = port;
		node.protocol = Protocol;
		node.address = InetAddress.getLocalHost();
		return node;
	}
	
	
	/**
	 * sends a message to a device
	 * @param address 
	 * @param port 
	 * @param message
	 * @throws IOException
	 */
	public abstract void send(InetAddress address, int port, String message) throws IOException, SocketTimeoutException ;
	
	/**
	 * Starts receiving messages
	 * @param reciverMessageListener indicates listener for receiving messages
	 */
	public abstract void startReceiver(final ReciverMessageListener reciverMessageListener);
	
	/**
	 * @return informs the receiving messages is enabled
	 */
	public boolean isStarted() {
		return started;
	}
	/**
	 * ends communication
	 * @throws IOException
	 */
	public abstract void close() throws IOException;

	
	public static final char DELIMITER = '#';
	
	protected String formart(int port,String string){
		return port+String.valueOf(DELIMITER)+string;
	}
	protected int deformGetPort(String message){
		try{
			StringBuffer portBuffer = new StringBuffer();
			
			for (int i = 0; i < message.length(); i++) {
				char c = message.charAt(i);
				if(c == DELIMITER) {
					break;
				}else {
					portBuffer.append(c);
				}
			}	
			int port = Integer.parseInt(portBuffer.toString());
			
			return port;
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}
	
	protected String deformGetMessage(String message){
		try{
			
			StringBuffer msgBuffer = new StringBuffer();
			boolean find = false;
			for (int i = 0; i < message.length(); i++) {
				
				char c = message.charAt(i);
				
				if(find)msgBuffer.append(c);
				
				if(c == DELIMITER) {
					find = true;
				}
				
				
			}	

			return msgBuffer.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
		
	public int getPort() {
		return port;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public InetAddress getInetAddress() {
		
		return address;
	}

	public String getAddress() {
		
		return address.getHostAddress();
	}
	
	
}
