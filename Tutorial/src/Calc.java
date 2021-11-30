import java.util.Scanner;

public class Calc {
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int a = 0;
		int b = 0;
		char operator = ' ';
		
		System.out.println("Enter a: ");
		a = s.nextInt();
		System.out.println("Enter b: ");
		b = s.nextInt();
		System.out.println("Enter operator: ");
		operator = s.next().charAt(0);
		
		if(operator == '-') {
			System.out.println("RESULT: " + (a - b));
		} else if(operator == '+') {
			System.out.println("RESULT: " + (a + b));
		} else if(operator == '/') {
			System.out.println("RESULT: " + (a / b));
		} else if(operator == '*') {
			System.out.println("RESULT: " + (a * b));
		}
		
		s.close();
	}
}
