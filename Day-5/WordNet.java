import java.util.ArrayList;
import java.util.Scanner; 
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
public class WordNet
{ 
    // private ArrayList<String> synsetId;
    // private ArrayList<String> synsetWord;
    // private ArrayList<String> hypernymsId;
    // private ArrayList<String> hypernymsEdges;
    private final Digraph vertices; 
    private final SAP sap;
    private final HashMap<Integer, String> idmap;
    private final HashMap<String, ArrayList<Integer>> wordmap;
    public WordNet(String synsets, String hypernyms) {

        idmap = new HashMap<Integer, String>();
        wordmap= new HashMap<String, ArrayList<Integer>>();

        // synsetId = new ArrayList<>();
        // synsetWord = new ArrayList<>();
        // hypernymsId = new ArrayList<>();
        // hypernymsEdges = new ArrayList<>();
        this.parseSynsets(synsets);
        vertices = new Digraph(idmap.size());
        this.parseHypernyms(hypernyms);
       // System.out.println(vertices.size());
        sap = new SAP(vertices);
    }
    private void parseSynsets(String synsets) {

        //File file =  new File("C:\\Users\\VAMSI\\Documents\\2019501118_ADS2\\2019501118_ADS2\\Day-1\\Wordnet\\synsets.txt"); 
        In sc = new In(synsets);
        while (sc.hasNextLine()){
            String[] arr = sc.readLine().split(",");
            // synsetId.add(arr[0]);
            // synsetWord.add(arr[1]);
            idmap.put(Integer.parseInt(arr[0]), arr[1]);
            String[] words = arr[1].split(" ");
            for(String s: words) {
                //idmap.put(Integer.parseInt(arr[0]),arr[1]);
                if(!wordmap.containsKey(s)){
                    ArrayList<Integer> array = new ArrayList<Integer>();
                    array.add(Integer.parseInt(arr[0]));
                    wordmap.put(s, array);
                }
                else{
                    ArrayList<Integer> val = wordmap.get(s);
                    val.add(Integer.parseInt(arr[0]));
                    wordmap.put(s, val);
                }
            }
            //System.out.println(arr[0] +" "+ arr[1]);
        }
    }

    private void parseHypernyms(String hypernyms) {
        //File file1 =  new File("C:\\Users\\VAMSI\\Documents\\2019501118_ADS2\\2019501118_ADS2\\Day-1\\Wordnet\\hypernyms.txt"); 
        In sc1 = new In(hypernyms);
        int count = 0;
        while (sc1.hasNextLine()){
            String[] arr = sc1.readLine().split(",");
           // System.out.println(arr[0] +" "+ arr[1]);
           //wordmap.add(arr[0]);
           for(int i = 1; i < arr.length; i++){
               vertices.addEdge(Integer.parseInt(arr[count]),Integer.parseInt(arr[i]));
           }
    }
    }
    public Iterable<String> nouns(){
        return wordmap.keySet();

    }

    public boolean isNoun(String word){
        return wordmap.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        if(!isNoun(nounA) || !isNoun(nounB)){
            throw new IllegalArgumentException();
        }
        Iterable<Integer> A = wordmap.get(nounA);
        Iterable<Integer> B = wordmap.get(nounB);

        return sap.length(A,B);
    }

    public String sap(String nounA, String nounB) {

        if(!isNoun(nounA) || !isNoun(nounB)){
            throw new IllegalArgumentException();
        }
        Iterable<Integer> A = wordmap.get(nounA);
        Iterable<Integer> B = wordmap.get(nounB);

        int ancestor = sap.ancestor(A,B);
        String result = idmap.get(ancestor);
        return result;

    }
  public static void main(String[] args)
  { 
    // WordNet obj = new WordNet("synsets","hypernyms");
    // // System.out.println("distance = " + obj.sap.length(3,11));
    // // System.out.println("ancestor = " + obj.sap.ancestor(3,16531));
    // String[] intre = {"vamsi","alluri"};
    // // System.out.println( obj.sap("vamsi","alluri"));
    // System.out.println(obj.oc.outcast(intre));

  } 
} 