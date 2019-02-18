public class MyClass {
    public static int n = 25;
    public static int m = 8;
    public static int pos = 1;
    public static int[] arr = new int[n];
    public static void main(String args[]) {
        rec(0,0,false,1);
        rec(0,0,false,0);
    }
    
    public static void rec(int idx,  int cnt, boolean isOne, int num){
        if(cnt == m){
            
            if(pos == 24008)out();
            pos++;
            return;
        }
        if(idx == n)return;
        if(!isOne && num == 1){
                arr[idx] = 1;
                cnt++;
                rec(idx + 1, cnt, true, 0);
        }else{
                arr[idx] = 0;
                rec(idx + 1, cnt, false, 1);
                rec(idx + 1, cnt, false, 0);
        }
        
        
    }
    
    public static void out(){
        System.out.print("#"+pos+":");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.println("");
    }
}
