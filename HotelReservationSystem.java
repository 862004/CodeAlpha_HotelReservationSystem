import java.util.*;

class Room {
    int roomNumber;
    String type;
    boolean isBooked;

    Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isBooked = false;
    }
}

class Booking {
    String customerName;
    Room room;

    Booking(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
    }
}

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        int choice;
        do {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: viewAvailableRooms(); break;
                case 2: bookRoom(); break;
                case 3: viewBookings(); break;
                case 4: cancelBooking(); break;
                case 5: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Deluxe"));
        rooms.add(new Room(103, "Suite"));
        rooms.add(new Room(104, "Standard"));
        rooms.add(new Room(105, "Deluxe"));
    }

    static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.roomNumber + " - " + room.type);
            }
        }
    }

    static void bookRoom() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        viewAvailableRooms();
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && !room.isBooked) {
                room.isBooked = true;
                bookings.add(new Booking(name, room));
                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("Room not available or invalid number.");
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking booking : bookings) {
                System.out.println("Name: " + booking.customerName + ", Room: " + booking.room.roomNumber + " (" + booking.room.type + ")");
            }
        }
    }

    static void cancelBooking() {
        System.out.print("Enter your name to cancel booking: ");
        String name = scanner.nextLine();
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.customerName.equalsIgnoreCase(name)) {
                booking.room.isBooked = false;
                iterator.remove();
                System.out.println("Booking canceled.");
                return;
            }
        }
        System.out.println("No booking found under this name.");
    }
}