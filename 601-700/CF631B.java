import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF631B {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve(){
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();

		ArrayList<int[]>[] r = new ArrayList[n];
		for (int i=0; i<n; i++) {
			r[i] = new ArrayList<int[]>();
			r[i].add(new int[]{-1,0});
		}

		ArrayList<int[]>[] c = new ArrayList[m];
		for (int i=0; i<m; i++) {
			c[i] = new ArrayList<int[]>();
			c[i].add(new int[]{-1,0});
		}


		for (int i=0; i<k; i++) {
			int a = in.nextInt();
			if (a == 1) {
				r[in.nextInt()-1].add(new int[]{i, in.nextInt()});
			} else {
				c[in.nextInt()-1].add(new int[]{i, in.nextInt()});
			}
		}
		int[][] ans = new int[n][m];

		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				int[] a = r[i].get(r[i].size()-1);
				int[] b = c[j].get(c[j].size()-1);
				if (a[0] > b[0]) {
					ans[i][j] = a[1];
				} else {
					ans[i][j] = b[1];
				}
			}
		}

		for (int i=0; i<n; i++) {
			for (int j=0; j<m-1; j++) {
				out.print(ans[i][j]+" ");
			}
			out.println(ans[i][m-1]);
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