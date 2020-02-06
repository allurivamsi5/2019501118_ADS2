public class TrieST{
    
    public String get(String key){
        Node x = get(root, key, 0);
        if(x == null){
            return null;
        }
        else{
            return x.word;
        }
    }
    public Node get(Node x, String key, int d){
        if(x == null){
            return null;
        }
        if(d == key.length()){
            return x;
        }
        else{
            char c = key.charAt(d);
            return get(x.next[c-65],key,d+1);
        }
    }

    public Node get(Node x, char key, int d){
        if(x == null){
            return null;
        }
        if(d== 1){
            return x;
        }
        else{
            return get(x.next[key-65],key,d+1);

        }
    }

    public void put(String key, String word){
        root = put(root,key,word,0);
    }

    public Node put(Node x, String key, String word, int d){
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