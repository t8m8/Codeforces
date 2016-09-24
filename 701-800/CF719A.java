import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF719A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int[] a = new int[n];
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		if (n == 1) {
			if (a[0] == 0) {
				out.println("UP");
			} else if (a[0] == 15) {
				out.println("DOWN");
			} else {
				out.println("-1");
			}
		} else {
			if (a[n-1] == 0) {
				out.println("UP");
			} else if (a[n-1] == 15) {
				out.println("DOWN");
			} else {
				out.println(a[n-2] < a[n-1] ? "UP" : "DOWN");
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