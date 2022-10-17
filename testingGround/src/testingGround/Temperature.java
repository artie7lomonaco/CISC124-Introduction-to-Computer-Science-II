package testingGround;

public class Temperature {
	private String strT;
	public Temperature() {
		this.strT = "0";
	}
	public Temperature(String s) {
		boolean b;
		if (s.charAt(s.length() - 1) == 'c' || s.charAt(s.length() - 1) == 'f') {
			b = true;
		}
		
		
		
	}
	public String toFar() {
		StringBuilder s = new StringBuilder(this.strT);
		double d;
		if (strT.charAt(strT.length() - 1) == 'c') {
			s.deleteCharAt(strT.length() - 1);
			d = Double.parseDouble(s.toString()) * (9.0 / 5) + 32;
			System.out.println(d);
			s = new StringBuilder(Double.toString(d));
			s.append("f");
			return s.toString();
		}
		return this.strT;
	}
	public String toCel() {
		StringBuilder s = new StringBuilder(this.strT);
		double d;
		if (strT.charAt(strT.length() - 1) == 'f') {
			s.deleteCharAt(strT.length() - 1);
			d = (Double.parseDouble(s.toString()) - 32) * (5.0 / 9) ;
			s = new StringBuilder(Double.toString(d));
			s.append("c");
			return s.toString();
		}
		return this.strT;
	}
	
	public static void main(String[] args) {
		Temperature t = new Temperature("100f");
		System.out.print(t.toCel());
	}
}
