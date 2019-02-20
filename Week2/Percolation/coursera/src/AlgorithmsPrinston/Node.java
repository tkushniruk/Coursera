package AlgorithmsPrinston;

public class Node {
    private String item;
    public Node previous;
    Node(String item){
        this.item = item;
        this.previous = null;
    }
    public String getItem(){
        return this.item;
    }
}
