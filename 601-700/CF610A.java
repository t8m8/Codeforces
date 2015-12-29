import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF610A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();
		if (n%2 == 1) {
			out.println("0");
			return;
		}

		int m = n / 4;
		if (m*4 == n) m--;

		out.println(m);
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