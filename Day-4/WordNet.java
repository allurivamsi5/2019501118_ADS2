import java.io.File;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner; 
import java.util.HashMap;
public class WordNet
{ 
    ArrayList<String> synsetId;
    ArrayList<String> synsetWord;
    ArrayList<String> hypernymsId;
    ArrayList<String> hypernymsEdges;
    Digraph vertices; 
    SAP sap;

    HashMap<Integer, Bag<String>> idmap;
    HashMap<String, Bag<Integer>> wordmap;
    public WordNet(String synsets, String hypernyms) throws Exception{

        idmap = new HashMap<Integer, Bag<String>>();
        wordmap= new HashMap<String, Bag<Integer>>();

        synsetId = new ArrayList<>();
        synsetWord = new ArrayList<>();
        hypernymsId = new ArrayList<>();
        hypernymsEdges = new ArrayList<>();
        this.parseSynsets(synsets);
        vertices = new Digraph(synsetId.size());
        this.parseHypernyms(hypernyms);
        System.out.println(vertices.size());
        int count = 0;
        for(int i = 0; i < vertices.size(); i++){
            count = count + vertices.adj[i].size();
        }
        System.out.println(count);
        sap = new SAP(vertices);
    }
    private void parseSynsets(String filename) throws Exception {

        File file =  new File("C:\\Users\\VAMSI\\Documents\\2019501118_ADS2\\2019501118_ADS2\\Day-1\\Wordnet\\synsets.txt"); 
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String[] arr = sc.nextLine().split(",");
            synsetId.add(arr[0]);
            synsetWord.add(arr[1]);
            //System.out.println(arr[0] +" "+ arr[1]);
    }
    }

    private void parseHypernyms(String filename) throws Exception{
        File file1 =  new File("C:\\Users\\VAMSI\\Documents\\2019501118_ADS2\\2019501118_ADS2\\Day-1\\Wordnet\\hypernyms.txt"); 
        Scanner sc1 = new Scanner(file1);
        int count = 0; 
        while (sc1.hasNextLine()){
            String[] arr = sc1.nextLine().split(",");
           // System.out.println(arr[0] +" "+ arr[1]);
           hypernymsId.add(arr[0]);
           for(int i = 1; i < arr.length; i++){
               vertices.addEdge(Integer.parseInt(arr[count]),Integer.parseInt(arr[i]));
           }
           for(int j = 1; j < arr.length; j++){
               hypernymsEdges.add(arr[j]);
           }
    }
    }
    public Iterable<String> nouns(){
        return wordmap.keySet();

    }

    public boolean isNoun(String word){
        return wordmap.containsKey(word);
    }

    public int distance(String nounA, String nounB) throws Exception{
        if(!isNoun(nounA) || !isNoun(nounB)){
            throw new IllegalArgumentException();
        }
        Iterable<Integer> A = wordmap.get(nounA);
        Iterable<Integer> B = wordmap.get(nounB);

        return sap.length(A,B);
    }

    public String sap(String nounA, String nounB) throws Exception{

        if(!isNoun(nounA) || !isNoun(nounB)){
            throw new IllegalArgumentException();
        }
        Iterable<Integer> A = wordmap.get(nounA);
        Iterable<Integer> B = wordmap.get(nounB);

        int ancestor = sap.ancestor(A,B);
        Bag<String> result = idmap.get(ancestor);

        String r = "";
        for(String noun : result){
            r += noun;
        }
        return r;

    }
  public static void main(String[] args) throws Exception 
  { 
    WordNet obj = new WordNet("synsets","hypernyms");
    System.out.println("distance = " + obj.sap.length(3,11));
    System.out.println("ancestor = " + obj.sap.ancestor(3,16531));

    System.out.println( obj.sap("vamsi","alluri"));
  } 
} 