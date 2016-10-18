import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF732A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int k = in.nextInt();
		int r = in.nextInt();
		for (int i=1; i<=10; i++) {
			int x = k*i;
			if (x%10 == 0 || x%10 == r) {
				out.println(i);
				return;
			}
		}
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