import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF741B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		int w = in.nextInt();
		int[] ws = new int[n];
		int[] bs = new int[n];
		for (int i=0; i<n; i++) {
		 	ws[i] = in.nextInt();
		}
		for (int i=0; i<n; i++) {
			bs[i] = in.nextInt();
		}

		DisjointSet uf = new DisjointSet(n);

		for (int i=0; i<m; i++) {
			int x = in.nextInt() - 1;
			int y = in.nextInt() - 1;
			uf.unite(x, y);
		}

		HashSet<Integer> set = new HashSet<>();
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		for (int i=0; i<n; i++) {
			set.add(uf.find(i));
			ArrayList<Integer> list = map.getOrDefault(uf.find(i), new ArrayList<Integer>());
			list.add(i);
			map.put(uf.find(i), list);
		}
		int size = set.size();
		int pos = 0;
		int[] ids = new int[size];
		int[] wsum = new int[size];
		int[] bsum = new int[size];
		for (Integer key : map.keySet()) {
			ids[pos] = key;

			for (Integer x : map.get(key)) {
				wsum[pos] += ws[x];
				bsum[pos] += bs[x];
			}

			pos++;
		}

		long[][] dp = new long[size+1][w+1];
		for (int i=0; i<size; i++) {
			for (int j=0; j<=w; j++) {
				dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
				if (j + wsum[i] <= w) {
					dp[i+1][j+wsum[i]] = Math.max(dp[i+1][j+wsum[i]], dp[i][j] + bsum[i]);
				}
				for (Integer k : map.get(ids[i])) {
					if (j + ws[k] <= w) {
						dp[i+1][j+ws[k]] = Math.max(dp[i+1][j+ws[k]], dp[i][j] + bs[k]);
					}
				}
			}
		}

		long max = 0;
		for (int i=0; i<=w; i++) {
			max = Math.max(max, dp[size][i]);
		}

		// dump(dp);

		out.println(max);
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

class DisjointSet {

	private int[] par, cnt;

	public DisjointSet(int n){
		par = new int[n];
		cnt = new int[n];
		for (int i=0; i<n; i++) {
			par[i] = i;
			cnt[i] = 1;
		}
	}

	public int find(int x){
		return par[x] == x ? x : (par[x] = find(par[x]));
	}

	public boolean same(int x, int y){
		return find(x) == find(y);
	}

	public void unite(int x, int y){
		x = find(x); y = find(y);
		if (x == y) return;
		cnt[x] = cnt[y] = cnt[x] + cnt[y];
		par[x] = y;
	}

	public int size(int x) {
		return cnt[find(x)];
	}

	public String toString() {
		return Arrays.toString(par);
	}
}