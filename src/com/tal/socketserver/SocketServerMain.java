package com.tal.socketserver;

import java.io.IOException;

public class SocketServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Socket server starting main...");

		try {
			SocketServer s = new SocketServer(8080);
		} catch (IOException e) {
			System.out.println("Couldn't create server.");
		}
	
	System.out.println("end");
	}

}
