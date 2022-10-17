package testingGround;

public class Person {
	String person;
	public Person(String s) {
		this.person = s;
	}
	
	public String getNam() {
		return this.person;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person tom = new Person("Tom Cruise");
		Person ethanHunt = tom;
		Person spy = ethanHunt;
		Person prof = "Dr. Liskov";
		Person artist = "Terry Uyarak";
		spy = artist;
		artist = prof;
		prof = tom;
		tom = spy;
//		System.out.println(tom.getNam());
//		System.out.println(Integer.MAX_VALUE + 2147483648);
//		System.out.println(Integer.MIN_VALUE);
	}

}
