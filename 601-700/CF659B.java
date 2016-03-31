import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF659B {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve(){
		int n = in.nextInt();
		int m = in.nextInt();
		ArrayList<ArrayList<Pair<Integer, String>>> list = new ArrayList<>();
		for (int i=0; i<m; i++) {
			list.add(new ArrayList<Pair<Integer, String>>());
		}
		for (int i=0; i<n; i++) {
			String s = in.next();
			int a = in.nextInt()-1;
			int p = in.nextInt();
			list.get(a).add(new Pair<Integer, String>(p, s));
		}
		for (int i=0; i<m; i++) {
			Collections.sort(list.get(i));
		}
		for (int i=0; i<m; i++) {
			ArrayList<Pair<Integer, String>> l = list.get(i);
			Collections.sort(l);
			dump(l);
			if (l.size() == 2 || l.get(1).fst > l.get(2).fst) {
				out.println(l.get(0).snd+" "+l.get(1).snd);
			} else {
				out.println("?");
			}
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

class Pair <A extends Comparable<? super A>, B extends Comparable<? super B>> implements Comparable<Pair<A, B>> {
	A fst; B snd;

	public Pair(A fst, B snd) {
		this.fst = fst;
		this.snd = snd;
	}

	public int compareTo(Pair<A, B> p) {
		if (fst.compareTo(p.fst) != 0) return p.fst.compareTo(fst);
		return snd.compareTo(p.snd);
	}

	public boolean equals(Object o) {
		Pair p = (Pair)o;
		return fst.equals(p.fst) && snd.equals(p.snd);
	}

	public int hashCode() {
		return Objects.hash(fst, snd);
	}

	public String toString() {
		return "(" + fst.toString() + "," + snd.toString() + ")";
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

	public void close() {try{ in.close();}catch(IOException e){ e.printStackTrace();}};
}