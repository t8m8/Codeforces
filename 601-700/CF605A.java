import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF605A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();
		int[] p = new int[n];
		int[] pos = new int[n];
		for (int i=0; i<n; i++) {
			p[i] = in.nextInt() - 1;
			pos[p[i]] = i;
		}

		int[] dp = new int[n];
		for (int i=0; i<n; i++) {
			dp[i] = i;
		}

		for (int i=n-2; i>=0; i--) {
			if (pos[i] < pos[i+1]) dp[i] = dp[i+1];
		}

		int ans = n;
		for (int i=0; i<n; i++) {
			ans = Math.min(ans, n-(dp[i]-i+1));
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