import java.util.*;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF812A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int[] l = new int[4];
		int[] s = new int[4];
		int[] r = new int[4];
		int[] p = new int[4];

		for (int i=0; i<4; i++) {
			l[i] = in.nextInt();
			s[i] = in.nextInt();
			r[i] = in.nextInt();
			p[i] = in.nextInt();

			if (p[i] == 1 && (l[i] + s[i] + r[i] > 0)) {
				out.println("YES");
				return;
			}
		}

		if (p[0] == 1 && (l[1] + s[2] + r[3] > 0)) {
			out.println("YES");
		} else if (p[1] == 1 && (l[2] + s[3] + r[0] > 0)) {
			out.println("YES");
		} else if (p[2] == 1 && (l[3] + s[0] + r[1] > 0)) {
			out.println("YES");
		} else if (p[3] == 1 && (l[0] + s[1] + r[2] > 0)) {
			out.println("YES");
		} else {
			out.println("NO");
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