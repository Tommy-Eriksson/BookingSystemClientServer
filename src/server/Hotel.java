package server;

import models.Person;
import models.Room;
import models.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {

    private List<Room> rooms = new ArrayList<>();

    public Hotel(int nrOfRooms) {
        for (int i = 0; i < nrOfRooms; i++) {
            rooms.add(new Room());
        }
    }

    public void checkIn(Person person) {
        getVacantRoom().checkIn(person);
    }

    public void checkOut(String id) {
        
        Room room = rooms.stream()
                .filter(r -> r.getTicket() != null)
                .filter(r -> r.getTicket().getId().equals(id))
                .findFirst()
                .orElse(null);

        if (room != null) {
            room.checkOut();
        }
    }
    
    public List<Ticket> getTickets() {
        
        ArrayList<Ticket> tickets = new ArrayList<>();

        rooms
            .stream()
            .filter(r -> r.getTicket() != null)
            .forEach(r -> tickets.add(r.getTicket()));
        return tickets;
    }

    public List<Room> getVacantRooms() {
        return rooms
                .stream()
                .filter(r -> r.getTicket() == null)
                .collect(Collectors.toList());
    }

    public Room getVacantRoom() {
        if (getVacantRooms().isEmpty()) {
            return null;
        }
        return getVacantRooms()
                .stream()
                .findAny()
                .get();
    }
}
