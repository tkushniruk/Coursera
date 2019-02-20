package CompetitionProgramming;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ChoosingProblem {
    public static ArrayList<String> data = new ArrayList<>();
    public static int[] begin;
    public static int[] end;
    public static int maxCount(ArrayList<Integer> outerIndexes){
        //sort(outerIndexes);
        int last = outerIndexes.get(0);
        ArrayList<Integer> localIndexes = new ArrayList<>();
        outerIndexes.remove(0);
        for(int i  : outerIndexes){
            if(begin[i] >= end[last]){
                last = i;
            }else {
                localIndexes.add(i);
            }
        }
        outerIndexes.clear();
        outerIndexes.addAll(localIndexes);
        return 1;
    }

    public static int minCount( ArrayList<Integer> outerIndexes){
        int cnt = 0;

        while(!outerIndexes.isEmpty()) {
            maxCount(outerIndexes);
            cnt++;
        }
        return cnt;
    }

    public static int minCount2(){
        int n = begin.length;
        boolean[] isUsed = new boolean[n];
        int last = 0;
        int cnt = 1;
        for(int i = 0; i < n; i++){
            isUsed[i] = false;
        }
        boolean allTrue = true;
        while(true){
            for(int i = 1; i < n; i++){
                if(!isUsed[i]){
                    if(begin[i] >= end[last]){
                        last = i;
                        isUsed[i] = true;
                    }
                }
            }
            for (int i = 0; i < n; i++){
                if(!isUsed[i]){
                    allTrue = false;
                    last = i;
                    break;
                }
            }
            if(allTrue)break;
            allTrue = true;
            cnt++;
        }
        return cnt;

    }

    public static void main(String args[]) {
        try {
            Scanner scanner;
            int n;
            for(int k = 0; k < 3; k++){
                scanner = new Scanner(new FileReader("choosing"+(k+1)+".txt"));
                n = Integer.parseInt(scanner.nextLine());
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

                System.out.println("Result#"+(k+1)+": "+ minCount(outerIndexes));

            }

        }catch(FileNotFoundException ex){
            System.out.println("FILE NOT FOUND");
        }

    }
}
