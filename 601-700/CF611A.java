import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF611A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	public static int[] leapDays = {0,31,29,31,30,31,30,31,31,30,31,30,31};

	static void solve() {
		int x = in.nextInt();
		in.next();
		int ans = 0;
		if (in.next().equals("week")) {
			if (x < 5 || x == 7) {
				ans = 52;
			} else {
				ans = 53;
			}
		} else {
			for (int i=1; i<=12; i++) {
				if (leapDays[i] >= x) {
					ans++;
				}
			}
		}

		out.println(ans);
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