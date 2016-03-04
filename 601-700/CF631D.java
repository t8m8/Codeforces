import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF631D {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	public static int rollingHash(long[][] s, long[][] t, long[] l, long[] r) {
		int n = s.length, m = t.length;
		if (n < m) return 0;

		long base = 1_007L;
		long mod = 1_000_000_009L;
		long pow = 1;
		for (int i=0; i<m; i++) pow = pow*base%mod;

		long sHash = 0, tHash = 0;
		for (int i=0; i<m; i++) {
			sHash = (sHash*base%mod + s[i][0] + s[i][1]*26%mod)%mod;
			tHash = (tHash*base%mod + t[i][0] + t[i][1]*26%mod)%mod;
		}

		int cnt = 0;

		for (int i=0; i+m<=n; i++) {
			if (sHash == tHash && i != 0 && l[0] == s[i-1][0] && i+m != n && r[0] == s[i+m][0]) {
				if (l[1] <= s[i-1][1] && r[1] <= s[i+m][1]) cnt++;
			}
			if (i + m < n) sHash = ((sHash*base%mod - (s[i][0]+s[i][1]*26%mod)*pow%mod + mod)%mod + (s[i+m][0]+s[i+m][1]*26%mod)%mod)%mod;
		}

		return cnt;
	}

	static void solve(){
		int n = in.nextInt();
		int m = in.nextInt();

		ArrayList<long[]> t = new ArrayList<>();
		ArrayList<long[]> s = new ArrayList<>();

		for (int i=0; i<n; i++) {
			String[] x = in.next().split("-");
			int cnt= Integer.parseInt(x[0]);
			int a = x[1].charAt(0) - 'a';

			if (i != 0 && t.get(t.size()-1)[0] == a) {
				long plus = t.get(t.size()-1)[1];
				t.remove(t.size()-1);
				t.add(new long[]{a, cnt + plus});
			} else {
				t.add(new long[]{a, cnt});
			}
		}
		for (int i=0; i<m; i++) {
			String[] x = in.next().split("-");
			int cnt= Integer.parseInt(x[0]);
			int a = x[1].charAt(0) - 'a';

			if (i != 0 && s.get(s.size()-1)[0] == a) {
				long plus = s.get(s.size()-1)[1];
				s.remove(s.size()-1);
				s.add(new long[]{a, cnt + plus});
			} else {
				s.add(new long[]{a, cnt});
			}
		}

		long[][] tc = new long[t.size()][2];
		long[][] sc = new long[s.size()][2];
		for (int i=0; i<t.size(); i++) {
			tc[i] = t.get(i);
		}
		for (int i=0; i<s.size(); i++) {
			sc[i] = s.get(i);
		}

		if (sc.length == 1) {
			long cnt = 0;
			for (int i=0; i<tc.length; i++) {
				if (tc[i][0] == sc[0][0] && tc[i][1] >= sc[0][1]) {
					cnt += tc[i][1] - sc[0][1] + 1;
				}
			}
			out.println(cnt);
		} else {
			out.println(rollingHash(tc, Arrays.copyOfRange(sc, 1, s.size()-1), sc[0], sc[s.size()-1]));
		}
	}

	public static void main(String[] args) throws Exception {
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

class Reader {
	private final InputStream in;
	private final byte[] buf = new byte[1024];
	private int ptr = 0;
	private int buflen = 0;

	public Reader() { this(System.in);}
	public Reader(InputStream source) { this.in = source;}

	private boolean hasNextByte() {
		if (ptr < buflen) return true;
		ptr = 0;
		try{
			buflen = in.read(buf);
		}catch (IOException e) {e.printStackTrace();}
		if (buflen <= 0) return false;
		return true;
	}

	private int readByte() { if (hasNextByte()) return buf[ptr++]; else return -1;}

	private boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}

	private void skip() { while(hasNextByte() && !isPrintableChar(buf[ptr])) ptr++;}

	public boolean hasNext() {skip(); return hasNextByte();}

	public String next() {
		if (!hasNext()) throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public long nextLong() {
		if (!hasNext()) throw new NoSuchElementException();
		boolean minus = false;
		long num = readByte();

		if(num == '-'){
			num = 0;
			minus = true;
		}else if (num < '0' || '9' < num){
			throw new NumberFormatException();
		}else{
			num -= '0';
		}

		while(true){
			int b = readByte();
			if('0' <= b && b <= '9')
				num = num * 10 + (b - '0');
			else if(b == -1 || !isPrintableChar(b))
				return minus ? -num : num;
			else
				throw new NoSuchElementException();
		}
	}

	public int nextInt() {
		long num = nextLong();
		if (num < Integer.MIN_VALUE || Integer.MAX_VALUE < num)
			throw new NumberFormatException();
		return (int)num;
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public char nextChar() {
		if (!hasNext()) throw new NoSuchElementException();
		return (char)readByte();
	}

	public String nextLine() {
		while (hasNextByte() && (buf[ptr] == '\n' || buf[ptr] == '\r')) ptr++;
		if (!hasNextByte()) throw new NoSuchElementException();

		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (b != '\n' && b != '\r') {
			sb.appendCodePoint(b);
			b = readByte();
		}

		return sb.toString();
	}

	public int[] nextIntArray(int n) {
		int[] res = new int[n];
		for (int i=0; i<n; i++) res[i] = nextInt();
		return res;
	}

	public char[] nextCharArray(int n) {
		char[] res = new char[n];
		for (int i=0; i<n; i++) res[i] = nextChar();
		return res;
	}

	public void close() {try{ in.close();}catch(IOException e){ e.printStackTrace();}};
}