public class QuickUnionUF
{
   private int[] id;
   private int[] sz;
   private final int N;
   private final int M;
   private int[] friendsId;
   private int[] timestamps;
   private boolean allConnected;
   public int input(){
     return (new Scanner(System.in)).nextInt();
   }
   
   public void read(){
     File file = new File("log.txt");
     Scanner input = new Scanner(file);
     int i = 0;
     while (input.hasNextLine()) {
       String line = input.nextLine();
       String[] args = line.split(" ");
       timestamps[i] = args[0];
       friendsId[0] = Integer.parseInt(args[1]);
       friendsId[0] = Integer.parseInt(args[2]);
       i++;
     }
     file.close();     
   }
   
   public QuickUnionUF(int N)
   {
     id = new int[N];
     friendsId = new int[2];
     friendsId[0] = new int[M];
     friendsId[1] = new int[M];
   timestamps = new int[M];
     for (int i = 0; i < N; i++) {
       id[i] = i;
       sz[i] = 1;      
     }
   }
   private int root(int i)
   {
     while (i != id[i]){
       id[i] = id[id[i]];
       i = id[i];       
     }
     return i;
   }
   public boolean connected(int p, int q)
   {
    return root(p) == root(q);
   }
   
   public int find(int i){
    
   }
   
   public void union(int p, int q)
   {
     int i = root(p);
     int j = root(q);
     if ( i == j ) return;
     if (sz[i] < sz[j])
     {  
      id[i] = j;
      sz[j] = += sz[i]; 
     }
     else 
     { 
      id[j] = i; sz[i] += sz[j];
     }
     if(sz[i] == N || sz[j] == N) allConnected = true;
   }
   
   public static void main(String[] args){
     System.out.println("INPUT N");
     N = input();
     System.out.println("INPUT M");
     M = input();
     read();
     int i = 0;
     while(!allConnected){
       union(friendsId[0][i],friendsId[0][j]);
       i++;
     }     
     System.out.println("Timestamp is: " + timestamps[i-1]);
   }
}