import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF611B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		long a = in.nextLong(), b = in.nextLong();
		String abit = Long.toBinaryString(a);
		String bbit = Long.toBinaryString(b);

		int l = abit.length(), r = bbit.length();
		long ans = 0;
		for (int k=l; k<=r; k++) {
			long x = (long)Math.pow(2,k)-1;
			for (int i=0; i<k-1; i++) {
				long y = 1L<<i;
				long z = x^y;
				if (a <= z && z <= b) {
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