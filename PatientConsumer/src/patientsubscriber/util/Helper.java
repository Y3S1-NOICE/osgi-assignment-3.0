package patientsubscriber.util;

import java.util.Scanner;

public class Helper {
	public static int takeInput(int noOfChoices, Scanner sc) {
		while(true) {
			boolean checked = false;
			int input;
			
			System.out.print("Enter a number : ");
			input = sc.nextInt();
			
			for (int i = 1; i <= noOfChoices; i++) {
				checked = ( i == input ? true : false );
				if (checked) {
					return input;
				}
			}
		}
	}
}
