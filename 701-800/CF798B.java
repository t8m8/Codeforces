import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF798B {

    static final Scanner in = new Scanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out,false);
    static boolean debug = false;

    static void solve() {
       int n = in.nextInt();
       String[] s = new String[n];
       for (int i=0; i<n; i++) {
           s[i] = in.next();
       }
       int m = s[0].length();
       for (int i=1; i<n; i++) {
           for (int j=0; j<m; j++) {
               boolean g = false;
               for (int k=0; k<m; k++) {
                   boolean f = true;
                   for (int p=j, q=k, r=0; r<m; p=(p+1)%m, q=(q+1)%m, r++) {
                       if (s[0].charAt(p) != s[i].charAt(q))
                            f = false;
                   }
                   if (f) {
                       g = true;
                       break;
                   }
               }
               if (!g) {
                   out.println("-1");
                   return;
               }
           }
       }
       int ans = Integer.MAX_VALUE/2;
       for (int j=0; j<m; j++) {
           int sum = j;
           for (int i=1; i<n; i++) {
               for (int k=0; k<m; k++) {
                   boolean f = true;
                   for (int p=j, q=k, r=0; r<m; p=(p+1)%m, q=(q+1)%m, r++) {
                       if (s[0].charAt(p) != s[i].charAt(q)) {
                            f = false;
                       }
                   }
                   if (f) {
                       sum += k;
                       break;
                   }
               }
           }
           ans = Math.min(ans, sum);
       }
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