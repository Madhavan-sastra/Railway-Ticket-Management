import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private UserDAO userDAO = new UserDAO();
    private TrainDAO trainDAO = new TrainDAO();
    private BookingDAO bookingDAO = new BookingDAO();

    public void showMenu() {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void register() {
        User user = new User();
        System.out.print("Enter username: ");
        user.setUsername(scanner.nextLine());
        System.out.print("Enter password: ");
        user.setPassword(scanner.nextLine());
        System.out.print("Enter email: ");
        user.setEmail(scanner.nextLine());
        System.out.print("Enter phone: ");
        user.setPhone(scanner.nextLine());

        userDAO.registerUser(user);
        System.out.println("Registration successful!");
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDAO.loginUser(username, password);
        if (user != null) {
            System.out.println("Login successful!");
            showUserMenu(user);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void showUserMenu(User user) {
        while (true) {
            System.out.println("1. Book Ticket");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bookTicket(user);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void bookTicket(User user) {
        List<Train> trains = trainDAO.getAllTrains();
        System.out.println("Available Trains:");
        for (Train train : trains) {
            System.out.println(train.getTrainId() + ": " + train.getTrainName() + " from " + train.getSource() + " to " + train.getDestination());
        }

        System.out.print("Enter train ID to book: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        Booking booking = new Booking();
        booking.setUserId(user.getUserId());
        booking.setTrainId(trainId);
        booking.setBookingDate(java.time.LocalDate.now().toString());
        booking.setStatus("Booked");
        booking.setPaymentStatus("Pending");

        bookingDAO.bookTicket(booking);
        System.out.println("Ticket booked successfully!");
    }
}
