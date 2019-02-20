package AlgorithmsPrinston;

public class Queue {
    private Node begin;
    private Node end;
    Queue(String item){
        System.out.println("INIT QUEUE with item " + item);
        begin = new Node(item);
        end = begin;
    }
    public void push(String item){
        System.out.println("PUSH "+item);
        Node newNode = new Node(item);
        begin.previous = newNode;
        begin = newNode;
    }
    public String pop(){
        String item = end.getItem();
        end = end.previous;
        return item;
    }
    public static void main(String[] args){
        Queue queue = new Queue("HELLO");
        queue.push("world!");
        System.out.print(queue.pop()+ " ");
        System.out.print(queue.pop() + "\n");
    }

}
