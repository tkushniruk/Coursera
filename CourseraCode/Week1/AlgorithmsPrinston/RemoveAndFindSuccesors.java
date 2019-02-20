import java.util.*;
public class MyClass {
    private static int N;
    private static HashMap<Integer,Integer> map;
    private static ArrayList<Integer> list;
    private static int[] forRemove = new int[]{1,3,5,8,4,2,6};
    public MyClass(){
        map = new HashMap<Integer,Integer>(N);
        list = new ArrayList<Integer>();
        for(int i = 0; i < N; i++){
            Integer iI = Integer.valueOf(i);
            map.put(iI,iI);
            list.add(iI);
        }
    }
    
    public static void remove(Integer x){
        map.remove(x,x);
    }
    
    public static Integer find(Integer x){
        Integer y = map.get(x);
        if(y != null) return y;
        int i = 1;
        Integer elem = list.get(x+i);
        y = map.get(elem);
        while(y == null && ( (x+i) < N) ){
            elem = list.get(x+i);
            y = map.get(elem);
            i++;
        }
        if(y == null) return Integer.valueOf(N);
        else return y;
    }
    
    
    public static void main(String args[]) {
        /*Scanner sc = new Scanner(System.in);
        System.out.println("Input N");
        N = sc.nextInt();
        MyClass m = new MyClass();
        for(int elem : forRemove){
            remove(elem);
        }
        
        int ans;
        do{
            System.out.println("What u want to find?");
            ans = sc.nextInt();
            if(ans == N){System.out.println("EXIT");break;}
            System.out.println("y =: " + find(ans));
        }while(true);*/
        N = 100000;
        long sum = 0;
        for(int i = 0; i < 222; i++){
            long start = System.currentTimeMillis();
            MyClass my = new MyClass();
            map = new HashMap<Integer,Integer>();
            map.put(0,0);
            map.put(N-1,N-1);
            find(1);
            long finish = System.currentTimeMillis();
            sum += finish - start;
            
        }
        System.out.println("AVG: ="+(sum/100.0));
    }
}
