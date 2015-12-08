import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Roots {
	private int[][] roots = new int[12][4];
	public Roots(String fileName) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src/" + fileName));
		int line = 0;
		String str;
		while ((str = reader.readLine()) != null) {
			for (int row = 0; row < str.length(); row++) {
				String s = str.substring(row, ++row);
				this.roots[line][row/2] = Integer.parseInt(s);
			}
			line++;
		}
	}
	
	public static void main(String[] args) {
		Roots r = null;
		try {
			r = new Roots("file.in");
		} catch (NumberFormatException | IOException e) {}
		int[][] answer = r.calculate(r.roots);
		for (int[] a : answer) {
			System.out.println(Arrays.toString(a));
		}
	}
	
	public int[][] calculate(int[][] src) {
		Stack<int[]> s = new Stack<int[]>();
		int j = 0;
		while(check(src)) {
			j++;
			Random rnd = new Random();
			for (int i = 0; i < 12; i++) {
				int randomPosition = rnd.nextInt(12);
			    int[] tmp = src[i];
			    src[i] = src[randomPosition];
			    src[randomPosition] = tmp;
			}
		} System.err.println(j);
		return src;
	}
	
	public boolean check(int[][] a) {
		if (a[0][3] + a[1][2] + a[3][1] + a[4][0] == 10 &&
				a[2][3] + a[3][2] + a[6][1] + a[7][0] == 10 &&
				a[3][3] + a[4][2] + a[7][1] + a[8][0] == 10 &&
				a[4][3] + a[5][2] + a[8][1] + a[9][0] == 10 &&
				a[7][3] + a[8][2] + a[10][1] + a[11][0] == 10 &&
				a[0][1] + a[1][0] <= 10 &&
				a[2][2] + a[6][0] <= 10 &&
				a[5][3] + a[9][1] <= 10 &&
				a[10][3] + a[11][2] <= 10 &&
				a[0][2] + a[2][1] + a[3][0] <= 10 &&
				a[1][3] + a[4][1] + a[5][0] <= 10 &&
				a[6][3] + a[7][2] + a[10][0] <= 10 &&
				a[8][3] + a[9][2] + a[11][1] <= 10
				) {
			return false;
		}
		return true;
	}
}