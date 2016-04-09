import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF660B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		ArrayDeque[] que = new ArrayDeque[4];
		for (int i=0; i<4; i++) {
			que[i] = new ArrayDeque<Integer>();
		}

		int pos = 0;
		for (int i=1; i<=m; i++) {
			que[pos].addFirst(i);
			pos = (pos+1)%2 + (que[0].size() == n && que[1].size() == n ? 2 : 0);
		}

		for (int i=0; i<4; i++) {
			dump(que[i]);
		}

		int[] p = {2, 0, 3, 1};
		pos = 0;
		ArrayList<Integer> ans = new ArrayList<>();
		for (int i=0; i<4*n; i++) {
			if (que[p[pos]].size() == 0) {
				pos = (pos+1)%4;
				continue;
			}
			ans.add((Integer)que[p[pos]].pollLast());
			pos = (pos+1)%4;
		}

		for (int i=0; i<ans.size()-1; i++) {
			out.print(ans.get(i)+" ");
		}
		out.println(ans.get(ans.size()-1));
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