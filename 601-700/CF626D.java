import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF626D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int[] a = new int[n];
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}

		ArrayList<Integer> diff = new ArrayList<>();
		int[] _cnt = new int[5000];
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				diff.add(Math.abs(a[i] - a[j]));
				_cnt[Math.abs(a[i] - a[j])]++;
			}
		}

		double[] cnt = new double[20000];
		for (int i=0; i<5000; i++) {
			for (int j=0; j<5000; j++) {
				cnt[i+j] += _cnt[i] * _cnt[j];
			}
		}

		for (int i=1; i<20000; i++) {
			cnt[i] += cnt[i-1];
		}

		int m = diff.size();
		double ans = 0;
		for (int i=0; i<m; i++) {
			ans += cnt[diff.get(i)-1]/n/n/n;
		}

		out.printf("%.20f\n", ans/(n-1)/(n-1)/(n-1)*2*2*2);
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