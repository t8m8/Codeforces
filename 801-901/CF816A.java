import java.util.*;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF816A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
        String[] s = in.next().split(":");
        int h = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        long ans = 0;
        while (true) {
            String t = String.valueOf(h);
            int k = 0;
            if (t.length() == 1) {
                k = (t.charAt(0)-'0') * 10;
            } else {
                k = (t.charAt(1)-'0')*10 + (t.charAt(0)-'0');
            }

            if (m <= k && k <= 59) {
                ans += k - m;
                break;
            }
            ans += 60 - m;
            m = 0;
            h = (h + 1) % 24;
        }

        out.println(ans);
	}

	public static void main(String[] args) {
		debug = args.length > 0;
		long start = System.nanoTime();

		solve();
		out.flush();

		long end = System.nanoTime();
		dump((end - start) / 1000000 + " ms");
		in.close();
		out.close();
	}

	static void dump(Object... o) { if (debug) System.err.println(Arrays.deepToString(o)); }
}