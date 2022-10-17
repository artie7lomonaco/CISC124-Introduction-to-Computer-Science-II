package dabble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import dict.Dictionary;

/**
 * A class that models the state of the puzzle game Dabble.
 * 
 * <p>
 * The game Dabble is made up of five English words of lengths 2, 3, 4, 5, and 6
 * (these five words make up a solution for the puzzle). The letters of the all
 * of the words are randomly scrambled together to produce five scrambled puzzle
 * words of lengths 2, 3, 4, 5, and 6. For example, the five solution words:
 * 
 * <p>
 * {@code it, you, here, batch, burner}
 * 
 * <p>
 * might produce the five scrambled words:
 * 
 * <p>
 * {@code un, uei, ytrc, bahrt, obrehe}
 * 
 * <p>
 * A player attempts to unscramble the words by repeatedly exchanging a letter
 * from one scrambled word with the letter in a second scrambled word.
 * 
 * <p>
 * The puzzle is solved when the player has formed five English words of lengths
 * 2, 3, 4, 5, and 6 (not necessarily the same words as the original solution
 * because there is often multiple solutions).
 *
 */
public class Dabble {

	private Map<Integer, String> solution;
	private Map<Integer, String> scrambled;

	/**
	 * The dictionary used by the class.
	 */
	public static final Dictionary DICT = new Dictionary();

	/**
	 * The shortest word length in the game.
	 */
	public static final int MIN_WORD_LENGTH = 2;

	/**
	 * The longest word length in the game.
	 */
	public static final int MAX_WORD_LENGTH = 6;

	/**
	 * The number of words in the game.
	 */
	public static final int NUMBER_OF_WORDS = 5;

	/**
	 * Initializes this dabble to a specific set of scrambled and solution words for
	 * debugging and testing purposes.
	 * 
	 * @param notUsed not used, included so that this constructor would have a
	 *                different signature than the other constructors
	 */
	public Dabble(int notUsed) {
		this.solution = new TreeMap<>();
		this.scrambled = new TreeMap<>();

		this.solution.put(2, "ad");
		this.solution.put(3, "bet");
		this.solution.put(4, "cook");
		this.solution.put(5, "dumps");
		this.solution.put(6, "eclair");

		this.scrambled.put(2, "ri");
		this.scrambled.put(3, "alc");
		this.scrambled.put(4, "espm");
		this.scrambled.put(5, "udkoo");
		this.scrambled.put(6, "ctebad");
	}

	/**
	 * Initialize the words of the game by choosing random words from a dictionary.
	 */
	public Dabble() {
		this.solution = new TreeMap();
		this.scrambled = new TreeMap();
		List<String> solList = new ArrayList();
		for (int i = Dabble.MIN_WORD_LENGTH; i <= Dabble.MAX_WORD_LENGTH; i++) {
			solList.add(getWord(i));
		}
		String[] tmp = new String[solList.size()];
		tmp = solList.toArray(tmp);
		List<String> scramSolList = Scramble(tmp);
		for (int i = 0; i < scramSolList.size(); i++) {
			this.scrambled.put(i + Dabble.MIN_WORD_LENGTH, scramSolList.get(i));
			this.solution.put(i + Dabble.MIN_WORD_LENGTH, solList.get(i));
		}
	}

	private boolean inDict(String[] solutions) {
		for (int i = 0; i < solutions.length; i++) {
			if (!Dabble.DICT.contains(solutions[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Initialize the words of the game by using the specified words.
	 * 
	 * <p>
	 * There must be exactly {@code NUMBER_OF_WORDS} strings in the {@code words}
	 * otherwise an exception is thrown. Furthermore, the strings must be in
	 * ascending order of length from {@code MIN_WORD_LENGTH, MIN_WORD_LENGTH + 1,
	 * MIN_WORD_LENGTH + 2, ... , MAX_WORD_LENGTH}. Finally, the strings must all be
	 * contained in the dictionary used by the class.
	 * 
	 * @param words an array of NUMBER_OF_WORDS strings in ascending order of length
	 * @throws IllegalArgumentException if
	 *                                  {@code words.length != Dabble.NUMBER_OF_WORDS}
	 *                                  or if the strings in word are not in
	 *                                  ascending order by length
	 */
	public Dabble(String... words) {
		this.solution = new TreeMap<>();
		this.scrambled = new TreeMap<>();
		if (Dabble.NUMBER_OF_WORDS != words.length) {
			throw new IllegalArgumentException("Expected 5 words, got " + Arrays.toString(words));
		}
		if (!inDict(words)) {
			throw new IllegalArgumentException("Expected all words to be in dictionary");
		}
		for (int i = 2; i < words.length; i++) {
			if (words[i].length() < words[i - 1].length()) {
				throw new IllegalArgumentException("Array strings must be in ascending order by length");
			}
		}
		List<String> newScr = Scramble(words);
		for (int i = 0; i < words.length; i++) {
			scrambled.put(i + 2, newScr.get(i));
			solution.put(i + 2, words[i]);
		}
	}

	/**
	 * Returns a string representation of the puzzle.
	 * 
	 * <p>
	 * The returned string consists of each scrambled word separated by a comma and
	 * space, followed by space-colon-space, followed by each solution word
	 * separated by a comma and space.
	 * 
	 * @return a string representation of the puzzle
	 */
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder(this.scrambled.get(Dabble.MIN_WORD_LENGTH));
		for (int len = Dabble.MIN_WORD_LENGTH + 1; len <= Dabble.MAX_WORD_LENGTH; len++) {
			b.append(", ");
			b.append(this.scrambled.get(len));
		}
		b.append(" : ");
		b.append(this.solution.get(Dabble.MIN_WORD_LENGTH));
		for (int len = Dabble.MIN_WORD_LENGTH + 1; len <= Dabble.MAX_WORD_LENGTH; len++) {
			b.append(", ");
			b.append(this.solution.get(len));
		}
		return b.toString();
	}

	/**
	 * Returns {@code true} if each scrambled word is contained in the dictionary
	 * used by the class, {@code false} otherwise.
	 * 
	 * <p>
	 * It is not the case that the scrambled words must be equal to the solution
	 * words because it is possible that many different solutions exist for any
	 * given puzzle.
	 * 
	 * @return {@code true} if each scrambled word is contained in the dictionary
	 *         used by the class, {@code false} otherwise.
	 */
	public boolean isSolved() {
		for (int i = 2; i < scrambled.size() + 2; i++) {
			if (Dabble.DICT.contains(scrambled.get(i)) == false) {
				return false;
			}
		}
		return true;
	}

	private String getWord(int length) {
		Random rng = new Random();
		int len = length;
		List<String> t = Dabble.DICT.getWordsByLength(len);
		int index = rng.nextInt(t.size());
		String word = t.get(index);
		return word;
	}

	/**
	 * Exchange a letter in one scrambled word with a letter from a second scrambled
	 * word. The two scrambled words might be the same word, in which case two
	 * letters are exchanged in the same word.
	 * 
	 * <p>
	 * The letter having {@code index1} in the scrambled word having length
	 * {@code len1} is exchanged with the letter having {@code index2} in the
	 * scrambled word having length {@code len2}.
	 * 
	 * <p>
	 * Consider the dabble {@code d} whose string representation is equal to:
	 * 
	 * <p>
	 * {@code "eb, ueu, eyoh, rnhti, rrtacb : it, you, here, batch, burner"}
	 * 
	 * <p>
	 * Then {@code d.exchange(2, 0, 5, 4)} would exchange the first letter of
	 * {@code "eb"} with the last letter of {@code "rnhti"}, and
	 * {@code d.toString()} would return the string equal to:
	 * 
	 * <p>
	 * {@code "ib, ueu, eyoh, rnhtb, rrtacb : it, you, here, batch, burner"}
	 * 
	 * @param len1   the length of the first word
	 * @param index1 the index of the letter to exchange of the first word
	 * @param len2   the length of the second word
	 * @param index2 the index of the letter to exchange of the second word
	 * @throws IllegalArgumentException if {@code len1} or {@code len2} are not
	 *                                  valid Dabble word lengths, or if
	 *                                  {@code index1} or {@code index2} are not
	 *                                  valid indexes for their respective strings
	 */
	public void exchange(int len1, int index1, int len2, int index2) {
		StringBuilder firstString;
		StringBuilder secondString;
		Integer firstIndex = len1;
		Integer secondIndex = len2;
		boolean len1Bool = len1 >= 2 & len1 <= 6;
		boolean len2Bool = len2 >= 2 & len2 <= 6;
		boolean index1Bool = index1 >= 0 & index1 <= len1;
		boolean index2Bool = index2 >= 0 & index2 <= len2;
		char index1Letter;
		char index2Letter;
		if (len1Bool == false || len2Bool == false) {
			throw new IllegalArgumentException("Expected valid Dabble word length");
		}
		if (index1Bool == false || index2Bool == false) {
			throw new IllegalArgumentException("Expected valid string index");
		}
		firstString = new StringBuilder(scrambled.get(firstIndex));
		secondString = new StringBuilder(scrambled.get(secondIndex));
		index1Letter = firstString.charAt(index1);
		index2Letter = secondString.charAt(index2);
		if (len1 != len2) {
			firstString.setCharAt(index1, index2Letter);
			secondString.setCharAt(index2, index1Letter);
			scrambled.replace(firstIndex, firstString.toString());
			scrambled.replace(secondIndex, secondString.toString());
		} else {
			char tmp = firstString.charAt(index1);
			firstString.setCharAt(index1, index2Letter);
			firstString.setCharAt(index2, tmp);
			scrambled.replace(firstIndex, firstString.toString());
		}

	}

	private List<String> Scramble(String[] solutions) {
		List<Character> shuffled = new ArrayList();
		List<String> scrambled = new ArrayList();
		StringBuilder ss = new StringBuilder();
		for (int i = 0; i < solutions.length; i++) {
			for (int j = 0; j < solutions[i].length(); j++) {
				shuffled.add(solutions[i].charAt(j));
			}
		}
		Collections.shuffle(shuffled);
		for (int i = 0; i < shuffled.size(); i++) {
			ss.append(shuffled.get(i));
		}
		int index = 0;
		for (int i = 0; i < solutions.length; i++) {
			int length = solutions[i].length();
			String newSs = ss.substring(index, length + index);
			scrambled.add(newSs);
			index += length;
		}
		System.out.println(ss);
		System.out.println(scrambled.toString());
		return scrambled;
	}

	/**
	 * Returns the map of scrambled words.
	 * 
	 * <p>
	 * The returned map maps the word length to a scrambled word.
	 * 
	 * @return the map of scrambled words
	 */
	public Map<Integer, String> getScrambledWords() {
		// ALREADY DONE FOR YOU
		return this.scrambled;
	}

	/**
	 * Returns a map of solution words. More than one solution may exist; this
	 * method always returns the solution that was used to generate the puzzle.
	 * 
	 * <p>
	 * The returned map maps the word length to a solution word.
	 * 
	 * @return the map of solution words
	 */
	public Map<Integer, String> getSolutionWords() {
		// ALREADY DONE FOR YOU
		return this.solution;
	}

	public static void main(String[] args) {
		Dabble dab = new Dabble(0);
		System.out.println(dab);

	}
}
