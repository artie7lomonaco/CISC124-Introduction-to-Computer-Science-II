package testingGround;
import java.util.*;

class test1 {
	public static void main(String[] args) {
		for (int i = 0; i <= 360 + 1; i++) {
			
			if (i % 90 == 0) {
				System.out.println("i = "+ i + ", cos = " + Math.cos(((7.0 * i) * Math.PI) / 180.0) + ", sin = " + Math.sin(((7.0 * i) * Math.PI) / 180.0));
			}
		}
		//System.out.println(Math.cos(7 * 270));
	}

}