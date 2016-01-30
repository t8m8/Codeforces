import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF618A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();
		int x = 1;
		ArrayList<Integer> ans = new ArrayList<>();
		while (n > 0) {
			if (n%2 != 0)
				ans.add(x);
			n /= 2;
			x++;
		}

		for (int i=ans.size()-1; i>0; i--) {
			out.print(ans.get(i)+" ");
		}
		out.println(ans.get(0));
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