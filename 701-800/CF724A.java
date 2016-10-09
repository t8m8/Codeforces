import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF724A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};

	static void solve() {
		String[] s = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
		String a = in.next();
		String b = in.next();

		for (int k=0; k<7; k++) {
			int x = k;
			int[] c = new int[12];
			for (int i=0; i<12; i++) {
				c[i] = x%7;
				x += days[i];
			}
			for (int i=0; i<12; i++) {
				if (a.equals(s[c[i]]) && b.equals(s[c[(i+1)%12]])) {
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