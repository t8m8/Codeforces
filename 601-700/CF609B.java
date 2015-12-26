import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF609B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		int[] cnt = new int[m+1];
		for (int i=0; i<n; i++) {
			int a = in.nextInt();
			cnt[a]++;
		}

		int ans = 0;

		for (int i=0; i<1<<m; i++) {
			if (Integer.bitCount(i) != 2) continue;
			int t = 1;
			for (int j=0; j<m; j++) {
				if ((i>>j&1) == 1) {
					t *= cnt[j+1];
				}
			}
			ans += t;
		}

		out.println(ans);
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