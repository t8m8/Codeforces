import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF645B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int k = in.nextInt();
		int t = k;
		long ans = 0;
		for (int i=1; i<=n; i++, t--) {
			if (n - k <= i) {
				ans += n - i;
			} else if (t > 0) {
				ans += n - i;
			} else {
				ans += k;
			}
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