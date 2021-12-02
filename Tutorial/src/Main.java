import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// scanner used for getting input via console
		Scanner s = new Scanner(System.in);

		// correct number
		int correct = new Random().nextInt(100) + 1;

		// var input
		int input = 0;
		
		// var lives
		int lives = 5;

		do {
			System.out.println("Enter your guess: ");
			
			// get input
			input = s.nextInt();
			
			// check if input is okay
			if (input < 101 && input > 0) {
				
				// check if input is correct
				if (input != correct) {
					
					// give a hint about the number
					if (input < correct) {
						System.out.println("Your guess was too low!");
					} else {
						System.out.println("Your guess was too high!");
					}

					// reduct lives
					lives--; // lives -= 7;
					System.out.println("You lost a live! {" + lives + "} lives left");
					System.out.println();
				}
			} else {
				System.out.println("Your guess was not accepted, try again");
			}
		} while (input != correct && lives > 0);

		// check if the player has won
		if (lives > 0) {
			System.out.println("Your guess was correct!");
		} else {
			System.out.println("Your guesses were incorrect!");
			System.out.println("The correct number was " + correct);
		}

		s.close();
	}
}
