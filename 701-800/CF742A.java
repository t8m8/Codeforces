import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF742A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int[] ans = {8, 4, 2, 6};

	static void solve() {
		int n = in.nextInt();

		if (n == 0) out.println(1);
		else
			out.println(ans[(n-1)%4]);
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