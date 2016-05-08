import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF673D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int k = in.nextInt();
		int[] a = new int[4];
		for (int i=0; i<4; i++) {
			a[i] = in.nextInt();
		}
		if (k - n < 1 || n == 4) {
			out.println("-1");
			return;
		}

		ArrayList<Integer> list = new ArrayList<>();
		for (int i=1; i<=n; i++) {
			boolean f = true;
			for (int j=0; j<4; j++) {
				if (a[j] == i) f = false;
			}
			if (f) {
				list.add(i);
			}
		}

		out.print(a[0]+" "+a[2]+" ");
		for (int i : list) {
			out.print(i+" ");
		}
		out.println(a[3]+" "+a[1]);

		out.print(a[2]+" "+a[0]+" ");
		for (int i : list) {
			out.print(i+" ");
		}
		out.println(a[1]+" "+a[3]);
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