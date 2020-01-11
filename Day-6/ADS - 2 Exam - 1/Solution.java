import java.io.File; 
// import java.util.Scanner;
// import java.util.Collections;
// import java.util.HashMap;
import java.util.*;
public class Solution{

    HashMap<Integer, String> map = new HashMap<>();
    HashMap<Integer, Integer> map1 = new HashMap<>();
    HashMap<Integer, Integer> map2 = new HashMap<>();

    int[] arr3;
    private void email(String filename) throws Exception{
        File file = new File("C:\\Users\\VAMSI\\Documents\\2019501118_ADS2\\2019501118_ADS2\\Day-6\\ADS - 2 Exam - 1\\emails.txt");
        Scanner sc = new Scanner (file);
        while (sc.hasNextLine()){
            String[] arr = sc.nextLine().split(";");
            int id = Integer.parseInt(arr[0]);
            map.put(id, arr[1]);
            //System.out.println(arr[0] + "    "+arr[1]);
        }
        arr3 = new int[map.size()];
        for (int i = 0; i < arr3.length;i++){
            arr3[i] = 0;
        }
    }
    
    private void emailLogs(String filename) throws Exception{
        File file1 = new File("C:\\Users\\VAMSI\\Documents\\2019501118_ADS2\\2019501118_ADS2\\Day-6\\ADS - 2 Exam - 1\\email-logs.txt");
        Scanner sc1 = new Scanner(file1);
        while (sc1.hasNextLine()){
            String[] arr = sc1.nextLine().split(" ");
            String[] arr1 = arr[1].split(",");
            int id1 = Integer.parseInt(arr1[0]);
            int id2 = Integer.parseInt(arr[3]);
            map1.put(id1,id2);
            arr3[id2]++;
        }
        for(int j = 0; j<arr3.length;j++){
            map2.put(arr3[j],j);
        }
        Arrays.sort(arr3);

        for(int x = arr3.length - 10; x < arr3.length;x++){
            System.out.println(map.get(map2.get(arr3[x])));
        }
    }

    public static void main (String[] args) throws Exception{
        Solution obj = new Solution();
        obj.email("emails");
        obj.emailLogs("email-logs");
        // System.out.println(obj.map);
        // System.out.println(obj.map1);
  
    }
}