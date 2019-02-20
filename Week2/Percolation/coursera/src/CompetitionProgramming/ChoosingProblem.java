package CompetitionProgramming;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ChoosingProblem {
    public static ArrayList<String> data = new ArrayList<>();
    public static int[] begin;
    public static int[] end;
    public static int maxCount(int n){
        int cnt = 1;
        int last = 0;
        for(int i = 1; i < n; i++){
            if(begin[i] >= end[last]){
                last = i;
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String args[]) {
        try {
            Scanner scanner;
            int n;
            for(int k = 0; k < 2; k++){
                scanner = new Scanner(new FileReader("choosing"+(k+1)+".txt"));
                n = Integer.parseInt(scanner.nextLine());
                begin = new int[n];
                end = new int[n];
                for(int i = 0; i < n; i++){
                    data.add( scanner.nextLine());
                }
                data.sort((s, t1) -> {
                    int a = Integer.parseInt(s.split(" ")[1]);
                    int b = Integer.parseInt(t1.split(" ")[1]);
                    return a-b;
                });
                for(int i = 0; i < n; i++){
                    String[] str = data.get(i).split(" ");
                    begin[i] = Integer.parseInt(str[0]);
                    end[i] = Integer.parseInt(str[1]);
                }

                System.out.println("Result#"+(k+1)+": "+maxCount(n));
            }

        }catch(FileNotFoundException ex){
            System.out.println("FILE NOT FOUND");
        }

    }
}
