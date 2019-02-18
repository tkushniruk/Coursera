import java.util.HashMap;
import java.util.Scanner;
public class QuickUnionUF
{
   static private int[] id;
   static private int[] sz;
   static private final int N = 10;
   static public  HashMap<Integer,Integer> map;
   public int input(){
     return (new Scanner(System.in)).nextInt();
   }
  
    public QuickUnionUF(int N)
   {
     id = new int[N];
     for (int i = 0; i < N; i++) {
       id[i] = i;
       sz[i] = 1;      
     }
   }
   static private int root(int i)
   {
     while (i != id[i]){
       id[i] = id[id[i]];
       i = id[i];       
     }
     return i;
   }
   static public boolean connected(int p, int q)
   {
    return root(p) == root(q);
   }
   
   static public int find(int x){
    map = new HashMap<Integer,Integer>();
    for(int i = 0; i < id.length; i++){
        int root = root(i);
        Integer val = map.get(root);
        if(val == null)map.put(id[root],i);
        else {
            if(i > val)map.merge(id[root],i,(ov,nv) -> nv);
        }
    }
    for(Integer elem : map.keySet()){
        System.out.print("KEY: " + elem + "   VALUE: " + map.get(elem));
        System.out.println("");
    }
    System.out.println(root(x));
    return map.get(root(x));
   }
   
   static public void union(int p, int q)
   {
     int i = root(p);
     int j = root(q);
     if ( i == j ) return;
     if (sz[i] < sz[j])
     {  
      id[i] = j;
      sz[j] += sz[i]; 
     }
     else 
     { 
      id[j] = i; sz[i] += sz[j];
     }
   }
   
   public static void main(String[] args){
     System.out.println("INPUT N");
     N = input();
     MyClass = new MyClass();
   }
}