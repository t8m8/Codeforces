import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF673B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		int max = 0, min = n+1;
		for (int i=0; i<m; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int div2 = Math.min(u, v);
			int div1 = Math.max(u, v);
			max = Math.max(max, div2);
			min = Math.min(min, div1);
		}

		if (max >= min) {
			out.println("0");
			return;
		}

		int d = min - max;
		if (max == 0) d--;
		if (min == n+1) d--;
		out.println(d);
	}

	public static void main(String[] args) {
		debug = args.length > 0;
		long start = System.currentTimeMillis();

		solve();
		out.flush();

		long end = System.currentTimeMillis();
		dump((end-start) + "ms");
		in.close();
		out.close();
	}

	static void dump(Object... o) { if (debug) System.err.println(Arrays.deepToString(o)); }
}