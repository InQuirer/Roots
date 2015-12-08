import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Squares {
	private ArrayList<int[]> squares = new ArrayList<int[]>();
	private ArrayList<Node> answers = new ArrayList<Node>();
	/**
	 * 
	 * @param fileName file to import (must be stored in src/..)
	 * @throws NumberFormatException file structure incorrect
	 * @throws IOException file structure incorrect
	 */
	public Squares(String fileName) throws NumberFormatException, IOException {
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader("src/" + fileName));
		String str;
		while ((str = reader.readLine()) != null) {
			int[] square = new int[4];
			for (int row = 0; row < str.length(); row++) {
				String s = str.substring(row, ++row);
				square[row/2] = Integer.parseInt(s);
			}
			this.squares.add(square);
		}
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Squares r = null;
		try {
			r = new Squares("file.in");
		} catch (NumberFormatException | IOException e) {}
		r.run();
		long end = System.currentTimeMillis();
		System.err.println("Runtime: " + (end - start) + " ms");
	}
	/**
	 * adds all possible answers to 'answers' list
	 */
	public void calculate() {
		// all possible squares, that can be at position 1 and 2
		ArrayList<Node> promisingNodesList1 = new ArrayList<Node>();
		for (int[] sq1 : squares) {
			for (int[] sq2 : squares) {
				if (sq1 != sq2 && sq1[1] + sq2[0] == 10) {
					Node n = new Node();
					n.fixed.add(sq1); n.fixed.add(sq2);
					for (int[] sq : squares) {
						if (sq != sq1 && sq != sq2) {
							n.free.add(sq);
						}
					}
					promisingNodesList1.add(n);
				}
			}
		}
		// all possible squares, that can be at position 4 and 5
		ArrayList<Node> promisingNodesList2 = new ArrayList<Node>();
		for (Node n : promisingNodesList1) {
			for (int[] sq4 : n.free) {
				for (int[] sq5 : n.free) {
					int[] sq1 = n.fixed.get(0);
					int[] sq2 = n.fixed.get(1);
					if (	sq4 != sq5 &&
							sq1[3] + sq2[2] + sq4[1] + sq5[0] == 10 &&
							sq1[2] + sq4[0] <= 10 && sq2[3] + sq5[1] <= 10) {
						Node square = new Node();
						square.fixed.addAll(n.fixed);
						square.fixed.add(sq4);
						square.fixed.add(sq5);
						for (int[] sq : n.free) {
							if (sq != sq4 && sq != sq5) {
								square.free.add(sq);
							}
						}
						promisingNodesList2.add(square);
					}
				}
			}
		}
		promisingNodesList1.clear();
		// searching for square to position 3
		for (Node n : promisingNodesList2) {
			for (int[] sq3 : n.free) {
				int[] sq1 = n.fixed.get(0);
				int[] sq4 = n.fixed.get(2);
				if (	sq1[2] + sq3[1] + sq4[0] <= 10 &&
						sq3[3] + sq4[2] <= 10) {
					Node square = new Node();
					square.fixed.addAll(n.fixed);
					square.fixed.add(2, sq3); // insert to 3rd position
					for (int[] sq : n.free) {
						if (sq != sq3) {
							square.free.add(sq);
						}
					}
					promisingNodesList1.add(square);
				}
			}
		}
		promisingNodesList2.clear();
		// searching for square to position 6
		for (Node n : promisingNodesList1) {
			for (int[] sq6 : n.free) {
				int[] sq2 = n.fixed.get(1);
				int[] sq5 = n.fixed.get(4);
				if (	sq2[3] + sq5[1] + sq6[0] <= 10 &&
						sq5[3] + sq6[2] <= 10) {
					Node square = new Node();
					square.fixed.addAll(n.fixed);
					square.fixed.add(sq6); // insert to 3rd position
					for (int[] sq : n.free) {
						if (sq != sq6) {
							square.free.add(sq);
						}
					}
					promisingNodesList2.add(square);
				}
			}
		}
		promisingNodesList1.clear();
		// searching for square to position 7
		for (Node n : promisingNodesList2) {
			for (int[] sq7 : n.free) {
				int[] sq3 = n.fixed.get(2);
				int[] sq4 = n.fixed.get(3);
				if (	sq3[2] + sq7[0] <= 10 &&
						sq3[3] + sq4[2] + sq7[1] <= 10) {
					Node square = new Node();
					square.fixed.addAll(n.fixed);
					square.fixed.add(sq7);
					for (int[] sq : n.free) {
						if (sq != sq7) {
							square.free.add(sq);
						}
					}
					promisingNodesList1.add(square);
				}
			}
		}
		promisingNodesList2.clear();
		// sq8
		for (Node n : promisingNodesList1) {
			for (int[] sq8 : n.free) {
				int[] sq3 = n.fixed.get(2);
				int[] sq4 = n.fixed.get(3);
				int[] sq7 = n.fixed.get(6);
				if (	sq3[3] + sq4[2] + sq7[1] + sq8[0] == 10 &&
						sq4[3] + sq8[1] <= 10 &&
						sq7[3] + sq8[2] <= 10)  {
					Node square = new Node();
					square.fixed.addAll(n.fixed);
					square.fixed.add(sq8);
					for (int[] sq : n.free) {
						if (sq != sq8) {
							square.free.add(sq);
						}
					}
					promisingNodesList2.add(square);
				}
			}
		}
		promisingNodesList1.clear();
		// sq9
		for (Node n : promisingNodesList2) {
			for (int[] sq9 : n.free) {
				int[] sq4 = n.fixed.get(3);
				int[] sq5 = n.fixed.get(4);
				int[] sq8 = n.fixed.get(7);
				if (	sq4[3] + sq5[2] + sq8[1] + sq9[0] == 10 &&
						sq5[3] + sq9[1] <= 10 &&
						sq8[3] + sq9[2] <= 10)  {
					Node square = new Node();
					square.fixed.addAll(n.fixed);
					square.fixed.add(sq9);
					for (int[] sq : n.free) {
						if (sq != sq9) {
							square.free.add(sq);
						}
					}
					promisingNodesList1.add(square);
				}
			}
		}
		promisingNodesList2.clear();
		// sq10
		for (Node n : promisingNodesList1) {
			for (int[] sq10 : n.free) {
				int[] sq5 = n.fixed.get(4);
				int[] sq6 = n.fixed.get(5);
				int[] sq9 = n.fixed.get(8);
				if (	sq5[3] + sq6[2] + sq9[1] + sq10[0] == 10 &&
						sq6[3] + sq10[1] <= 10 &&
						sq9[3] + sq10[2] <= 10)  {
					Node square = new Node();
					square.fixed.addAll(n.fixed);
					square.fixed.add(sq10);
					for (int[] sq : n.free) {
						if (sq != sq10) {
							square.free.add(sq);
						}
					}
					promisingNodesList2.add(square);
				}
			}
		}
		promisingNodesList1.clear();
		// sq11 and 12
		for (Node n : promisingNodesList2) {
			int[] sq7 = n.fixed.get(6);
			int[] sq8 = n.fixed.get(7);
			int[] sq9 = n.fixed.get(8);
			int[] sq10 = n.fixed.get(9);
			int[] sq11 = n.free.get(0);
			int[] sq12 = n.free.get(1);
			Node node = new Node();
			node.fixed.addAll(n.fixed);
			if (	sq8[3] + sq9[2] + sq11[1] + sq12[0] == 10 &&
					sq7[3] + sq8[2] + sq11[0] <= 10 &&
					sq9[3] + sq10[2] + sq12[2] <= 10 &&
					sq11[3] + sq12[2] <= 10) {
				Node answer = new Node();
				answer.fixed.addAll(n.fixed);
				answer.fixed.add(sq11);
				answer.fixed.add(sq12);
				answers.add(answer);
			}
			if (	sq8[3] + sq9[2] + sq12[1] + sq11[0] == 10 &&
					sq7[3] + sq8[2] + sq12[0] <= 10 &&
					sq9[3] + sq10[2] + sq11[2] <= 10 &&
					sq12[3] + sq11[2] <= 10) {
				Node answer = new Node();
				answer.fixed.addAll(n.fixed);
				answer.fixed.add(sq12);
				answer.fixed.add(sq11);
				answers.add(answer);
			}
		}
	}
	/**
	 * @return true if there is one solution at least
	 */
	public boolean run() {
		boolean run = false;
		calculate();
		for (Node n : answers) {
			if (n.isValid()) {
				run = true;
				n.print();
			}
		} if(!run) System.err.println("No solutions!");
		return run;
	}
}