import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF666A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static String s;
	static int len;

	static BitSet visited = new BitSet();
	static BitSet f2 = new BitSet();
	static BitSet f3 = new BitSet();

	static void rec(int pos, int t) {
		visited.set(pos + len*(t - 1));
		if (t == 2) {
			f2.set((s.charAt(pos)-'a')*26 + (s.charAt(pos+1)-'a'));
		} else {
			f3.set((s.charAt(pos)-'a')*26*26 + (s.charAt(pos+1)-'a')*26 + (s.charAt(pos+2)-'a'));
		}
		if (pos >= 2 && !visited.get(pos-2 + len))
			if (t != 2 || (s.charAt(pos-2) != s.charAt(pos) || s.charAt(pos-1) != s.charAt(pos+1)))
				rec(pos-2, 2);
		if (pos >= 3 && !visited.get(pos-3 + len*2))
			if (t != 3 || (s.charAt(pos-3) != s.charAt(pos) || s.charAt(pos-2) != s.charAt(pos+1) || s.charAt(pos-1) != s.charAt(pos+2)))
				rec(pos-3, 3);
	}

	static void solve() {
		String t = in.next();
		s = t.substring(5);
		len = s.length();
		
		if (len >= 2)
			rec(len-2, 2);
		if (len >= 3)
			rec(len-3, 3);

		out.println((f2.cardinality() + f3.cardinality()));
		if (f2.cardinality() + f3.cardinality() == 0) return;

		ArrayList<String> ans = new ArrayList<>();
		for (int i = f2.nextSetBit(0); i >= 0; i = f2.nextSetBit(i+1)) {
			StringBuilder sb = new StringBuilder();
			ans.add(sb.append((char)(i/26 + 'a')).append((char)(i%26 + 'a')).toString());
		}

		for (int i = f3.nextSetBit(0); i >= 0; i = f3.nextSetBit(i+1)) {
			StringBuilder sb = new StringBuilder();
			ans.add(sb.append((char)(i/(26*26) + 'a')).append((char)(i%(26*26)/26 + 'a')).append((char)(i%26 + 'a')).toString());
		}

		Collections.sort(ans);
		for (String i : ans) {
			out.println(i);
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