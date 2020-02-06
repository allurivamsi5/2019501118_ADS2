import java.util.HashSet;
import java.util.Set;

public class BoggleSolver{
    private Node root;
    private static class Node{
        private String word;
        private Node[] next = new Node[26];
    }
    
    public BoggleSolver(String[] dict){
        for(int i = 0; i < dict.length; i++){
            put(dict[i],dict[i]);
        }

    }

    public Iterable<String> getAllValidWords(BoggleBoard board){
        Node next_node;
        int b_row = board.rows();
        int b_col = board.cols();
        Set<String> validwords = new HashSet<String>(3*b_row*b_col);
        boolean[][] visited = new boolean[b_row][b_col];

        for(int i = 0; i < b_row;i++){
            for(int j = 0;j<b_col;j++){
                if(board.getLetter(i,j) == 'Q'){
                    next_node = get(root,'Q',0);
                    next_node = get(next_node,'U',0);
                }
                else{
                    next_node = get(root,board.getLetter(i,j),0);
                }
                if(next_node != null){
                    dfs(board, visited, validwords, i,j, next_node);
                }
            }
        }
        return validwords;

    }
    
    private void dfs(BoggleBoard board, boolean[][] visited, Set<String> validwords,int i, int j, Node x){
        Node next_node;
        int b_row = board.rows();
        int b_col= board.cols();
        visited[i][j] = true;
        for(int adj_row = i-1; adj_row <= i+1;adj_row++){
            for(int adj_col = j-1;adj_col <=j+1;adj_col++){
                if(adj_row == i && adj_col == j){
                    continue;
                }
                if(adj_row < b_row && adj_col < b_col && adj_row >= 0 && adj_col >= 0){
                    if(!visited[adj_row][adj_col]){
                        if(board.getLetter(adj_row,adj_col)=='Q'){
                            next_node = get(x,'Q',0);
                            next_node = get(next_node,'U',0);
                        }else{
                            next_node = get(x,board.getLetter(adj_row,adj_col),0);
                        }
                        if(next_node != null){
                            dfs(board,visited,validwords,adj_row,adj_col,next_node);
                        }
                    }
                }
            }
        }
        visited[i][j] = false;
        if(x.word != null){
            if(x.word.length()>2){
                validwords.add(x.word);
            }
        }
    }

    public int scoreOf(String word){
        if(get(word) == null){
            return 0;
   
        }
        else{
            switch (word.length()) {
                case 0: return 0;
                case 1: return 0;
                case 2: return 0;
		    	case 3: return 1;
		    	case 4: return 1;
		    	case 5: return 2;
		    	case 6:	return 3;
		    	case 7:	return 5;
                default: return 11;
            }
            
        }

    }
    
    private String get(String key){
        Node x = get(root, key, 0);
        if(x == null){
            return null;
        }
        else{
            return x.word;
        }
    }
    private Node get(Node x, String key, int d){
        // if(x == null){
        //     return null;
        // }
        if(d == key.length()){
            return x;
        }
        else{
            char c = key.charAt(d);
            return get(x.next[c-65],key,d+1);
        }
    }

    private Node get(Node x, char key, int d){
        if(x == null){
            return null;
        }
        if(d == 1){
            return x;
        }
        else{
            return get(x.next[key-65],key,d+1);

        }
    }

    private void put(String key, String word){
        root = put(root,key,word,0);
    }

    private Node put(Node x, String key, String word, int d){
        if(x == null){
            x = new Node();
        }
        if(d == key.length()){
            x.word = word;
            return x;
        }
        char c = key.charAt(d);
        x.next[c-65] = put(x.next[c-65],key,word,d+1);
        return x;
    }
}