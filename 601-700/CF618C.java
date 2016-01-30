import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF618C {

	static final Reader in = new Reader(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();

		Point[] ps = new Point[n];
		for (int i=0; i<n; i++) {
			ps[i] = new Point(in.nextInt(), in.nextInt(), i+1);
		}

		Arrays.sort(ps);

		for (int i=2; i<n; i++) {
			if (EuclideanSpace.epsEquals((ps[i].getY()-ps[0].getY())*(ps[i].getX()-ps[1].getX()),
					(ps[i].getY()-ps[1].getY())*(ps[i].getX()-ps[0].getX()))) continue;
			out.println(ps[0].idx+" "+ps[1].idx+" "+ps[i].idx);
			return;
		}
	}

	public static void main(String[] args) {
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

class EuclideanSpace{
	public static final double EPS = 1E-8;
	public static boolean epsEquals(double a, double b) { return Math.abs(a-b) < EPS; };

	public static double ptSegDist(Segment s, Point p) {
		Vector a = new Vector(s.getP2(), s.getP1());
		Vector b = new Vector(p, s.getP1());
		Vector c = new Vector(p, s.getP2());

		if (Vector.dot(a, b) < 0) return b.length();
		if (Vector.dot(a, c) < 0) return c.length();
		return Math.abs(Vector.cross(a, b)) / b.length();
	}

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
}

class Segment {

	private final Point p1, p2;

	public Segment(double x1, double y1, double x2, double y2) {
		this.p1 = new Point(x1,y1);
		this.p2 = new Point(x2,y2);
	}
	public Segment(Point p1, Point p2) {
		this.p1 = p1.clone();
		this.p2 = p2.clone();
	}

	public double getX1() {
		return p1.getX();
	}
	public double getY1() {
		return p1.getY();
	}
	public double getX2() {
		return p2.getX();
	}
	public double getY2() {
		return p2.getY();
	}

	public Point getP1() {
		return p1.clone();
	}
	public Point getP2() {
		return p2.clone();
	}

	public String toString() {
		return "[("+p1.getX()+","+p1.getY()+"),("+p2.getX()+","+p2.getY()+")]";
	}
}

class Point extends EuclideanSpace implements Comparable, Cloneable {

	private double x, y;
	public int idx;
	public Point(double x, double y) {
		this.x = x; this.y = y;
	}
	public Point(double x, double y, int idx) {
		this.x = x; this.y = y;
		this.idx = idx;
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