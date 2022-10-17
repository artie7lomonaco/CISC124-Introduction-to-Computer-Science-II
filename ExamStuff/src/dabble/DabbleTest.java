package dabble;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DabbleTest {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	
	static List<Character> getChars(Map<Integer, String> m) {
		List<Character> c = new ArrayList<>();
		for (String s : m.values()) {
			for (int i = 0; i < s.length(); i++) {
				c.add(s.charAt(i));
			}
		}
		return c;
	}
	
	static void sameLetters(Map<Integer, String> m1, Map<Integer, String> m2) {
		List<Character> c1 = getChars(m1);
		List<Character> c2 = getChars(m2);
		c1.sort(null);
		c2.sort(null);
		assertEquals("map words have different letters",
				c1, c2);
	}
	
	@Test
	public void test_ctor01_setsMaps() {
		Dabble d = new Dabble();
		assertEquals("wrong number of solution words",
				5, d.getSolutionWords().size());
		assertEquals("wrong number of scrambled words",
				5, d.getScrambledWords().size());
		TreeMap<Integer, String> m1 = new TreeMap<>(d.getScrambledWords());
		TreeMap<Integer, String> m2 = new TreeMap<>(d.getSolutionWords());
		for (int i = 2; i <= 6; i++) {
			// keys
			assertTrue("scrambled words missing key: " + i,
					m1.keySet().contains(i));
			assertTrue("solution words missing key: " + i,
					m2.keySet().contains(i));
			
			// values
			assertEquals("scrambled map has string of wrong length", 
					i, m1.get(i).length());
			assertEquals("solution map has string of wrong length", 
					i, m1.get(i).length());
		}
	}
	
	@Test
	public void test_ctor01_createsRandomWords() {
		Dabble d = new Dabble();
		sameLetters(d.getSolutionWords(), d.getScrambledWords());
	}
	
	
	
	@Test
	public void test_ctor02_mapSizes() {
		Dabble d = new Dabble("it", "bit", "four", "seven", "insert");
		Map<Integer, String> got = d.getScrambledWords();
		int exp = 5;
		int n = got.size();
		assertEquals("wrong number of scrambled words",
				exp, n);
		
		assertEquals("wrong number of solution words",
				exp, d.getSolutionWords().size());
	}

	@Test
	public void test_ctor02_mapWordLengths() {
		Dabble d = new Dabble("it", "bit", "four", "seven", "insert");
		
		// scrambled words
		Map<Integer, String> got = d.getScrambledWords();
		TreeMap<Integer, String> sorted = new TreeMap<>(got);
		Integer exp = 2;
		for (Integer length : sorted.keySet()) {
			assertEquals(exp, length);
			exp++;
		}
		// solution words
		sorted = new TreeMap<>(d.getSolutionWords());
		exp = 2;
		for (Integer length : sorted.keySet()) {
			assertEquals(exp, length);
			exp++;
		}
	}
	
	private static List<Character> string2List(String s) {
		List<Character> result = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			result.add(s.charAt(i));
		}
		return result;
	}
	
	@Test
	public void test_ctor02_setsSolutionWords() {
		Dabble d = new Dabble("it", "bit", "four", "seven", "insert");
		Map<Integer, String> got = d.getScrambledWords();
		List<Character> solutionLetters = new ArrayList<>();
		solutionLetters.addAll(string2List("it"));
		solutionLetters.addAll(string2List("bit"));
		solutionLetters.addAll(string2List("four"));
		solutionLetters.addAll(string2List("seven"));
		solutionLetters.addAll(string2List("insert"));
		
		List<Character> scrambledLetters = new ArrayList<>();
		for (String s : got.values()) {
			scrambledLetters.addAll(string2List(s));
		}
		
		Collections.sort(solutionLetters);
		scrambledLetters.sort(null);
		
		assertEquals(solutionLetters, scrambledLetters);
	}
	
	@Test
	public void test_exchange() {
		Dabble d = new Dabble("it", "bit", "four", "seven", "insert");
		Map<Integer, String> got = d.getScrambledWords();
		String s2 = got.get(2);
		String s3 = got.get(3);
		
		d.exchange(2, 0, 3, 1);
		
		String t2 = got.get(2);
		String t3 = got.get(3);
		assertEquals(s3.charAt(1), t2.charAt(0));
		assertEquals(s2.charAt(0), t3.charAt(1));
	}
	
	@Test
	public void test_isSolved() {
		Dabble d = new Dabble("go", "tea", "bash", "maple", "python");
		Map<Integer, String> scram = d.getScrambledWords();
		scram.put(2, "go");
		scram.put(3, "tea");
		scram.put(4, "bash");
		scram.put(5, "maple");
		scram.put(6, "python");
		assertTrue(d.isSolved());
		
		scram.put(2, "no");
		scram.put(3, "bag");
		scram.put(4, "soap");
		scram.put(5, "empty");
		scram.put(6, "health");
		assertTrue(d.isSolved());
	}
	
}
