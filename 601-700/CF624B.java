import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF624B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		HashSet<Integer> set = new HashSet<>();
		long ans = 0;
		for (int i=0; i<n; i++) {
			int a = in.nextInt();
			while (a > 0 && set.contains(a)) {
				a--;
			}
			ans += a;
			set.add(a);
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