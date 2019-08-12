package tests;

import java.io.IOException;
import java.net.InetAddress;

import api.Node;
import api.Protocol;
import api.ReciverMessageListener;

public class ClientMain {

	public static void main(String[] args) throws IOException {

		Node client = Node.build(3001, Protocol.TCP);
		System.out.println("Client Running");
		client.startReceiver(new ReciverMessageListener() {

			public void receiverMessage(String message, InetAddress address, int port) throws IOException {

				System.out.println("------> "+message);

			}
		});

		client.send(InetAddress.getLocalHost(), 3000, "Hello!");

	}
}
