import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF609A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		int[] a = new int[n];
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		Arrays.sort(a);
		for (int i=n-1,j=1; i>=0; i--,j++) {
			m -= a[i];
			if (m <= 0) {
				out.println(j);
				return;
			}
		}
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