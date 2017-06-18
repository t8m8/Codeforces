import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class CF816C {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);

    static int row(int[][] g, int y) {
        int min = 1000;
        for (int i=0; i<g[0].length; i++) {
            min = Math.min(min, g[y][i]);
        }
        return min;
    }

    static int col(int[][] g, int x) {
        int min = 1000;
        for (int i=0; i<g.length; i++) {
            min = Math.min(min, g[i][x]);
        }
        return min;
    }

    static void rowd(int[][] g, int y, int val) {
        for (int i=0; i<g[0].length; i++) {
            g[y][i] -= val;
        }
    }

    static void cold(int[][] g, int x, int val) {
        for (int i=0; i<g.length; i++) {
            g[i][x] -= val;
        }
    }

	static void solve(){
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] g = new int[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                g[i][j] = in.nextInt();
            }
        }
        ArrayList<String> ans = new ArrayList<>();
        boolean ok = true;
        outer:
        while (true) {
            if (!ok) {
                out.println(-1);
                return;
            }
            ok = false;
            int max = -1;
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    max = Math.max(max, g[i][j]);
                }
            }
            if (max == 0) {
                break;
            }
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    if (g[i][j] == max) {
                        int f1 = row(g, i);
                        int f2 = col(g, j);

                        if (f1 > 0 && f2 > 0) {
                            if (n < m) {
                                rowd(g, i, f1);
                                for (int k=0; k<f1; k++) {
                                    ans.add("row "+(i+1));
                                }
                            }
                            else {
                                cold(g, j, f2);
                                for (int k=0; k<f2; k++) {
                                    ans.add("col "+(j+1));
                                }
                            }
                            ok = true;
                            continue outer;
                        } else if (f1 > 0) {
                            rowd(g, i, f1);
                            for (int k=0; k<f1; k++) {
                                ans.add("row "+(i+1));
                            }
                            ok = true;
                            continue outer;
                        } else if (f2 > 0) {
                            cold(g, j, f2);
                            for (int k=0; k<f2; k++) {
                                ans.add("col "+(j+1));
                            }
                            ok = true;
                            continue outer;
                        }
                    }
                }
            }
        }

        out.println(ans.size());
        for (int i=0; i<ans.size(); i++) {
            out.println(ans.get(i));
        }
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