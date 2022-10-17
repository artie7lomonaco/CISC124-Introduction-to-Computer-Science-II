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
		String[] words = new String[Dabble.NUMBER_OF_WORDS];

		Random rng = new Random();
		for (int len = Dabble.MIN_WORD_LENGTH; len <= Dabble.MAX_WORD_LENGTH; len++) {
			// pick a random word of length len
			List<String> t = Dabble.DICT.getWordsByLength(len);
			int index = rng.nextInt(t.size());
			String word = t.get(index);
			words[len - Dabble.MIN_WORD_LENGTH] = word;
		}
		this.solution = new TreeMap<>();
		this.scrambled = new TreeMap<>();
		this.scramble(words);
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
		if (words.length != Dabble.NUMBER_OF_WORDS) {
			throw new IllegalArgumentException("expected 5 words, got " + Arrays.toString(words));
		}
		for (int i = 0; i < words.length; i++) {
			Dabble.testLength(words[i], i + Dabble.MIN_WORD_LENGTH);
			if (!DICT.contains(words[i])) {
				throw new IllegalArgumentException("not in dictionary: " + words[i]);
			}
		}
		this.solution = new TreeMap<>();
		this.scrambled = new TreeMap<>();
		this.scramble(words);
	}

	private static final void testLength(String s, int expLength) {
		if (s.length() != expLength) {
			String err = String.format("bad length, got %s, expected length %d", s, expLength);
			throw new IllegalArgumentException(err);
		}
	}

	private static final List<Character> toList(String s) {
		List<Character> t = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			t.add(s.charAt(i));
		}
		return t;
	}

	private final void scramble(String[] words) {
		List<Character> letters = new ArrayList<>();
		for (int len = Dabble.MIN_WORD_LENGTH; len <= Dabble.MAX_WORD_LENGTH; len++) {
			String word = words[len - Dabble.MIN_WORD_LENGTH];
			this.solution.put(len, word);
			letters.addAll(Dabble.toList(word));
		}
		Collections.shuffle(letters);
		int i = 0;
		for (int len = Dabble.MIN_WORD_LENGTH; len <= Dabble.MAX_WORD_LENGTH; len++) {
			List<Character> t = letters.subList(i, i + len);
			String s = "";
			for (Character c : t) {
				s += c;
			}
			this.scrambled.put(len, s);
			i += len;
		}
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
		if (len1 < Dabble.MIN_WORD_LENGTH || len1 > Dabble.MAX_WORD_LENGTH) {
			throw new IllegalArgumentException();
		}
		if (len2 < Dabble.MIN_WORD_LENGTH || len2 > Dabble.MAX_WORD_LENGTH) {
			throw new IllegalArgumentException();
		}
		if (index1 < 0 || index1 >= len1) {
			throw new IllegalArgumentException();
		}
		if (index2 < 0 || index2 >= len2) {
			throw new IllegalArgumentException();
		}
		if (len1 == len2) {
			// swap characters in same word
			StringBuilder b = new StringBuilder(this.scrambled.get(len1));
			char c1 = b.charAt(index1);
			char c2 = b.charAt(index2);
			b.setCharAt(index1, c2);
			b.setCharAt(index2, c1);
			this.scrambled.put(len1, b.toString());
		} else {
			// swap characters in different words
			StringBuilder b1 = new StringBuilder(this.scrambled.get(len1));
			StringBuilder b2 = new StringBuilder(this.scrambled.get(len2));
			char c1 = b1.charAt(index1);
			char c2 = b2.charAt(index2);
			b1.setCharAt(index1, c2);
			b2.setCharAt(index2, c1);
			this.scrambled.put(len1, b1.toString());
			this.scrambled.put(len2, b2.toString());
		}
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
		for (String word : this.scrambled.values()) {
			if (!Dabble.DICT.contains(word)) {
				return false;
			}
		}
		return true;
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
		return this.solution;
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

	public static void main(String[] args) {
		Dabble dab = new Dabble("it", "you", "here", "batch", "burner");
		System.out.println(dab);
		dab.exchange(2, 0, 5, 4);
		System.out.println(dab);
	}
}
