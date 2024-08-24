import java.util.Scanner;
import java.util.Random;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        int maxAttempts = 10;
        int attempts = 0;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between " + lowerBound + " and " + upperBound + ". Can you guess it?");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < targetNumber) {
                System.out.println("Higher! You have " + (maxAttempts - attempts) + " attempts left.");
            } else if (userGuess > targetNumber) {
                System.out.println("Lower! You have " + (maxAttempts - attempts) + " attempts left.");
            } else {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                score = maxAttempts - attempts;
                System.out.println("Your score is: " + score);
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.out.println("Sorry, you've reached the maximum number of attempts. The number was " + targetNumber + ".");
        }

        scanner.close();
    }
}
