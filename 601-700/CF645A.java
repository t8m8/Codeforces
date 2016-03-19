import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF645A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		String[] s = new String[4];
		for (int i=0; i<4; i++) {
			s[i] = in.next();
		}

		String x = "", y = "";
		if (s[0].charAt(0) != 'X') {
			x += s[0].charAt(0);
		}
		if (s[0].charAt(1) != 'X') {
			x += s[0].charAt(1);
		}
		if (s[1].charAt(1) != 'X') {
			x += s[1].charAt(1);
		}
		if (s[1].charAt(0) != 'X') {
			x += s[1].charAt(0);
		}

		if (s[2].charAt(0) != 'X') {
			y += s[2].charAt(0);
		}
		if (s[2].charAt(1) != 'X') {
			y += s[2].charAt(1);
		}
		if (s[3].charAt(1) != 'X') {
			y += s[3].charAt(1);
		}
		if (s[3].charAt(0) != 'X') {
			y += s[3].charAt(0);
		}

		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				boolean f = true;
				for (int k=0; k<3; k++) {
					if (x.charAt((i+k)%3) != y.charAt((j+k)%3)) {
						f = false;
					}
				}
				if (f) {
					out.println("YES");
					return;
				}
			}
		}


		out.println("NO");
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