import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {
    public String username;
    public String password;
    public String profile;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.profile = "Student";
    }

    public boolean authenticate(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }

    public String getUsername() {
        return username;
    }

    public void updateProfile(String newProfile) {
        profile = newProfile;
    }

    public void updatePassword(String newPassword) {
        password = newPassword;
    }
}

class Question {
    public String question;
    public String[] options;
    public int correctOption;

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}

public class Task4 {
    public User currentUser;
    public Question[] questions;
    public Timer timer;

    public Task4() {
        currentUser = null;
        timer = new Timer();
        questions = new Question[3];
        questions[0] = new Question("What is 2 + 2?", new String[]{"A. 3", "B. 4", "C. 5", "D. 6"}, 1);
        questions[1] = new Question("What is the capital of France?", new String[]{"A. London", "B. Berlin", "C. Paris", "D. Madrid"}, 2);
        questions[2] = new Question("Which planet is known as the Red Planet?", new String[]{"A. Venus", "B. Jupiter", "C. Mars", "D. Saturn"}, 2);
    }

    public void displayMenu() {
        System.out.println("Online Examination System Menu:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Update Profile");
        System.out.println("4. Update Password");
        System.out.println("5. Start Exam");
        System.out.println("6. Close Session");
        System.out.println("7. Logout");
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        currentUser = new User(username, password);
        System.out.println("Registration successful. Welcome, " + currentUser.getUsername() + "!");
    }

    public void login() {
        if (currentUser != null) {
            System.out.println("You are already logged in as " + currentUser.getUsername());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (currentUser != null && currentUser.authenticate(username, password)) {
            System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }
    }

    public void updateProfile() {
        if (currentUser != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your new profile: ");
            String newProfile = scanner.nextLine();
            currentUser.updateProfile(newProfile);
            System.out.println("Profile updated.");
        } else {
            System.out.println("Please log in first.");
        }
    }

    public void updatePassword() {
        if (currentUser != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your new password: ");
            String newPassword = scanner.nextLine();
            currentUser.updatePassword(newPassword);
            System.out.println("Password updated.");
        } else {
            System.out.println("Please log in first.");
        }
    }

    public void startExam() {
        if (currentUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.question);
            for (int i = 0; i < question.options.length; i++) {
                System.out.println(question.options[i]);
            }

            System.out.print("Enter your choice (1-4): ");
            int selectedOption = scanner.nextInt();

            if (question.isCorrect(selectedOption - 1)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }
        }

        System.out.println("Exam completed. Your score: " + score + " out of " + questions.length);
    }

    public void closeSession() {
        if (currentUser == null) {
            System.out.println("Please log in first.");
            return;
        }
        System.out.println("Session closed.");
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("Logged out. Goodbye, " + currentUser.getUsername() + "!");
            currentUser = null;
        } else {
            System.out.println("You are not logged in.");
        }
    }

    public static void main(String[] args) {
    Task4 examSystem = new Task4();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            examSystem.displayMenu();
            System.out.print("Enter your choice (1-7): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    examSystem.register();
                    break;
                case 2:
                    examSystem.login();
                    break;
                case 3:
                    examSystem.updateProfile();
                    break;
                case 4:
                    examSystem.updatePassword();
                    break;
                case 5:
                    examSystem.startExam();
                    break;
                case 6:
                    examSystem.closeSession();
                    break;
                case 7:
                    examSystem.logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}