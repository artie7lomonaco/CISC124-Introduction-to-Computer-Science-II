package chess;

public class Board {

	private int width;
	private int height;
	
	public Board() {
		this.width = 8;
		this.height = 8;
	}
	
	public Board(int w, int h) {
		this.width = w;
		this.height = h;
	}
	
	public int getW() {
		return this.width;
	}
	
	
	public int getH() {
		return this.height;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board(8,8);
		System.out.println(b.getH());

	}

}
