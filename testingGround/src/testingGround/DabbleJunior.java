package testingGround;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import dict.Dictionary;

public class DabbleJunior {
	List<String> solutions;
	List<String> scrambled;
	//public static final Dictionary DICT = new Dictionary();
	public static final int MIN_WORD_LENGTH = 3;
	public static final int MAX_WORD_LENGTH = 5;
	public static final int NUMBER_OF_WORDS = 3;
	
	
	public DabbleJunior(String a, String b, String c){
		this.solutions = new ArrayList<>();
		this.scrambled = new ArrayList<>();
		this.solutions.add(a);
		this.solutions.add(b);
		this.solutions.add(c);
		if (a.length() != 3) {
			throw new IllegalArgumentException();
		}
		if (b.length() != 4) {
			throw new IllegalArgumentException();
		}
		if (c.length() != 5) {
			throw new IllegalArgumentException();
		}
		this.scrambled = new ArrayList<>();
	}
	/**
	 * Scramble method
	 * @param solution
	 * @return
	 */
	public List<String> Scramble(List<String> solution){
		List<String> result = new ArrayList<>();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i <= solution.size(); i++) {
			String word = solution.get(i);
			List<Character> shuffle = new ArrayList<>();
			for (int j = 0; j <= word.length(); j++) {
				shuffle.add(word.charAt(j-1));
			}
			Collections.shuffle(shuffle);
			s.append(solution.get(0));
			for (int k = 0; k <= shuffle.size(); k++) {
				s.append(shuffle.get(k));
			}
			result.add(s.toString());
		}
		return result;
	}
	
	/**
	 * Score method
	 * @param solution
	 * @return
	 */
	public int Score(List<String> solution) {
		int result = 0;
		for (int i = 0; i <= NUMBER_OF_WORDS; i++) {
			if (this.solutions.get(i).equals(this.scrambled.get(i))) {
				result += 1;
			}
		}
		return result;
	}
	
	/**
	 * Exchange method
	 * @param solution
	 * @return
	 */
	public void exchange(int len1, int index1, int len2, int index2) {
		StringBuilder firstString;
		StringBuilder secondString;
		Integer firstIndex = len1;
		Integer secondIndex = len2;
		boolean len1Bool = len1 >= 3 & len1 <= 5;
		boolean len2Bool = len2 >= 3 & len2 <= 5;
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
	
	public static void main(String[] args) {
	}

}
