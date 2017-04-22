import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

    static final Scanner in = new Scanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out,false);
    static boolean debug = false;

    static int gcd(int a, int b) {
        if (a == 0) return b;
        else return gcd(b%a, a);
    }

    static void solve() {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = in.nextInt();
        }

        int gcd = a[0];
        for (int i=1; i<n; i++) {
            gcd = gcd(gcd, a[i]);
        }
        if (gcd > 1) {
            out.println("YES");
            out.println(0);
            return;
        }

        long ans = 0;
        for (int i=0; i<n-1; i++) {
            if (a[i]%2 == 1 && a[i+1]%2 == 1) {
                ans += 1;
                a[i] = 2;
                a[i+1] = 2;
            }
        }

        for (int i=0; i<n-1; i++) {
            if (a[i]%2 != a[i+1]%2) {
                ans += 2;
                a[i] = 2;
                a[i+1] = 2;
            }
        }
        out.println("YES");
        // dump(a);
        out.println(ans);
    }

    public static void main(String[] args) {
        debug = args.length > 0;
        long start = System.nanoTime();

        solve();
        out.flush();

        long end = System.nanoTime();
        in.close();
        out.close();
    }

    static void dump(Object... o) { if (debug) System.err.println(Arrays.deepToString(o)); }
}