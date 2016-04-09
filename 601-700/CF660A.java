import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF660A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int gcd(int a, int b) {
		return a == 0 ? b : gcd(b%a, a);
	}

	static void solve() {
		int n = in.nextInt();
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0; i<n; i++) {
			list.add(in.nextInt());
		}

		ArrayList<Integer> ans = new ArrayList<>();
		ans.add(list.get(0));
		for (int i=1; i<n; i++) {
			int s = ans.get(ans.size()-1);
			int t = list.get(i);
			if (gcd(s, t) != 1) {
				ans.add(gcd(s,1000009) == 1 && gcd(t, 1000009) == 1 ? 1000009 : (gcd(s,1000007) == 1 && gcd(t, 1000007) == 1 ? 1000007 : 119891));
			}
			ans.add(t);
		}

		dump(ans);

		out.println(ans.size() - n);
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