import java.util.*;
public class MinigameRunner {

	public static void main(String[] args) {
		Scanner kb  = new Scanner (System.in);
		System.out.print("What game would you like to play?\n1: Hangman\n2: Tic-Tac-Toe\nPick a number 1-2: ");
		int choice = (int)(kb.next().charAt(0));
		if (choice == 49) {
			Hangman.main(args);
		} else if (choice == 50) {
			TicTacToe.main(args);
		} else {
			System.out.print("Pick a number 1-2 please. ");
			main(args);
		}
		kb.close();
		
	}
	

}
