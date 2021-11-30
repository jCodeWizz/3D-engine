import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int answer = new Random().nextInt(10) + 1;
		int guessed = 0;
		
		do {
			System.out.println("Enter your guess: ");
			Scanner s = new Scanner(System.in);
			guessed = s.nextInt();
			System.out.println("Your guess is: " + guessed);
			
			if(answer != guessed) {
				System.out.println("WRONG!");
				System.out.println();
			}
			
			s.close();
		} while(answer != guessed);
		
		
		System.out.println("CORRECT!");
	}
}
