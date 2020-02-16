import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

	private static int R = 256;
    public static void encode() {
    		char[] asciiarr = new char[R];
    		for (char i=0; i<256; i++) {
    			asciiarr[i] = i;
    		}
    		while (!BinaryStdIn.isEmpty()) {
    			char c = BinaryStdIn.readChar();
    			// searc that char
    			char prev = asciiarr[0];
    			int mark=-1;
    			for (char j=0; j<R; j++) {
    				if (asciiarr[j] == c) {
    					asciiarr[0] = c;
    					asciiarr[j] = prev;
    					mark=j;
    					break;
    				} else {
    					char temp = asciiarr[j];
    					asciiarr[j] = prev;
    					prev = temp;
    				}
    			}
    			BinaryStdOut.write((char)mark);
    		}
    		BinaryStdOut.close();
    }
    public static void decode() {
    		char[] chars = new char[R];
		for (char i=0; i<R; i++) {
			chars[i] = i;
		}
		while (!BinaryStdIn.isEmpty()) {
			char c = BinaryStdIn.readChar();
			BinaryStdOut.write(chars[c], 8);
			char prev = chars[0];
			for (char j=0; j<R; j++) {
				if (j == c) {
					chars[0] = chars[c];
					chars[j] = prev;
					break;
				} else {
					char temp = chars[j];
					chars[j] = prev;
					prev = temp;
				}
			}
		}
		BinaryStdOut.close();
    }
    public static void main(String[] args) {
        
    }
}