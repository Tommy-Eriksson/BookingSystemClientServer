package client;

import java.io.IOException;
import models.Person;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UI {

    private static Client client;

    public static void main(String[] args) {
        try {
            client = new Client();

            while (true) {
                switch (printUIMenu()) {
                    case '1': 
                        client.printVacantRooms();
                        break;
                    case '2': 
                        client.printCheckedinPersons();
                        break;
                    case '3': 
                        client.checkIn(printUICheckIn());
                        break;
                    case '4': 
                        client.checkOut(printUICheckOut());
                        break;
                    case '9':
                        client.close();
                        return;
                    default:
                        break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("resource")
	private static char printUIMenu() {
        StringBuilder sr = new StringBuilder();

        sr.append("************************************\n");
        sr.append("*            MENY                  *\n");
        sr.append("************************************\n");
        sr.append("* 1. Visa lediga rum               *\n");
        sr.append("* 2. Visa incheckade personer      *\n");
        sr.append("* 3. Checka in person              *\n");
        sr.append("* 4. Checka ut person              *\n");
        sr.append("* 9. Avsluta                       *\n");

        sr.append("\nGör ditt val: ");
        System.out.print(sr.toString());

        Scanner in = new Scanner(System.in);

        return in.next().charAt(0);
    }

    @SuppressWarnings("resource")
	private static Person printUICheckIn() throws IOException, ClassNotFoundException {

        if (client.getVacantRoom() == null) {
            System.out.println("Hotellet är fullbelagt...\n");
            return null;
        }

        Scanner in = new Scanner(System.in);

        System.out.println("Checka in ny gäst:");

        System.out.println("Skriv in namn: ");
        String name = in.nextLine();

        System.out.println("Skriv in ålder: ");
        int age = in.nextInt();

        return new Person(name, age);
    }

    @SuppressWarnings("resource")
	private static String printUICheckOut() throws IOException {
        
        Scanner in = new Scanner(System.in);

        System.out.println("Ange ticket ID:");

        return in.nextLine();
    }
}
