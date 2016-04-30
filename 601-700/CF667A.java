import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF667A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		double d = in.nextInt();
		double h = in.nextInt();
		double v = in.nextInt();
		double e = in.nextInt();

		double r = d/2;
		double s = r*r*Math.PI;
		double x = s*h;
		if (v <= e*s) {
			out.println("NO");
		} else {
			out.println("YES");
			out.printf("%.10f\n", x/(v-e*s));
		}
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