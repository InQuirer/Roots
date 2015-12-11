import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	ArrayList<int[]> fixed = new ArrayList<>();
	ArrayList<int[]> free = new ArrayList<>();
	/**
	 * @return true if this solution is correct (additional checking)
	 */
	public boolean isValid() {
		if (fixed != null && free.isEmpty() && fixed.size() == 12)
			if (	fixed.get(0)[3] + fixed.get(1)[2] + fixed.get(3)[1] + fixed.get(4)[0] == 10 &&
					fixed.get(2)[3] + fixed.get(3)[2] + fixed.get(6)[1] + fixed.get(7)[0] == 10 &&
					fixed.get(3)[3] + fixed.get(4)[2] + fixed.get(7)[1] + fixed.get(8)[0] == 10 &&
					fixed.get(4)[3] + fixed.get(5)[2] + fixed.get(8)[1] + fixed.get(9)[0] == 10 &&
					fixed.get(7)[3] + fixed.get(8)[2] + fixed.get(10)[1] + fixed.get(11)[0] == 10 &&
					fixed.get(0)[1] + fixed.get(1)[0] <= 10 &&
					fixed.get(2)[2] + fixed.get(6)[0] <= 10 &&
					fixed.get(5)[3] + fixed.get(9)[1] <= 10 &&
					fixed.get(10)[3] + fixed.get(11)[2] <= 10 &&
					fixed.get(0)[2] + fixed.get(2)[1] + fixed.get(3)[0] <= 10 &&
					fixed.get(1)[3] + fixed.get(4)[1] + fixed.get(5)[0] <= 10 &&
					fixed.get(6)[3] + fixed.get(7)[2] + fixed.get(10)[0] <= 10 &&
					fixed.get(8)[3] + fixed.get(9)[2] + fixed.get(11)[1] <= 10
					) {
				return true;
			}
		return false;
	}
	/**
	 * prints current node's 'fixed' squares
	 */
	public void print() {
		for (int[] i : fixed) {
			System.out.println(
					i[0] + " " +
					i[1] + " " +
					i[2] + " " +
					i[3]);
		}
		System.out.println();
	}
	/**
	 * Overrides method implemented from Object.
	 */
	public String toString() {
		String s = "fixed: ";
		for (int[] a : fixed) {
			s += Arrays.toString(a);
		} s += "\nfree: ";
		for (int[] a : free) {
			s += Arrays.toString(a);
		}
		return s + "\n";
	}
}
