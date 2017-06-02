import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF812B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
        int n = in.nextInt();
        int m = in.nextInt() + 2;

        char[][] cs = new char[n][m];
        for (int i=0; i<n; i++) {
            String s = in.next();
            cs[n-1-i] = s.toCharArray();
        }

        for (int i=n-1; i>=0; i--) {
            boolean f = true;
            for (int j=0; j<m; j++) {
                if (cs[i][j] == '1') {
                    f = false;
                    break;
                }
            }
            if (!f) {
                break;
            } else {
                n--;
            }
        }

        if (n == 0) {
            out.println(0);
            return;
        }

        int[][] lr = new int[n][2];
        for (int i=0; i<n; i++) {
            lr[i][0] = lr[i][1] = -1;
            for (int j=0; j<m; j++) {
                if (cs[i][j] == '1') {
                    lr[i][0] = j;
                    break;
                }
            }

            for (int j=m-1; j>=0; j--) {
                if (cs[i][j] == '1') {
                    lr[i][1] = j;
                    break;
                }
            }
        }

        // int start = 0;
        // for (int i=0; i<m; i++) {
        //     if (cs[0][i] == '1') {
        //         start = i;
        //     }
        // }

        // out.println(start);

        int ans = Integer.MAX_VALUE/2;

        for (int i=0; i<(1<<n); i++) {
            int cnt = 0;
            int pos = 0;

            for (int j=0; j<n; j++) {
                if (lr[j][0] == -1) {
                    cnt += 1;
                    continue;
                } else {
                    int a = Math.abs(pos - lr[j][0]);
                    int b = Math.abs(pos - lr[j][1]);
                    if (a > b) {
                        cnt += a;
                        pos = lr[j][0];
                    } else {
                        cnt += b;
                        pos = lr[j][1];
                    }
                }

                if (j == n-1) {
                    break;
                }

                if (((i >> j) & 1) == 0) {
                    cnt += pos + 1;
                    pos = 0;
                } else {
                    cnt += (m - pos - 1) + 1;
                    pos = m-1;
                }
            }

            ans = Math.min(ans, cnt);
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