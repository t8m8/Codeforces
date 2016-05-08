import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF673A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int[] t = new int[n];
		for (int i=0; i<n; i++) {
			t[i] = in.nextInt();
		}
		int i = 0, pos = 0, last = 0;
		for (; i<=90; i++) {
			if (last + 15 < i) {
				break;
			}
			if (pos < n && t[pos] == i) {
				last = t[pos];
				pos++;
			}

			
		}
		out.println(i-1);
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