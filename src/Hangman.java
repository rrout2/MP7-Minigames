import java.util.*;
public class Hangman {
	private static final String[] possibilities = new String[]{"cs rocks", "illinois is better than michigan", "wisconsin sux", 
	"i love pokemon", "ultimate is the best sport", "bill chapman", 
	"lawrence angrave", "geoffrey challen"};
	private final char[] wordToGuess;
	private char[] wordSoFar;
	private int chancesLeft;
	private boolean solved;
	private int movesTaken; 
	private String[] ascii = new String[]{
		"   ________\n   |      |\n   |\n   |\n   |\n   |\n   |\n_______",
		"   ________\n   |      |\n   |      O\n   |\n   |\n   |\n   |\n_______",
		"   ________\n   |      |\n   |      O\n   |      |\n   |\n   |\n   |\n_______"};
	
	public Hangman(char[] word) {
		wordToGuess = word;
		wordSoFar = new char[word.length];
		for (int i = 0; i < word.length; i++) {
			if (word[i] == ' ') wordSoFar[i] = ' ';
			else wordSoFar[i] = '_';
		}
		chancesLeft = word.length / 3;
		solved = false;
		movesTaken = 0;
	}
	
	public void play() {
		Scanner kb = new Scanner(System.in);
		while(chancesLeft > 0 && !solved) {
			System.out.println("Your word so far is " + String.valueOf(wordSoFar) + ".\nYou have " + chancesLeft + " chances left. What letter is your next guess?");
			char guess = kb.next().toLowerCase().charAt(0);
			movesTaken++;
			guess(guess);
			if (String.valueOf(wordSoFar).equals(String.valueOf(wordToGuess))) solved = true;
		}
		
		if (solved) {
			System.out.println("Congrats! You won on the word/phrase \"" + String.valueOf(wordToGuess) + "\" in " + movesTaken + " guesses!");
			System.out.println(ascii[1]);
		} else {
			System.out.println("Oooh, you lost on the word/phrase " + String.valueOf(wordToGuess) + "!");
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
		int choice = (int)(Math.random() * (possibilities.length));
		Hangman test = new Hangman(possibilities[choice].toCharArray());
		test.play();
	}
}
