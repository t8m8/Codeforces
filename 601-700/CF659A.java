import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF659A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		if (b > 0) {
			for (int i=0; i<b; i++) {
				a++;
				if (a == n+1) a = 1;
			}
			out.println(a);
		} else {
			b = -b;
			for (int i=0; i<b; i++) {
				a--;
				if (a == 0) a = n;
			}
			out.println(a);
		}
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