class Untitled {
	public static void drawtriangle(int size){
		int col;
		int row;
		for (row = 1; row <= size; row++){
			for (col = 0; col < row; col++){
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
	public static void main(String[] args) {
		drawtriangle(10);
	}
}