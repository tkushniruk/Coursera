package CompetitionProgramming;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ChoosingProblem {
    public static ArrayList<String> data = new ArrayList<>();
    public static int[] begin;
    public static int[] end;
    public static int maxCount(int n,ArrayList<Integer> outerIndexes){
        int cnt = 1;
        int last = 0;
        ArrayList<Integer> localIndexes = new ArrayList<>();
        for(int i  : outerIndexes){
            if(begin[i] >= end[last]){
                cnt++;
                last = i;
            }else {
                localIndexes.add(i);
            }
        }
        outerIndexes.removeAll(localIndexes);
        return cnt;
    }

    public static int minCount(int n, ArrayList<Integer> outerIndexes){
        int cnt = outerIndexes.size();
        int count = 0;

        while(count != outerIndexes.size()) {
            count = outerIndexes.size();
            int last = outerIndexes.get(0);
            ArrayList<Integer> localIndexes = new ArrayList<>();

            if(count == localIndexes.size()){
                cnt+=localIndexes.size();
                break;
            }else {
                cnt++;
            }
            outerIndexes.clear();
            outerIndexes.addAll(localIndexes);
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
                System.out.println(n);
                begin = new int[n];
                end = new int[n];
                data = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    data.add( scanner.nextLine());
                }
                data.sort((s, t1) -> {
                    int a = Integer.parseInt(s.split(" ")[1]);
                    int b = Integer.parseInt(t1.split(" ")[1]);
                    return a-b;
                });
                ArrayList<Integer> outerIndexes = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    String[] str = data.get(i).split(" ");
                    begin[i] = Integer.parseInt(str[0]);
                    end[i] = Integer.parseInt(str[1]);
                    outerIndexes.add(i);
                }

                System.out.println("Result#"+(k+1)+": "+maxCount(n) + " " + minCount(n,outerIndexes));

            }

        }catch(FileNotFoundException ex){
            System.out.println("FILE NOT FOUND");
        }

    }
}
