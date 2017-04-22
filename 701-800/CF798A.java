import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class A {

    static final Scanner in = new Scanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out,false);
    static boolean debug = false;

    static void solve() {
       String s = in.next();
       int cnt = 0;
       for (int i=0, j=s.length()-1; i<j; i++, j--) {
           if (s.charAt(i) != s.charAt(j)) {
               cnt++;
           }
       } 
       if (cnt == 1 || (cnt == 0 && s.length()%2 == 1)) {
           out.println("YES");
       } else {
           out.println("NO");
       }
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