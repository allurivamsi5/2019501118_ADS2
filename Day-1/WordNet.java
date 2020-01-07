import java.io.File; 
import java.util.Scanner; 
public class WordNet
{ 
    private void parseSynsets(String filename) throws Exception {

        File file =  new File("C:\\Users\\VAMSI\\Documents\\2019501118_ADS2\\2019501118_ADS2\\Day-1\\Wordnet\\synsets.txt"); 
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String[] arr = sc.nextLine().split(",");
            System.out.println(arr[0] +" "+ arr[1]);
    }
    }

    private void parseHypernyms(String filename) throws Exception{
        File file1 =  new File("C:\\Users\\VAMSI\\Documents\\2019501118_ADS2\\2019501118_ADS2\\Day-1\\Wordnet\\hypernyms.txt"); 
        Scanner sc1 = new Scanner(file1); 
        while (sc1.hasNextLine()){
            String[] arr = sc1.nextLine().split(",");
            System.out.println(arr[0] +" "+ arr[1]);
    }
    }
  public static void main(String[] args) throws Exception 
  { 
    
    WordNet obj = new WordNet();
    obj.parseSynsets("f");
    obj.parseHypernyms("f1");
  } 


} 