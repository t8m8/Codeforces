import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF608B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		String a = in.next();
		String b = in.next();

		int n = a.length();
		int m = b.length();

		long[][] cnt = new long[m+1][2];
		for (int i=0; i<n; i++) {
			cnt[i][a.charAt(i)-'0']++;
			cnt[m-n+i+1][a.charAt(i)-'0']--;
		}

		// trace(cnt);


		long ans = 0;

		for (int i=1; i<=m; i++) {
			cnt[i][0] += cnt[i-1][0];
			cnt[i][1] += cnt[i-1][1];
		}

		// trace(cnt);

		for (int i=0; i<m; i++) {
			int idx = b.charAt(i) == '0' ? 1 : 0;
			ans += cnt[i][idx];
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