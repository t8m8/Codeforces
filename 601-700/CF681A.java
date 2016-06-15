import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF681A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		boolean f = false;
		for (int i=0; i<n; i++) {
			String s = in.next();
			int a = in.nextInt();
			int b = in.nextInt();
			if (a >= 2400 && a < b) {
				f = true;
			}
		}
		out.println(f ? "YES" : "NO");
	}

	public static void main(String[] args) {
		debug = args.length > 0;
		long start = System.nanoTime();

		solve();
		out.flush();

		long end = System.nanoTime();
		dump((end - start) / 1000000 + " ms");
		in.close();
		out.close();
	}

	static void dump(Object... o) { if (debug) System.err.println(Arrays.deepToString(o)); }
}