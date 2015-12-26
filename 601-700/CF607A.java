import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF607A {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);

	static int binarySearch(int[][] a, int k, boolean f) {
		int n = a.length;
		int l = -1, r = n-1;
		while (r - l > 1) {
			int m = (l + r)/2;
			if (a[m][0] < k) l = m;
			else r = m;
		}

		return f ? r : l;
	}

	static void solve(){
		int n = in.nextInt();
		int[][] a = new int[n][2];

		for (int i=0; i<n; i++) {
			for (int j=0; j<2; j++) {
				a[i][j] = in.nextInt();
			}
		}

		Arrays.sort(a, new Comparator<int[]>(){
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});

		int[][] dp = new int[n+1][2];
		for (int i=1; i<=n; i++) {
			for (int j=0; j<2; j++) {
				dp[i][j] = Integer.MAX_VALUE/2;
			}
		}

		for (int i=1; i<=n; i++) {
			dp[i][0] = Math.min(dp[i-1][0],dp[i-1][1]) + 1;
			int plus = i-1 - binarySearch(a, a[i-1][0]-a[i-1][1], true);
			int idx = binarySearch(a, a[i-1][0]-a[i-1][1], false);
			dp[i][1] = dp[idx+1][1] + plus;
		}

		// trace(dp);

		out.println(Math.min(dp[n][0],dp[n][1]));
	}

	public static void main(String[] args) throws Exception {
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