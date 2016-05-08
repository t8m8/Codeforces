import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF673C {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int n;
	static int[] t;
	static int[][] dp, cnt;

	static void solve(){
		int n = in.nextInt();
		t = new int[n];
		cnt = new int[5001][5001];
		for (int i=0; i<n; i++) {
			t[i] = in.nextInt() - 1;
			cnt[t[i]][i]++;
		}
		for (int i=0; i<=5000; i++) {
			for (int j=1; j<=5000; j++) {
				cnt[i][j] += cnt[i][j-1];
			}
		}

		dp = new int[n][n];
		dp[0][0] = t[0];

		for (int r=1; r<n; r++) {
			dp[r][r] = t[r];
			for (int l=0; l<r; l++) {
				int x = cnt[t[r]][r-1] - (l > 0 ? cnt[t[r]][l-1] : 0) + 1;
				int y = cnt[dp[l][r-1]][r-1] - (l > 0 ? cnt[dp[l][r-1]][l-1] : 0);
				if (x == y) {
					dp[l][r] = Math.min(dp[l][r-1], t[r]);
				} else if (x > y) {
					dp[l][r] = t[r];
				} else {
					dp[l][r] = dp[l][r-1];
				}
			}
		}

		int[] cnt = new int[n];
		for (int i=0; i<n; i++) {
			for (int j=i; j<n; j++) {
				cnt[dp[i][j]]++;
			}
		}
		for (int i=0; i<n-1; i++) {
			out.print(cnt[i]+" ");
		}
		out.println(cnt[n-1]);
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

class FenwickTree {

	public static final long MOD = 1_000_000_007;
	public final int length;
	private long[] data;

	public FenwickTree(int length) {
		this.length = length;
		this.data = new long[length+2];
	}

	// [begin, end)
	public void add(int begin, int end, long n) {
		add(begin, n);
		add(end, (MOD - n)%MOD);
	}

	private void add(int idx, long n) {
		idx++;
		while (idx <= length) {
			data[idx] = (data[idx] + n)%MOD;
			idx += idx&-idx;
		}
	}

	public long get(int idx) {
		idx++;
		long ret = 0;
		while (idx > 0) {
			ret = (ret + data[idx])%MOD;
			idx -= idx&-idx;
		}
		return ret;
	}

	public String toString() {
		long[] val = new long[length];
		for (int i=0; i<length; i++) val[i] = get(i);
		return Arrays.toString(val);
	}
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
		while (b != '\n' && b != '\r' && b != -1) {
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