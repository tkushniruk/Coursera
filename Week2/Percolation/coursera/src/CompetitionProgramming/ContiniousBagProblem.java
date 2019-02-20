package CompetitionProgramming;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ContiniousBagProblem {

    public static int[] costs;
    public static int[] weights;
    public static ArrayList<String> data;
    public static long maxWeight(int W){
        int n = costs.length;
        long maxCost = 0;
        data = new ArrayList<>();
        for(int i = 0; i < n; i++){
            data.add(""+weights[i]+" "+costs[i]+" "+costs[i]/weights[i]);
        }
        data.sort((o1, o2) -> {
            int a = Integer.parseInt(o1.split(" ")[2]);
            int b = Integer.parseInt(o2.split(" ")[2]);
            return b - a ;
        });
        int i = 0;
        while(W > 0){
           String[] str = data.get(i).split(" ");
           i++;
           int cost = Integer.parseInt(str[1]);
           int weight = Integer.parseInt(str[0]);
           if(W - weight >= 0){
               maxCost += cost;
               W -= weight;
           }else {
               maxCost += W * cost / weight;
               break;
           }
        }
        return maxCost;
    }
    public static void main(String args[]) {
        try {
            Scanner scanner;
            int n;
            int W;
            for(int k = 0; k < 2; k++){
                scanner = new Scanner(new FileReader("continious_bag"+(k+1)+".txt"));
                String[] str = scanner.nextLine().split(" ");
                n = Integer.parseInt(str[0]);
                W = Integer.parseInt(str[1]);
                costs = new int[n];
                weights = new int[n];
                for(int i = 0; i < n; i++){
                    str = scanner.nextLine().split(" ");
                    costs[i] = Integer.parseInt(str[1]);
                    weights[i] = Integer.parseInt(str[0]);
                }
                System.out.println("Result#"+(k+1)+": "+maxWeight(W));
            }

        }catch(FileNotFoundException ex){
            System.out.println("FILE NOT FOUND");
        }
    }
}
