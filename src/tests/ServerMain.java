package tests;

import java.io.IOException;
import java.net.InetAddress;

import api.Node;
import api.Protocol;
import api.ReciverMessageListener;

public class ServerMain {

	public static void main(String[] args) throws IOException {

		Node server = Node.build(3000, Protocol.TCP);
		System.out.println("Server Running");
		server.startReceiver(new ReciverMessageListener() {

			public void receiverMessage(String message, InetAddress address, int port) throws IOException {

				System.out.println("------> "+message);


				server.send(address, port, message); // echos
			}
		});

	}

}
