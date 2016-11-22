import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF738A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		String s = in.next();
		StringBuilder ans = new StringBuilder();
		for (int i=0; i<n; i++) {
			if (i+3 <= n && s.substring(i, i+3).equals("ogo")) {
				ans.append("***");
				int p = i+3;
				while (p+2 <= n && s.substring(p, p+2).equals("go")) {
					p += 2;
				}
				i = p - 1;
			} else {
				ans.append(s.charAt(i));
			}
		}
		out.println(ans);
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