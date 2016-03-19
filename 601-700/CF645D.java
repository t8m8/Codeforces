import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF645D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int[] dp;
	static DirectedGraph g;

	static int rec(int cur, int m) {
		if (dp[cur] != 0) return dp[cur];

		int max = 0;
		for (int[] to : g.get(cur)) {
			if (to[1] > m) continue;
			max = Math.max(max, rec(to[0], m));
		}

		return dp[cur] = max + 1;
	}

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();

		g = new DirectedGraph(n);
		for (int i=0; i<m; i++) {
			int s = in.nextInt() - 1;
			int t = in.nextInt() - 1;
			g.addArc(s,t,i);
		}

		int l = -1, r = m;
		while (r - l > 1) {
			dp = new int[n];
			int mid = (l + r)>>1;
			int max = 0;
			for (int i=0; i<n; i++) {
				if (dp[i] == 0)
					max = Math.max(max, rec(i, mid));
			}
			if (max == n) r = mid;
			else l = mid;
		}

		dp = new int[n];
		int max = 0;
		for (int i=0; i<n; i++) {
			if (dp[i] == 0)
				max = Math.max(max, rec(i, r));
		}

		out.println(max == n ? (r+1) : "-1");
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

class DirectedGraph {

	public static final int INF = Integer.MAX_VALUE/2;

	public final int n;
	public final ArrayList<ArrayList<int[]>> arcs;

	public DirectedGraph(int n) {
		this.n = n;
		this.arcs = new ArrayList<ArrayList<int[]>>();
		for (int i=0; i<n; i++) arcs.add(new ArrayList<int[]>());
	}

	public void addArc(int from, int to) {
		addArc(from,to,0);
	}

	public void addArc(int from, int to, int c) {
		arcs.get(from).add(new int[]{to,c});
	}

	public ArrayList<int[]> get(int v) {
		return arcs.get(v);
	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		for (int i=0; i<n; i++) {
			res.append(i).append(" ").append(Arrays.deepToString(arcs.get(i).toArray())).append("\n");
		}
		return res.substring(0,res.length()-1);
	}
}
