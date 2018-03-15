package client;

import models.Person;
import models.Room;
import models.Ticket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Client() throws IOException {
        socket = new Socket("localhost", 9999);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }
    
    @SuppressWarnings("unchecked")
	public void printVacantRooms() throws IOException, ClassNotFoundException {
        output.writeUTF("GET_VACANT_ROOMS");
        output.flush();
        ArrayList<Room> response = (ArrayList<Room>) input.readObject();
        System.out.println(response);
    }

    @SuppressWarnings("unchecked")
	public void printCheckedinPersons() throws IOException, ClassNotFoundException {
        output.writeUTF("GET_TICKETS");
        output.flush();
        ArrayList<Ticket> response = (ArrayList<Ticket>) input.readObject();
        System.out.println(response);
    }

	public void checkIn(Person person) throws IOException {
		if (person != null) {
			output.writeUTF("CHECK_IN");
			output.writeObject(person);
			output.flush();
		}
	}

    public void checkOut(String id) throws IOException {
        output.writeUTF("CHECK_OUT");
        output.writeUTF(id);
        output.flush();
    }

    public Room getVacantRoom() throws IOException, ClassNotFoundException {
        output.writeUTF("GET_VACANT_ROOM");
        output.flush();
        return (Room) input.readObject();
    }

    public void close() throws IOException {
        input.close();
        output.close();
        socket.close();
    }
}
