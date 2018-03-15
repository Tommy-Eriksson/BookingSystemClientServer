package models;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 189917067119101105L;
	private Person person;
	private Room room;
	private String id;
	private Date checkInDate;
	
	public Ticket(Person person, Room room) {
		this.person = person;
		this.room = room;
		checkInDate = new Date();
		id = generateId();
	}

	public String getId() {
		return id;
	}

	public Person getPerson() {
		return person;
	}

	public Room getRoom() {
		return room;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	@Override
	public String toString() {
		return "\nRum nr " + this.getRoom().getRoomNr() + ": \t" + this.getPerson().toString() + 
				"\n\t\tIncheckad: "+ checkInDate + 
				"\n\t\tTicket: " + id +"\n";
	}

	private String generateId() {
		Random random = new Random();
		
		return person.getName().replaceAll("\\s", "")+random.nextInt();
	}
}
