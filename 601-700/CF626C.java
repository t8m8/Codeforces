import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF626C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		int a = 2*n;
		int b = 3*m;
		for (int i=6; i<=a && i<=b; i+=6) {
			if (a > b) b += 3;
			else a += 2;
		}
		out.println(Math.max(a, b));
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