import java.util.*;
public class Hangman {
	private char[] wordToGuess;
	private char[] wordSoFar;
	private int chancesLeft;
	boolean solved;
	public Hangman(char[] word) {
		wordToGuess = word;
		wordSoFar = new char[word.length];
		for (int i = 0; i < word.length; i++) {
			if (word[i] == ' ') wordSoFar[i] = ' ';
			else wordSoFar[i] = '_';
		}
		chancesLeft = word.length * 2 / 3;
		solved = false;
	}
	
	public void play() {
		Scanner kb = new Scanner(System.in);
		while(chancesLeft > 0 && !solved) {
			System.out.println("Your word so far is " + String.valueOf(wordSoFar) + ".\nYou have " + chancesLeft + " chances left. What letter is your next guess?");
			char guess = kb.next().toLowerCase().charAt(0);
			guess(guess);
			if (String.valueOf(wordSoFar).equals(String.valueOf(wordToGuess))) solved = true;
		}
		
		if (solved) {
			System.out.println("Congrats! You won on the word " + String.valueOf(wordToGuess) + "!");
		} else {
			System.out.println("Oooh, you lost on the word " + String.valueOf(wordToGuess) + "!");
		}
		kb.close();
	}
	
	private void guess(char c) {
		boolean contains = false;
		for (char elem : wordToGuess) {
			if (elem == c) contains = true;
		}
		if(contains) {
			System.out.println("The word contains " + c + "!");
			String throwaway = String.valueOf(wordToGuess);
			while(throwaway.indexOf(c) >= 0) {
				wordSoFar[throwaway.indexOf(c)] = c;
				String stringed = String.valueOf(c);
				throwaway = throwaway.replaceFirst(stringed, "*");
			}
			
		} else {
			System.out.println("The word doesn't contain " + c + "!");
			chancesLeft--;
		}
	}
	
	public static void main(String[] args) {
		String[] possibilities = new String[]{"cs rocks", "illinois is better than michigan", "wisconsin sux", "i love pokemon", "ultimate is the best sport"};
		int choice = (int)(Math.random() * (possibilities.length));
		Hangman test = new Hangman(possibilities[choice].toCharArray());
		test.play();
	}
}
