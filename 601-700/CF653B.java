import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF653B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static ArrayList<StringBuilder>[] list;
	static HashSet<String> set = new HashSet<>();
	static int n;

	static void rec(StringBuilder s) {
		int m = s.length();
		if (m == n) {
			set.add(s.toString());
			return;
		}

		for (StringBuilder sb : list[s.charAt(0)-'a']) {
			StringBuilder tmp = new StringBuilder(s);
			tmp.deleteCharAt(0);
			tmp.insert(0, sb);
			rec(tmp);
		}
	}

	static void solve() {
		n = in.nextInt();
		int q = in.nextInt();
		list = new ArrayList[6];
		for (int i=0; i<6; i++) {
			list[i] = new ArrayList<StringBuilder>();
		}
		for (int i=0; i<q; i++) {
			StringBuilder sb = new StringBuilder(in.next());
			int p = in.next().charAt(0) - 'a';
			list[p].add(sb);
		}
		rec(new StringBuilder("a"));
		out.println(set.size());
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