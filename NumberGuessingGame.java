import java.util.Scanner;

public class NumberGuessingGame 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int round = 1;

        while (true) 
        {
            System.out.println("\nRound " + round);
            int numberToGuess = (int) (Math.random() * 100) + 1;
            int attemptsLeft = 5;

            while (attemptsLeft > 0) 
            {
                System.out.print("Guess a number between 1 and 100: ");
                int guess = scanner.nextInt();

                if (guess == numberToGuess) 
                {
                    System.out.println("Congratulations! You guessed the number correctly.");
                    score++;
                    break;
                } else if (guess < numberToGuess) 
                {
                    System.out.println("Too low. Try again.");
                } else 
                {
                    System.out.println("Too high. Try again.");
                }

                attemptsLeft--;
            }

            if (attemptsLeft == 0) 
            {
                System.out.println("Sorry, you've run out of attempts. The number was: " + numberToGuess);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("yes")) 
            {
                break;
            }

            round++;
        }

        System.out.println("\nGame over! Your final score is: " + score);
        scanner.close();
    }
}