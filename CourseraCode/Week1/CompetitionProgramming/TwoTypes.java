public class MyClass {
    public static int n = 7;
    public static int pos = 1;
    public static int[] arr = new int[n];
    public static char[] s = new char[2*n];
    public static void main(String args[]) {
       rec(0,0,0,false,2);
       rec(0,0,0,true,1);
       
    }
    
    public static void rec(int idx, int bal1, int bal2, boolean ch, int whichFirst){
        if(idx == 2 * n){
            if(bal1 == 0 && bal2 == 0){
                if(pos == 8233)out();
                pos++;
            }
            return;
        }
        if(bal1 > n || bal2 > n)return;
        if(ch){
            s[idx] = '[';
            rec(idx+1,bal1+1,bal2,false,whichFirst);
            rec(idx+1,bal1+1,bal2,true,whichFirst);
            if(bal1 == 0 )return;
            if((whichFirst == 1 && bal2 == 0) || (whichFirst == 2 && bal2 > 0) ){
                if(idx + 1 == n * 2) {
                    s[idx] = ']';
                    rec(idx+1, bal1 - 1, bal2, true, 1);
                }else {
                    s[idx] = ']';
                    if(whichFirst == 1 && (bal1 - 1 == 0)){
                       rec(idx+1,bal1-1,bal2,false,2);
                       rec(idx+1,bal1-1,bal2,true,1);
                    }else {
                        rec(idx+1,bal1-1,bal2,false,whichFirst);
                        rec(idx+1,bal1-1,bal2,true,whichFirst);
                    }
                }
            }
        }else {
            s[idx] = '(';
            rec(idx+1,bal1,bal2+1,false,whichFirst);
            rec(idx+1,bal1,bal2+1,true,whichFirst);
            if(bal2 == 0 )return;
            if((whichFirst == 1 && bal1 > 0) || (whichFirst == 2 && bal1 == 0)){
                if(idx+1 == 2 * n){
                    s[idx] = ')';
                    rec(idx+1,bal1,bal2-1,true,1);
                }else {
                    s[idx] = ')';
                     if( (whichFirst == 2) && (bal2 - 1 == 0) ){
                        rec(idx+1,bal1,bal2-1,false,2);
                        rec(idx+1,bal1,bal2-1,true,1);
                    }else {
                        rec(idx+1,bal1,bal2-1,false,whichFirst);
                        rec(idx+1,bal1,bal2-1,true,whichFirst);
                    }
                }
            }
        }
        
        
        
    }
    
    public static void out(){
        System.out.print("#"+pos+":");
        for(int i = 0; i < s.length; i++){
            System.out.print(s[i]);
        }
        System.out.println("");
    }
    public static void out2(){
        System.out.print("#"+pos+":");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.println("");
    }
    
}
