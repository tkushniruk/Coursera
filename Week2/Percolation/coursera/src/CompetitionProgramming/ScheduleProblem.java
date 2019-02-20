package CompetitionProgramming;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleProblem{
    public static ArrayList<String> data = new ArrayList<>();
    public static int[] costs;
    public static int[] terms;
    public static boolean[] used;
    public static long maxCost(){
        int n = costs.length;
        long maxCost = 0;
        for(int i = 0; i < n; i++){
            int j = terms[i];
            while(j >= 1 && used[j]){
                j--;
            }
            if(j == 0){
                continue;
            }
            used[j] = true;
            maxCost += (long)costs[i];
        }
        return maxCost;
    }
    public static void main(String args[]) {
        try {
            Scanner scanner;
            int n;
            for(int k = 0; k < 2; k++) {
                scanner = new Scanner(new FileReader("schedule"+(k+1)+".txt"));
                n = Integer.parseInt(scanner.nextLine());
                costs = new int[n];
                terms = new int[n];
                used = new boolean[n];
                for(int i = 0; i < n; i++){
                    data.add(scanner.nextLine());
                }
                data.sort((s, t1) -> {
                    int a = Integer.parseInt(s.split(" ")[1]);
                    int b = Integer.parseInt(t1.split(" ")[1]);
                    return b-a;
                });
                for (int i = 0; i < n; i++) {
                    used[i] = false;
                    String[] strArr = data.get(i).split(" ");
                    terms[i] = Integer.parseInt(strArr[0]);
                    costs[i] = Integer.parseInt(strArr[1]);
                }
                //System.out.println(data.toString());
                System.out.println(maxCost());
            }
        }catch(FileNotFoundException ex){
            System.out.println("FILE NOT FOUND");
        }

    }
}
