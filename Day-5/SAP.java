import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
public class SAP{
    private final Digraph dg;
    public SAP(Digraph G){
        this.dg = G;
    }

    public int length(int v, int w){
        BreadthFirstDirectedPaths vbfs = new BreadthFirstDirectedPaths(dg,v);
        BreadthFirstDirectedPaths wbfs = new BreadthFirstDirectedPaths(dg,w);
        return sap(vbfs, wbfs, true);
    }

    public int ancestor(int v, int w){
        BreadthFirstDirectedPaths vbfs = new BreadthFirstDirectedPaths(dg,v);
        BreadthFirstDirectedPaths wbfs = new BreadthFirstDirectedPaths(dg,w);
        return sap(vbfs, wbfs, false);
    }

    public int length(Iterable<Integer>v, Iterable<Integer>w){
        BreadthFirstDirectedPaths vbfs = new BreadthFirstDirectedPaths(dg,v);
        BreadthFirstDirectedPaths wbfs = new BreadthFirstDirectedPaths(dg,w);
        return sap(vbfs, wbfs, true);
    }

    public int ancestor(Iterable<Integer>v, Iterable<Integer>w){
        BreadthFirstDirectedPaths vbfs = new BreadthFirstDirectedPaths(dg,v);
        BreadthFirstDirectedPaths wbfs = new BreadthFirstDirectedPaths(dg,w);
        return sap(vbfs, wbfs, false);
    }

    private int sap(BreadthFirstDirectedPaths vbfs,BreadthFirstDirectedPaths wbfs, boolean len) {
        int min = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < dg.V() ; i++) {
            if(vbfs.hasPathTo(i) && wbfs.hasPathTo(i)){
                int vLength = vbfs.distTo(i);
                int wLength = wbfs.distTo(i);
                int vwLength = vLength + wLength;
                if(min > vwLength){
                    min = vwLength;
                    ancestor = i;
                }
            }
            
        }
        if(len){
            if(min < Integer.MAX_VALUE){
                return min;
            }else{
                return -1;
            }
        }
        else{
            return ancestor;
        }

    }
}