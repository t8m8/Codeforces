import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF645C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int k = in.nextInt();
		String s = in.next();

		int[] sum = new int[n+1];
		for (int i=0; i<n; i++) {
			if (s.charAt(i) == '0') {
				sum[i+1]++;
			}
			sum[i+1] += sum[i];
		}

		int ans = 1<<29;

		dump(sum);

		for (int p=0; p<n; p++) {
			if (s.charAt(p) != '0') continue;
			int l = -1, r = n;
			while (r - l > 1) {
				int m = (l + r) >> 1;
				int a = Math.max(0, p-m);
				int b = Math.min(n-1, p+m);
				int cnt = sum[b+1] - sum[a];
				if (cnt >= k+1) {
					r = m;
				} else {
					l = m;
				}
			}
			ans = Math.min(ans, r);
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