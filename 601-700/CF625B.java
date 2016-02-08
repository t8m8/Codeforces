import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF625B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		String s = in.next();
		String t = in.next();
		int n = s.length();
		int m = t.length();
		int cnt = 0;
		for (int i=0; i<n; i++) {
			boolean f = true;
			for (int j=0; j<m; j++) {
				if (i + j == n || s.charAt(i+j) != t.charAt(j)) {
					f = false;
					break;
				}
			}
			if (f) {
				cnt++;
				i += m - 1;
			}
		}
		out.println(cnt);
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		solve();
		out.flush();

		long end = System.currentTimeMillis();
		//trace(end-start + "ms");
		in.close();
		out.close();
	}

	static void trace(Object... o) { System.out.println(Arrays.deepToString(o));}
}