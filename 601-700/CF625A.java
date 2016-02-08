import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF625A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		long n = in.nextLong();
		long a = in.nextLong();
		long b = in.nextLong();
		long c = in.nextLong();

		if (n < Math.min(a, b)) {
			out.println(0);
			return;
		}

		long ans = 0;
		if (a < b) {
			long m = n;
			if (n >= b) {
				ans = (n - b)/(b - c);
				n -= ans*(b - c);
				while (n >= b) {
					ans++;
					n -= b;
					n += c;
				}
			}
			ans += n/a;
			ans = Math.max(m/a, ans);
		} else {
			ans = (n - b)/(b - c);
			n -= ans*(b - c);
			while (n >= b) {
				ans++;
				n -= b;
				n += c;
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