import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF732D {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static boolean f(int[] a, int[] d, int mid) {
		int n = d.length;
		int m = a.length;
		int[][] idx = new int[m][2];
		for (int i=0; i<m; i++) {
			idx[i][0] = -1;
		}
		for (int i=mid; i>=0; i--) {
			if (d[i] == -1) continue;
			if (idx[d[i]][0] == -1) {
				idx[d[i]][0] = i;
			}
		}



		for (int i=0; i<m; i++) {
			if (idx[i][0] == -1) {
				return false;
			}
			idx[i][1] = i;
		}

		int[] cnt = new int[m];
		Arrays.sort(idx, new Comparator<int[]>(){
			public int compare(int[] x, int[] y) {
				if (x[0] != y[0]) return x[0] - y[0];
				return x[1] - y[1];
			}
		});

		int cpos = 0, use = 0;
		for (int i=0; i<=mid; i++) {
			if (i == idx[use][0]) {
				if (cnt[idx[use][1]] != a[idx[use][1]]) return false;
				use++;
			} else {
				cnt[idx[cpos][1]]++;
				if (cnt[idx[cpos][1]] == a[idx[cpos][1]]) {
					cpos++;
				}
			}

			if (cpos == m || use == m) {
				return true;
			}
		}

		return false;
	}

	static void solve(){
		int n = in.nextInt();
		int m = in.nextInt();
		int[] d = new int[n];
		for (int i=0; i<n; i++) {
			d[i] = in.nextInt() - 1;
		}
		int[] a = new int[m];
		for (int i=0; i<m; i++) {
			a[i] = in.nextInt();
		}

		if (n < m) {
			out.println("-1");
			return;
		}

		int l = -1, r = n;
		while (r - l > 1) {
			int mid = (l + r)/2;
			if (f(a, d, mid)) r = mid;
			else l = mid;
		}

		int p = Math.max(1, l-3), q = Math.min(n, r+3);

		for (int i=p; i<q; i++) {
			if (!f(a, d, i-1) && f(a, d, i)) {
				out.println((i+1));
				return;
			}
		}

		out.println("-1");
	}

	public static void main(String[] args) throws Exception {
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