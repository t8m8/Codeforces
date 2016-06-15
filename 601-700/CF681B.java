import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF681B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int x = 1234567;
		int y = 123456;
		int z = 1234;

		for (long i=0; x*i <= n; i++) {
			for (long j=0; y*j <= n - x*i; j++) {
				if ((n - x*i - y*j)%z == 0) {
					out.println("YES");
					return;
				}
			}
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