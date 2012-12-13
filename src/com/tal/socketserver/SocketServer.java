package com.tal.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	//example data to return. will be incremented for each client call
	private int importantData = 1;

	private int port;

	//Sockets
	private ServerSocket serverSocket = null;
	Socket clientSocket = null;

	//In and out writers
	PrintWriter out;
	BufferedReader in;


	public SocketServer() throws IOException{
		this(8080);
	}

	public SocketServer(int port) throws IOException{
		this.port = port;

		//Create the socket
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Error creating socket: ");
			e.printStackTrace();
			throw(e);
		}
		startServer();
	}

	private void startServer() throws IOException{
		//Listen to client
		clientSocket = serverSocket.accept();

		//create the in and out
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		runServer();
	}

	private void runServer() throws IOException{
		String inputLine, outputLine;

		while ((inputLine = in.readLine()) != null) {
			System.out.println("Input received from " + clientSocket.getInetAddress() +": " + inputLine);
			outputLine = "DATA=" + importantData;
			importantData++;
			out.println(outputLine);
		}

		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
}
