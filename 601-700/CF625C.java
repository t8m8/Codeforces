import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF625C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();
		int k = in.nextInt() - 1;
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

		int p = 1;
		for (int i=0; i<n; i++) {
			ans.add(new ArrayList<Integer>());
			for (int j=0; j<k; j++) {
				ans.get(i).add(p++);
			}
		}

		int x = 0;
		for (int i=0; i<n; i++) {
			ArrayList<Integer> list = ans.get(i);
			while (list.size() < n) {
				list.add(p++);
			}
			x += list.get(k);
		}

		out.println(x);
		for (ArrayList<Integer> list : ans) {
			for (int i=0; i<list.size()-1; i++) {
				out.print(list.get(i)+" ");
			}
			out.println(list.get(list.size()-1));
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