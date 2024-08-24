import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task1 {
    private Map<String, String> users = new HashMap<>();
    private Map<Integer, Reservation> reservations = new HashMap<>();
    private int reservationId = 1;
    private String loggedInUser = null;

    public static class Reservation {
        public String username;
        public int numSeats;

        public Reservation(String username, int numSeats) {
            this.username = username;
            this.numSeats = numSeats;
        }
    }

    public Task1() {
        // Simulated user database (username, password)
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    public boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedInUser = username;
            return true;
        }
        return false;
    }

    public void logout() {
        if (loggedInUser != null) {
            System.out.println("User " + loggedInUser + " logged out successfully.");
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public void makeReservation(int numSeats) {
        if (loggedInUser != null) {
            if (numSeats > 0) {
                reservations.put(reservationId, new Reservation(loggedInUser, numSeats));
                System.out.println("Reservation successful. Reservation ID: " + reservationId);
                reservationId++;
            } else {
                System.out.println("Number of seats must be a positive number.");
            }
        } else {
            System.out.println("Please log in first.");
        }
    }

    public void cancelReservation(int reservationId) {
        if (loggedInUser != null && reservations.containsKey(reservationId)) {
            Reservation reservation = reservations.get(reservationId);
            if (reservation.username.equals(loggedInUser)) {
                reservations.remove(reservationId);
                System.out.println("Reservation canceled successfully.");
            } else {
                System.out.println("You can only cancel your own reservations.");
            }
        } else {
            System.out.println("Invalid reservation ID or you are not logged in.");
        }
    }

    public void register(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
        } else {
            users.put(username, password);
            System.out.println("Registration successful.");
        }
    }

    public static void main(String[] args) {
        Task1 system = new Task1();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Make Reservation");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter a username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter a password: ");
                    String newPassword = scanner.nextLine();
                    system.register(newUsername, newPassword);
                    break;
                case 2:
                    if (system.loggedInUser != null) {
                        System.out.println("You are already logged in as " + system.loggedInUser);
                    } else {
                        System.out.print("Enter your username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String password = scanner.nextLine();
                        if (system.login(username, password)) {
                            System.out.println("Login successful.");
                        } else {
                            System.out.println("Login failed. Please try again.");
                        }
                    }
                    break;
                case 3:
                    if (system.loggedInUser != null) {
                        System.out.print("Enter the number of seats to reserve: ");
                        int numSeats = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        system.makeReservation(numSeats);
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 4:
                    if (system.loggedInUser != null) {
                        System.out.print("Enter the reservation ID to cancel: ");
                        int reservationId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        system.cancelReservation(reservationId);
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 5:
                    system.logout();
                    break;
                case 6:
                    System.out.println("Exiting the reservation system.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
