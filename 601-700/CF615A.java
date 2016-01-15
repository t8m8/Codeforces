import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF615A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		boolean[] p = new boolean[m];
		for (int i=0; i<n; i++) {
			int x = in.nextInt();
			for (int j=0; j<x; j++) {
				p[in.nextInt()-1] = true;
			}
		}

		for (int i=0; i<m; i++) {
			if (!p[i]) {
				out.println("NO");
				return;
			}
		}

		out.println("YES");
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