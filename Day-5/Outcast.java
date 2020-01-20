public class Outcast{
    private final WordNet wn;
    public Outcast(WordNet wn){
        this.wn = wn;
    }
    public String outcast(String[] nouns) {
        int d = 0;
        String outcast = null;
        for(String i : nouns){
            int distance = 0;
            for(String j : nouns){
                int distLength = wn.distance(i,j);
                distance = distance + distLength;

            }
            if(distance > d){
                d = distance;
                outcast = i;
            }
        }
        return outcast;
    }
}