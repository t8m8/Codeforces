import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF653A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		boolean[] f = new boolean[10000];
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0; i<n; i++) {
			int a = in.nextInt();
			if (!f[a]) {
				list.add(a);
			}
			f[a] = true;
		}
		Collections.sort(list);
		int m = list.size();
		for (int i=0; i+3<=m; i++) {
			if (list.get(i) + 1 == list.get(i+1) && list.get(i) + 2 == list.get(i+2)) {
				out.println("YES");
				return;
			}
		}
		out.println("NO");
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