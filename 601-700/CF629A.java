import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF629A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		char[][] t = new char[n][];
		for (int i=0; i<n; i++) {
			t[i] = in.next().toCharArray();
		}

		int[] r = new int[n];
		int[] c = new int[n];
		long ans = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (t[i][j] == 'C') r[i]++;
				if (t[j][i] == 'C') c[i]++;
			}
			ans += r[i]*(r[i]-1)/2;
			ans += c[i]*(c[i]-1)/2;
		}
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