package com.company;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        while (true) {
            System.out.println("Hotel Reservation System");
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View reservation details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    hotel.searchRooms();
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int numberOfNights = scanner.nextInt();
                    hotel.makeReservation(guestName, roomNumber, numberOfNights);
                    break;
                case 3:
                    System.out.print("Enter reservation ID: ");
                    int reservationId = scanner.nextInt();
                    hotel.viewReservationDetails(reservationId);
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
    }
}
class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;
    private double pricePerNight;
    public Room(int roomNumber, String category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
        this.pricePerNight = pricePerNight;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public String getCategory() {
        return category;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public double getPricePerNight() {
        return pricePerNight;
    }
    public void bookRoom() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Room is already booked.");
        }
    }
    public void freeRoom() {
        isAvailable = true;
    }
}
class Reservation {
    private int reservationId;
    private Room room;
    private String guestName;
    private int numberOfNights;
    private double totalPrice;
    public Reservation(int reservationId, Room room, String guestName, int numberOfNights) {
        this.reservationId = reservationId;
        this.room = room;
        this.guestName = guestName;
        this.numberOfNights = numberOfNights;
        this.totalPrice = room.getPricePerNight() * numberOfNights;
        room.bookRoom();
    }
    public int getReservationId() {
        return reservationId;
    }
    public Room getRoom() {
        return room;
    }
    public String getGuestName() {
        return guestName;
    }
    public int getNumberOfNights() {
        return numberOfNights;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
}
class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int reservationCounter;
    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        reservationCounter = 1;
        initializeRooms();
    }
    private void initializeRooms() {
        rooms.add(new Room(101, "Single", 50.0));
        rooms.add(new Room(102, "Double", 100.0));
        rooms.add(new Room(103, "Triple", 150.0));
        rooms.add(new Room(104, "Deluxe", 200.0));
        // Add more rooms as needed
    }
    public void searchRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Category: " + room.getCategory() + ", Price per Night: $" + room.getPricePerNight());
            }
        }
    }
    public void makeReservation(String guestName, int roomNumber, int numberOfNights) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                Reservation reservation = new Reservation(reservationCounter++, room, guestName, numberOfNights);
                reservations.add(reservation);
                System.out.println("Reservation successful. Reservation ID: " + reservation.getReservationId());
                return;
            }
        }
        System.out.println("Room is not available or does not exist.");
    }
    public void viewReservationDetails(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                System.out.println("Reservation ID: " + reservation.getReservationId());
                System.out.println("Guest Name: " + reservation.getGuestName());
                System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
                System.out.println("Category: " + reservation.getRoom().getCategory());
                System.out.println("Number of Nights: " + reservation.getNumberOfNights());
                System.out.println("Total Price: $" + reservation.getTotalPrice());
                return;
            }
        }
        System.out.println("Reservation not found.");
    }
}