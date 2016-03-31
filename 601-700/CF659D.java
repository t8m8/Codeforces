import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF659D {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static final double EPS = 1E-10;

	public static int contains(Point[] poly, Point p) {

		int n = poly.length;
		boolean flag = false;

		for (int i=0; i<n; i++) {
			Vector a = new Vector(poly[i], p);
			Vector b = new Vector(poly[(i+1)%n], p);

			if (Math.abs(Vector.cross(a,b)) < EPS && Vector.dot(a,b) < EPS) return 1;

			if (a.getY() < b.getY()) {
				if (a.getY() < EPS && EPS < b.getY() && Vector.cross(a,b) > EPS) flag = !flag;
			} else {
				if (b.getY() < EPS && EPS < a.getY() && Vector.cross(b,a) > EPS) flag = !flag;
			}
		}

		return flag ? 2 : 0;
	}

	static int ccw(Point pp, Point p, Point q) {
		Vector a = new Vector(p, pp);
		Vector b = new Vector(q, pp);
		if (Vector.cross(a,b) > 1E-10) return -1;
		return 1;
	}

	static void solve(){
		int n = in.nextInt();
		Point[] poly = new Point[n];
		for (int i=0; i<n; i++) {
			poly[i] = new Point(in.nextDouble(), in.nextDouble());
		}
		in.nextDouble(); in.nextDouble();
		int ans = 0;
		int dir = 3;
		double[] dx = { 0, 0.5, 0,-0.5};
		double[] dy = { 0.5, 0,-0.5, 0};
		for (int i=0; i<n; i++) {
			if (contains(poly, new Point(poly[i].getX()+dx[dir], poly[i].getY()+dy[dir])) == 2) {
				ans++;
			}
			dir += ccw(poly[(i-1+n)%n], poly[(i+n)%n], poly[(i+1+n)%n]);
			dir = (dir+4)%4;
		}
		out.println(ans);
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

class EuclideanSpace{
	public static final double EPS = 1E-10;
	public static boolean epsEquals(double a, double b) { return Math.abs(a-b) < EPS; };
}

class Vector extends EuclideanSpace implements Comparable {

	private double x, y;
	public Vector(double x, double y) {
		this.x = x; this.y = y;
	}
	public Vector(Point target, Point source) {
		this.x = target.getX() - source.getX();
		this.y = target.getY() - source.getY();
	}
	public Vector(double tx, double ty, double sx, double sy) {
		this.x = tx - sx;
		this.y = ty - sy;
	}

	public static double dot(Vector a, Vector b) { return a.getX()*b.getX() + a.getY()*b.getY(); }
	public static double cross(Vector a, Vector b) { return a.getX()*b.getY() - a.getY()*b.getX(); }

	public double norm() { return x*x + y*y; }
	public double length() { return Math.sqrt(this.norm()); }

	public Vector plus(Point p) { return new Vector(this.x + p.getX(), this.y + p.getY()); }
	public Vector scalarMul(double c) { return new Vector(c*this.x, c*this.y); }

	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}

	public void set(double x, double y) {
		this.x = x; this.y = y;
	}

	public int compareTo(Object other) {
		Vector p = (Vector)other;
		if (!epsEquals(this.x,p.x)) return this.x < p.x ? -1 : 1;
		if (!epsEquals(this.y,p.y)) return this.y < p.y ? -1 : 1;
		return 0;
	}

	public boolean equals(Vector p) {
		return epsEquals(this.x,p.x) && epsEquals(this.y,p.y);
	}

	public Point toPoint() {
		return new Point(this.x, this.y);
	}

	public String toString() {
		return "("+x+","+y+")";
	}
}


class Point extends EuclideanSpace implements Comparable, Cloneable {

	private double x, y;
	public Point(double x, double y) {
		this.x = x; this.y = y;
	}

	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}

	public void set(double x, double y) {
		this.x = x; this.y = y;
	}

	public int compareTo(Object another) {
		Point p = (Point)another;
		if (!epsEquals(this.x,p.x)) return this.x < p.x ? -1 : 1;
		if (!epsEquals(this.y,p.y)) return this.y < p.y ? -1 : 1;
		return 0;
	}

	public boolean equals(Point p) {
		return epsEquals(this.x,p.x) && epsEquals(this.y,p.y);
	}

	public Point clone() {
		return new Point(this.x, this.y);
	}

	public String toString() {
		return "("+x+","+y+")";
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