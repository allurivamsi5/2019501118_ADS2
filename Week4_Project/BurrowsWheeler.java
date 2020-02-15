import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
	static int R = 256;
	public static void transform() {
		String str = BinaryStdIn.readString();
		CircularSuffixArray cSuffixArray = new CircularSuffixArray(str);
		int zeroId = -1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cSuffixArray.length(); i++) {
			if (cSuffixArray.index(i) == 0) {
				zeroIdx = i;
			}
			int i_dx = cSuffixArray.index(i) - 1;
			if (i_dx < 0) {
				i_dx = cSuffixArray.length()-1;
			}
			sb.append(str.charAt(i_dx));
		}
		BinaryStdOut.write(zeroIdx);                                    
		BinaryStdOut.write(sb.toString());
		BinaryStdOut.close();
	}
	public static void inverseTransform() {
		int first = BinaryStdIn.readInt();
		String t = BinaryStdIn.readString();
		int length = t.length();
		int[] next = new int[length];
		int[] count = new int[R+1];
		for (int i = 0; i < length; i++) {
			count[t.charAt(i)+1]++;
		}
		for (int i = 0; i < R; i++) {
			count[i+1] = count[i]+count[i+1];
		}
		for (int i = 0; i < length; i++) {
			next[count[t.charAt(i)]++] = i;
		}
		StringBuilder sb = new StringBuilder();
		int i_dx = first;
		for (int i = 0; i < length; i++) {
			sb.append(t.charAt(next[i_dx]));
			i_dx = next[i_dx];
		}
		BinaryStdOut.write(sb.toString());
		BinaryStdOut.close();
    }
    public static void main(String[] args) {
        
    }
}