class Untitled {
	public static void drawtriangle(int size){
		int col = 0;
		int row = 0;
		while (row < size){
			row += 1;
			while (col < row){
				col += 1;
				System.out.print("*");
			}
			col = 0;
			System.out.println();
		}
	}
	public static void main(String[] args) {
		drawtriangle(10);
	}
}