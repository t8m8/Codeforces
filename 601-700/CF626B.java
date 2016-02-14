import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF626B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int n, r = 0, g = 0, b = 0;
	static int[][][] dp;

	static int rec(int x, int y, int z) {
		if (dp[x][y][z] > 0) return dp[x][y][z];

		if (x + y + z == n) {
			if (x == r && y == g && z == b) return dp[x][y][z] = 2;
			return dp[x][y][z] = 1;
		}

		if (x > 0 && rec(x+1, y, z) > 1) {
			return dp[x][y][z] = 2;
		} else if (y > 0 && rec(x, y+1, z) > 1) {
			return dp[x][y][z] = 2;
		} else if (z > 0 && rec(x, y, z+1) > 1) {
			return dp[x][y][z] = 2;
		} else if (x > 0 && rec(x-1, y+1, z+1) > 1) {
			return dp[x][y][z] = 2;
		} else if (y > 0 && rec(x+1, y-1, z+1) > 1) {
			return dp[x][y][z] = 2;
		} else if (z > 0 && rec(x+1, y+1, z-1) > 1) {
			return dp[x][y][z] = 2;
		}

		return dp[x][y][z] = 1;
	}

	static void solve() {
		n = in.nextInt();
		String s = in.next();

		for (int i=0; i<n; i++) {
			if (s.charAt(i) == 'R') {
				r++;
			} else if (s.charAt(i) == 'G') {
				g++;
			} else {
				b++;
			}
		}

		dp = new int[201][201][201];
		boolean x = rec(1, 0, 0) > 1;
		dp = new int[201][201][201];
		boolean y = rec(0, 1, 0) > 1;
		dp = new int[201][201][201];
		boolean z = rec(0, 0, 1) > 1;

		out.println((z ? "B" : "") + (y ? "G" : "") + (x ? "R" : ""));
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