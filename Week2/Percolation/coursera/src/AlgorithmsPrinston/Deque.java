package AlgorithmsPrinston;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    public Deque(){
        first = null;
        n = 0;
        last = null;
        assert check();
    }
    public boolean isEmpty(){
        return first == null && last == null;
    }
    public int size(){
        return n;
    }
    public void addFirst(Item item)  {
        if(item == null) throw new IllegalArgumentException("Item is null");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
        if(n == 1){
            last = first;
            first.next = last;
            last.previous = first;
            first.previous = null;
            last.next = null;
        }else {
            oldfirst.previous = first;
        }
        assert  check();
    }
    public void addLast(Item item){
        if(item == null) throw new IllegalArgumentException("Item is null");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        n++;
        if(n == 1){
            first = last;
            last.previous = first;
            first.next = last;
            first.previous = null;
            last.next = null;
        }else {
            oldlast.next = last;
            last.previous = oldlast;
        }
        assert check();
    }

    private boolean check() {
        if(n < 0){
            return false;
        }
        if( n == 0) {
            if (first != null || last != null ) return false;
        }else if(n == 1){
            if( first == null || last == null || first != last) return false;
            if(first.next != null || last.next != null || first.previous != null || last.previous != null) return false;
        }else {
            if (first == null || last == null || first.previous != null || last.previous == null) return false;
            if (first.next == null || last.next != null) return false;
        }
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next){
            numberOfNodes++;
        }
        if(numberOfNodes != n) return false;

        return true;
    }

    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        if(first.next != null){
            first = first.next;
            first.previous = null;
        }else {
            first = null;
        }
        n--;
        assert  check();
        return item;
    }
    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = last.item;
        if(last.previous != null){
            last = last.previous;
            last.next = null;
        }
        else last = null;
        n--;
        assert check();
        return item;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    public static void main(String[] args){
        Deque<String> deque = new Deque<>();
        assert deque.isEmpty();
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader("tale.txt"));
            String str = "";
            while(scanner.hasNextLine()){
                str += scanner.nextLine();
            }
            String[] strArr = str.split(" ");
            for(String elem : strArr){
                deque.addFirst(elem);
            }
            for(String elem : deque){
                System.out.println(elem);
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}
