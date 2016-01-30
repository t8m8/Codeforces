import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF618B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();
		int[][] a = new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				a[i][j] = in.nextInt();
			}
		}

		int[] ans = new int[n];

		for (int k=1; k<=n; k++) {
			boolean ff = true;
			for (int i=0; i<n; i++) {
				if (ans[i] != 0) continue;

				boolean f = true;
				for (int j=0; j<n; j++) {
					if (i == j || a[i][j] < k) continue;
					if (a[i][j] != k) f = false;
				}

				for (int j=0; j<n; j++) {
					if (i == j || a[j][i] < k) continue;
					if (a[j][i] != k) f = false;
				}

				if (f) {
					ans[i] = k;
					ff = false;
					break;
				}
			}

			if (ff) {
				for (int i=0; i<n; i++) {
					if (ans[i] == 0) {
						ans[i] = k;
						break;
					}
				}
			}
		}

		for (int i=0; i<n-1; i++) {
			out.print(ans[i] + " ");
		}
		out.println(ans[n-1]);
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		solve();
		out.flush();

		long end = System.currentTimeMillis();
		//trace(end-start + "ms");
		in.close();
		out.close();
	}

	static void trace(Object... o) { System.out.println(Arrays.deepToString(o));}
}