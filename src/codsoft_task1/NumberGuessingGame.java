package codsoft_task1;


import java.util.Scanner;

public class NumberGuessingGame {
    private static final int ATTEMPTS = 10;
    private static final int MAX_SCORE = 100;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ROUNDS = 3; // Maximum number of rounds allowed

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain = true;
        int  currentRound = 0;


        System.out.println("\nWelcome to the Ultimate Number Guessing Game! \n");
        System.out.println("You have " + ATTEMPTS + " attempts per round to guess the correct number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
        System.out.println("You can play up to " + MAX_ROUNDS + " rounds.");

        while (playAgain) {
            currentRound++;
            System.out.println("Round " + currentRound + " of " + MAX_ROUNDS);

            int randomNumber = getRand(MIN_NUMBER, MAX_NUMBER);
            boolean guessedCorrectly = false;
            int score = MAX_SCORE;
            int scoreDecrement = MAX_SCORE / ATTEMPTS;

            for (int i = 0; i < ATTEMPTS; i++) {
                System.out.println("Attempt " + (i + 1) + ": Enter your guess (" + MIN_NUMBER + "-" + MAX_NUMBER + "):");

                // Validate user input
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ":");
                    sc.next(); // Clear invalid input
                }

                int userGuess = sc.nextInt();

                // Check if the guess is within the valid range
                if (userGuess < MIN_NUMBER || userGuess > MAX_NUMBER) {
                    System.out.println("Out of range. Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ":");
                    i--; // Do not count this as an attempt
                    continue;
                }

                // Compare user's guess with the random number
                if (userGuess == randomNumber) {
                    guessedCorrectly = true;
                    System.out.println("Hooray! You guessed it right!");
                    break;
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high");
                } else {
                    System.out.println("Too low.");
                }

                // Decrease score based on the number of attempts
                score -= scoreDecrement;
            }

            // Handle the case where the user did not guess the number
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber+ ".");
                score = 0; // Reset the score if the user loses
            }

            System.out.println("Your score: " + score + " out of " + MAX_SCORE+ ". ");

            // Ask the user if they want to play again, if the maximum rounds haven't been reached
            if (currentRound < MAX_ROUNDS) {
                System.out.println("Do you want to play another round? (y/n)");
                String again = sc.next();
                playAgain = again.equalsIgnoreCase("y");
            } else {
                System.out.println("You've reached the maximum number of rounds.");
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing! Hope you enjoyed the game. See You next time...");
        sc.close();
    }

    // Method to generate a random number between min and max (inclusive)
    public static int getRand(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
