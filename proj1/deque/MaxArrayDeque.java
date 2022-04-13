package deque;

import java.util.Arrays;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
	
	private Comparator<T> comparator;

	public 	MaxArrayDeque(Comparator<T> c) {
		comparator = c;
	}
	
	public T max() {
		return max(this.comparator);
	}
	
	public T max(Comparator<T> c) {
		if (this.isEmpty()) return null;
		
		T maxArrayDeque = this.get(0);

		for (int i = 1; i < this.Array.length; i++){
			if (this.get(i) == null) continue;

			if(c.compare(maxArrayDeque, this.get(i)) < 0) {
				maxArrayDeque = this.get(i);
			}
        }
		return maxArrayDeque;
	}
	
}
