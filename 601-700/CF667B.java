import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF667B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int[] a = new int[n];
		long sum = 0;
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
			sum += a[i];
		}
		Arrays.sort(a);
		long t = sum - a[n-1];
		long ans = a[n-1] - t + 1;
		out.println(ans);
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