package testingGround;

public class Length {    
	private double l;
	private String u;
	public Length(double d, String s) {
		if (d < 0) {
			throw new IllegalArgumentException();
		}
		if (s.equals("mm") || s.equals("m") || s.equals("km")) {
			this.l = d;
			this.u = s;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	public double tomm(double dd, String ss) {
		String s = ss;
		double d = dd;
		if (s.equals("km")) {
			d = d * 1000000.0;
			return d;
		}
		else if (s.equals("m")) {
			d = d * 1000.0;
			return d;
		}
		else {
			return d;
		}
	}
	public double tom(double dd, String ss) {
		String s = ss;
		double d = dd;
		if (s.equals("km")) {
			d = d * 1000.0;
			return d;
		}
		else if (s.equals("mm")) {
			d = d / 1000.0;
			return d;
		}
		else {
			return d;
		}
	}
		public double toKm(double dd, String ss) {
			String s = ss;
			double d = dd;
			if (s.equals("m")) {
				d = d / 1000.0;
				return d;
			}
			else if (s.equals("mm")) {
				d = d / 1000000.0;
				return d;
			}
			else {
				return d;
			}
	}
	public String toString() {
		return Double.toString(toKm(this.l, this.u)) + "km";
	}
}
