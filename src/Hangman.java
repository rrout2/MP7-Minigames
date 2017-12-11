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
	private ArrayList<Character> used;
	private int wrongGuesses;
	private static String[] ascii = new String[]{

		"   ________\n   |      |\n   |\n   |\n   |\n   |\n   |\n_______",
		"   ________\n   |      |\n   |      O\n   |\n   |\n   |\n   |\n_______",
		"   ________\n   |      |\n   |      O\n   |      |\n   |\n   |\n   |\n_______",
		"   ________\n   |      |\n   |      O\n   |      |\n   |     /\n   |\n   |\n_______",
		"   ________\n   |      |\n   |      O\n   |      |\n   |     / \\\n   |\n   |\n_______",
		"   ________\n   |      |\n   |      O\n   |     /|\n   |     / \\\n   |\n   |\n_______",
		"   ________\n   |      |\n   |      O\n   |     /|\\\n   |     / \\\n   |\n   |\n_______"};
	private static String dead = "                      ____________________\n"+
            "                      |                    |\n"+
            "                      |                  ----\n"+
            "                      |                /  X X \\\n"+
            "                      |               |   ___  |\n"+
            "                      |                \\      /\n" +
            "                      |                 ```|``\n" +
            "                      |                    |\n" +
            "                      |                   /|\\\n" +
            "                      |                  / | \\\n" +
            "                      |                 /  |  \\\n" +
            "                      |                /   |   \\\n" +
            "                      |                    |\n" +
            "                      |                    |\n" +
            "                      |                    |\n" +
            "                      |                   / \\\n" +
            "                      |                  /   \\\n" +
            "                      |                 /     \\\n" +
            "                      |\n"+
            "                      |\n"+
            "                      |\n"+
            "                      |\n"+
            "                      |\n"+
            "                      |\n"+
"------------------------------------------";
	private static String alive = "                      ____________________\n"+
            "                      |                    |\n"+
            "                      |                  ----\n"+
            "                      |                /  ^ ^ \\\n"+
            "                      |               |    ▼  |\n"+
            "                      |                \\      /\n" +
            "                      |                 ```|``\n" +
            "                      |                    |\n" +
            "                      |                   /|\\\n" +
            "                      |                  / | \\\n" +
            "                      |                 /  |  \\\n" +
            "                      |                /   |   \\\n" +
            "                      |                    |\n" +
            "                      |                    |\n" +
            "                      |                    |\n" +
            "                      |                   / \\\n" +
            "                      |                  /   \\\n" +
            "                      |                 /     \\\n" +
            "                      |\n"+
            "                      |\n"+
            "                      |\n"+
            "                      |\n"+
            "                      |\n"+
            "                      |\n"+
"------------------------------------------";
	public Hangman(char[] word) { // constructor
		wordToGuess = word;
		wordSoFar = new char[word.length];
		for (int i = 0; i < word.length; i++) {
			if (word[i] == ' ') wordSoFar[i] = ' ';
			else wordSoFar[i] = '_';
		}
		chancesLeft = 6;
		solved = false;
		movesTaken = 0;
		used = new ArrayList<Character>();
		wrongGuesses = 0;
	}
	
	public void play() {
		Scanner kb = new Scanner(System.in);
		while(chancesLeft > 0 && !solved) {
			System.out.println(ascii[6 - chancesLeft]);
			System.out.println("Your word/phrase so far is " + String.valueOf(wordSoFar) + ".\nYou have " + chancesLeft + " chance(s) left. What letter is your next guess?");
			char guess = kb.next().toLowerCase().charAt(0);
			movesTaken++;
			guess(guess);
			if (String.valueOf(wordSoFar).equals(String.valueOf(wordToGuess))) solved = true;
			used.add(guess);
		}
		
		if (solved) {
			System.out.println("Congrats! You won on the word/phrase \"" + String.valueOf(wordToGuess) + "\" in " + movesTaken + " guesses!");
			System.out.println(alive);
		} else {
			System.out.println("Oooh, you lost on the word/phrase \"" + String.valueOf(wordToGuess) + "\"!");
			System.out.println(dead);
		}
		kb.close();
	}
	
	private void guess(char c) {
		if(used.contains(c)) {
			System.out.println("You've already tried that letter!");
			return;
		}
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
			wrongGuesses++;
		}
	}
	
	public static void main(String[] args) {
		int choice = (int)(Math.random() * (possibilities.length));
		Hangman test = new Hangman(possibilities[choice].toCharArray());
		test.play();
	}
}
