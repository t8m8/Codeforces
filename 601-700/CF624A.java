import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF624A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		double d = in.nextInt();
		double l = in.nextInt();
		double v1 = in.nextDouble();
		double v2 = in.nextDouble();

		out.printf("%.20f\n", (l - d)/(v1 + v2));
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