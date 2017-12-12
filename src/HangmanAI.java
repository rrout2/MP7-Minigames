import java.io.*;
import java.util.*;

public class HangmanAI {
	private int wordLength;
	private List<String> corpus;
	private Map<Character, Integer> defaultFreq;
	private boolean[] guessed;
	
	public HangmanAI(int wordLength) {
		this.wordLength = wordLength;
		BufferedReader reader;
		corpus = new ArrayList<String>();
		guessed = new boolean[26];
		Arrays.fill(guessed, false);
		
		try {
			reader = new BufferedReader(new FileReader("words.txt"));
			String line = reader.readLine();
			while(line != null) {
				corpus.add(line);
				line = reader.readLine();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return;
		}
		
		defaultFreq = letterFreq();
		
		for (Iterator<String> iter = corpus.listIterator(); iter.hasNext(); ) {
			if (iter.next().length() != wordLength) {
				iter.remove();
			}
		}
	}
	
	public char nextLetter(char[] letters) {
		int c = nextInd(letters, 0);
		while (c < letters.length) {
			for (Iterator<String> iter = corpus.iterator(); iter.hasNext(); ) {
				if (iter.next().charAt(c) != letters[c]) {
					iter.remove();
				}
			}	
			c = nextInd(letters, c + 1);
		}
		
		Map<Character, Integer> frequency = letterFreq();
		
		char currMax = getMax(frequency);
		while (currMax != ' ' && guessed[(int)currMax - 'a']) {
			frequency.remove(currMax);
			currMax = getMax(frequency);
		}
		
		if (currMax == ' ') {
			return currMax;
		}
		
		guessed[(int)currMax - 'a'] = true;
		
		for (String i : corpus) {
			System.out.println(i);
		}
		
		System.out.println(corpus.size());
		return currMax;
	}
		
	public int nextInd(char[] letters, int start) {
		int c = start;
		while (c < letters.length && letters[c] == ' ') { 
			c++;
		}
		
		return c;
	}
	
	public char getMax(Map<Character, Integer> dict) {
		char max = ' ';
		int maxCount = 0;
		for (Map.Entry<Character, Integer> entry: dict.entrySet()) {
			if (entry.getValue() > maxCount) {
				max = entry.getKey();
				maxCount = entry.getValue();
			}
		}
		
		return max;
	}
	
	public Map<Character, Integer> letterFreq() {
		Map<Character, Integer> freq = new HashMap<Character, Integer>();
		for (int i = 0; i < 26; i ++) {
			freq.put((char)(97 + i), 0);
		}
		int[] frequency = new int[26];
		for (String s : corpus) {
			for (int i = 0; i < 26; i++) {
				char character = (char)(i + 'a');
				if (s.contains(character + "")) {
					freq.put(character, freq.get(character) + 1);
				}
			}
		}
		return freq;
	}
	
	public void addToGuessed(char c) {
		guessed[c - 'a'] = true;
	}
	
	public static void main(String args[]) {
		
	}
}
