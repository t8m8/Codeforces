import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF610C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static ArrayList<ArrayList<Character>> ans;

	static void rec(int k, int p, boolean isMinus) {
		if (k == 1) {
			ans.get(p).add(isMinus ? '*' : '+');
			return;
		}

		rec(k/2, p, isMinus);
		rec(k/2, p+k/2, isMinus);
		rec(k/2, p, isMinus);
		rec(k/2, p+k/2, !isMinus);
	}


	static void solve() {
		int k = in.nextInt();
		int d = (int)Math.pow(2,k);
		ans = new ArrayList<ArrayList<Character>>();
		for (int i=0; i<d; i++) {
			ans.add(new ArrayList<Character>());
		}

		rec(d,0,false);
		for (ArrayList<Character> i : ans) {
			for (Character j : i) {
				out.print(j);
			}
			out.println();
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		solve();
		out.flush();

		long end = System.currentTimeMillis();
		//trace(end-start + "ms");
		in.close();
		out.close();
	}

	static void trace(Object... o) { System.out.println(Arrays.deepToString(o));}
}