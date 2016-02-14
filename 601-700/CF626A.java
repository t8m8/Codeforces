import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF626A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		String c = in.next();
		int cnt = 0;
		for (int i=0; i<n; i++) {
			for (int j=1; i+j<=n; j++) {
				String s = c.substring(i, i+j);
				int m = s.length();
				int x = 0, y = 0;
				for (int k=0; k<m; k++) {
					if (s.charAt(k) == 'U') {
						y++;
					} else if (s.charAt(k) == 'D') {
						y--;
					} else if (s.charAt(k) == 'R') {
						x++;
					} else if (s.charAt(k) == 'L') {
						x--;
					}
				}
				if (x == 0 && y == 0) {
					cnt++;
				}
			}
		}

		out.println(cnt);
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