import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF629B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int[] m = new int[400];
		int[] f = new int[400];
		for (int i=0; i<n; i++) {
			if ("M".equals(in.next())) {
				m[in.nextInt()]++;
				m[in.nextInt()+1]--;
			} else {
				f[in.nextInt()]++;
				f[in.nextInt()+1]--;
			}
		}

		for (int i=1; i<400; i++) {
			m[i] += m[i-1];
			f[i] += f[i-1];
		}

		dump(m,f);

		int max = 0;
		for (int i=0; i<400; i++) {
			max = Math.max(max, Math.min(m[i],f[i])*2);
		}

		out.println(max);
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