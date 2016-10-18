import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF732B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int k = in.nextInt();
		int[] a = new int[n];
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		int[] b = Arrays.copyOfRange(a, 0, n);
		int cnt = 0;
		for (int i=0; i<n-1; i++) {
			if (b[i] + b[i+1] < k) {
				cnt += k - (b[i] + b[i+1]);
				b[i+1] += k - (b[i] + b[i+1]);
			}
		}

		int[] b2 = Arrays.copyOfRange(a, 0, n);
		int cnt2 = 0;
		Arrays.copyOfRange(b2, 0, n);
		for (int i=0; i<n-1; i++) {
			if (b2[i] + b2[i+1] < k) {
				cnt2 += k - (b2[i] + b2[i+1]);
				b2[i+1] += k - (b2[i] + b2[i+1]);
			}
		}

		if (cnt < cnt2) {
			out.println(cnt);
			for (int i=0; i<n-1; i++) {
				out.print(b[i]+" ");
			}
			out.println(b[n-1]);
		} else {
			out.println(cnt2);
			for (int i=0; i<n-1; i++) {
				out.print(b2[i]+" ");
			}
			out.println(b2[n-1]);
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