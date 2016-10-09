import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF724B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] t = new int[n][m];
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				t[i][j] = in.nextInt();
			}
		}

		int[][] s = new int[n][m];

		for (int i=0; i<m; i++) {
			for (int j=i+1; j<m; j++) {
				for (int k=0; k<n; k++) {
					for (int l=0; l<m; l++) {
						if (l == i) {
							s[k][l] = t[k][j];
						} else if (l == j) {
							s[k][l] = t[k][i];
						} else {
							s[k][l] = t[k][l];
						}
					}
				}

				boolean f = true;

				for (int k=0; k<n; k++) {
					int cnt = 0;
					for (int l=0; l<m; l++) {
						if (s[k][l] != l+1) {
							cnt++;
						}
					}
					if (cnt > 2) {
						f = false;
					}
				}

				if (f) {
					out.println("YES");
					return;
				}
			}
		}

		boolean f = true;

		for (int k=0; k<n; k++) {
			int cnt = 0;
			for (int l=0; l<m; l++) {
				if (t[k][l] != l+1) {
					cnt++;
				}
			}
			if (cnt > 2) {
				f = false;
			}
		}

		if (f) {
			out.println("YES");
			return;
		}

		out.println("NO");
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