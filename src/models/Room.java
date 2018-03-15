package models;

import java.io.Serializable;

public class Room implements Serializable{

	private static final long serialVersionUID = 414661724803492296L;
	private static int id;
	private int roomNr;
	private Ticket ticket;
	
	public Room() {
		id++;
		roomNr = id +100;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public int getRoomNr() {
		return roomNr;
	}
	
	public void checkIn(Person person) {
		ticket = person != null ? new Ticket(person, this) : null;
	}
	public Ticket checkOut() {
		Ticket checkOutTicket = ticket;
		ticket = null;
		return checkOutTicket;
	}

	@Override
	public String toString() {
		return "Room [roomNr=" + roomNr + ", ticket=" + ticket + "]\n";
	}
	
	
}
