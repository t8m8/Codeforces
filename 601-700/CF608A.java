import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF608A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();
		int s = in.nextInt();
		int[][] p = new int[n][2];
		for (int i=0; i<n; i++) {
			for (int j=0; j<2; j++) {
				p[i][j] = in.nextInt();
			}
		}

		Arrays.sort(p, new Comparator<int[]>(){
			public int compare(int[] a, int[] b) {
				if (a[0] != b[0]) return b[0] - a[0];
				return a[1] - b[1];
			}
		});

		// trace(p);

		int cnt = 0, f = s;
		for (int i=0; i<n; i++) {
			cnt += f - p[i][0];
			f = p[i][0];
			if (cnt < p[i][1]) cnt = p[i][1];
		}

		cnt += f;
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