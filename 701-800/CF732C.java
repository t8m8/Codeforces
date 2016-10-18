import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF732C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		long a = in.nextLong();
		long b = in.nextLong();
		long c = in.nextLong();
		long ans = Long.MAX_VALUE;
		for (int i=1; i<(1<<3); i++) {
			long max = Math.max(a, Math.max(b, c));

			long p = Math.max(a, (i>>2&1) == 1 ? max : (max - 1));
			long q = Math.max(b, (i>>1&1) == 1 ? max : (max - 1));
			long r = Math.max(c, (i&1) == 1 ? max : (max - 1));

			long tp = p - a;
			long tq = q - b;
			long tr = r - c;
			ans = Math.min(ans, tp + tq + tr);
		}
		out.println(ans);
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