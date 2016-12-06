import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF741A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static boolean[] visited;
	static int n;
	static int[] a;

	static int rec(int cur) {
		if (visited[cur]) return 0;
		visited[cur] = true;
		return rec(a[cur]) + 1;
	}

	static long gcd(long a, long b) {
		if (a == 0) return b;
		return gcd(b%a, a);
	}

	static void solve() {
		n = in.nextInt();
		a = new int[n];
		boolean[] f = new boolean[n];
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt() - 1;
			if (f[a[i]]) {
				out.println("-1");
				return;
			}
			f[a[i]] = true;
		}
		visited = new boolean[n];
		long ans = 1;
		for (int i=0; i<n; i++) {
			if (!visited[i]) {
				int tmp = rec(i);
				tmp = tmp%2 == 0 ? tmp/2 : tmp;
				ans = ans*tmp / gcd(ans, tmp);
			}
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