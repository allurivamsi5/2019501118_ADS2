import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.In;

public class CircularSuffixArray {
	private int[] index;

	// circular suffix array of s
	public CircularSuffixArray(String s) {
		if (null == s) {
			throw new IllegalArgumentException();
		}
		int length = s.length();
		index = new int[length];
		// each i represent the starting char of that suffix string
		for (int i=0; i<length; i++) {
			index[i] = i;
		}

		LSDSort(s, index);
	}

	private void LSDSort(String s, int[] arr) {
		int R= 256;
		int N = s.length();
		int[] aux = new int[N];

		for (int d=N-1; d>=0; d--) {
			int[] count = new int[R+1];
			for (int i=0; i<N; i++) {
				int startingPos = arr[i];
				int realIdx = d+startingPos;
				if (realIdx >= N) {
					realIdx = realIdx-N;
				}
				count[s.charAt(arr[realIdx]) +1] ++;
			}

			for (int r=0; r<R; r++) {
				count[r+1] += count[r];
			}

			for (int i=0; i<N; i++) {
				int startingPos = arr[i];
				int realIdx = d+startingPos;
				if (realIdx >= N) {
					realIdx = realIdx-N;
				}
				aux[count[s.charAt(realIdx)]++] = arr[i];
			}

			for (int i=0; i<N; i++) {
				arr[i] = aux[i];
			}
		}
	}

	// length of s
	public int length() {
		return index.length;
	}

	// returns index of ith sorted suffix
	public int index(int i) {
		if (i<0 || i>index.length-1) {
			throw new IllegalArgumentException();
		}
		return index[i];
    }
    public static void main(String[] args) {
        
    }
}