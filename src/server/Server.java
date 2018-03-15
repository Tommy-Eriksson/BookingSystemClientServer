package server;

import models.Person;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	protected static Hotel hotel;
	protected static Date date;

	private static ServerSocket serverSocket;

	public Server() {
		hotel = new Hotel(2);
		date = new Date();
	}

	public static void main(String[] args) {

		Server server = new Server();

		try {
			server.run();
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void run() throws IOException, ClassNotFoundException {

		serverSocket = new ServerSocket(9999);
		System.out.println("Server running on http://localhost:9999");
		
		while (true) {
			// Vänta på och starta ny tråd för varje klient som ansluter
			new Thread(new ClientConnection(serverSocket.accept())).start();
		}
	}

	public static class ClientConnection implements Runnable {

		private Socket socket;
		private ObjectOutputStream output;
		private ObjectInputStream input;

		public ClientConnection(Socket socket) throws IOException {
			this.socket = socket;
			this.output = new ObjectOutputStream(socket.getOutputStream());
			this.input = new ObjectInputStream(socket.getInputStream());
			// Logga anslutning
			System.out.println(date + " " + socket.getInetAddress() + ":" + socket.getLocalPort() + " CONNECTED");
		}

		public void run() {
			while (socket.isConnected()) {	
				try {
					while (input.available() > 0) {
						// Läs in request 
						String request = input.readUTF();
						// Logga request
						System.out.println(date + " " + socket.getInetAddress() + ":" + socket.getLocalPort()+ " - REQUEST: " + request);
						// Hantera request
						synchronized (hotel) { 
							switch (request) {
								case "GET_VACANT_ROOMS":
									output.writeObject(hotel.getVacantRooms());
									break;
								case "GET_VACANT_ROOM":
									output.writeObject(hotel.getVacantRoom());
									break;
								case "CHECK_IN":
									hotel.checkIn((Person) input.readObject());
									break;
								case "CHECK_OUT":
									hotel.checkOut((String) input.readUTF());
									break;
								case "GET_TICKETS":
									output.writeObject(hotel.getTickets());
									break;
							}
							output.flush();
							hotel.notifyAll();
						}
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}