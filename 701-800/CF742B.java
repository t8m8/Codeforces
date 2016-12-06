import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF742B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int x = in.nextInt();
		int[] a = new int[n];

		HashMap<Integer,Integer> cnt = new HashMap<>();
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
			cnt.put(a[i], cnt.getOrDefault(a[i], 0)+1);
		}

		if (x == 0) {
			long ans = 0;
			for (Integer key : cnt.keySet()) {
				ans += (long)cnt.get(key)*(cnt.get(key) - 1)/2;
			}
			out.println(ans);
		}
		else {
			long ans = 0;
			for (int i=0; i<n; i++) {
				int y = x ^ a[i];
				ans += cnt.getOrDefault(y, 0);
			}
			out.println(ans/2);
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