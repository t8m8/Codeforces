import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF631A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		long a = 0;
		long b = 0;
		for (int i=0; i<n; i++) {
			a |= in.nextInt();
		}
		for (int i=0; i<n; i++) {
			b |= in.nextInt();
		}
		out.println((a+b));
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