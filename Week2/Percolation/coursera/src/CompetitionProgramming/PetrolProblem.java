package CompetitionProgramming;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class PetrolProblem {
    public static int[] petrolsDifLengths;

    public static int minPetrolCount(int n, int lenAtoB, int maxCarLen){
        int cnt = 1;
        int lastPetrol = 0;
        for(int i = 0; i < n; i++){
            int diff = petrolsDifLengths[i] - lastPetrol;
            if(maxCarLen - diff < 0){
                lastPetrol = petrolsDifLengths[i-1];
                cnt++;
            }else if(petrolsDifLengths[i] + maxCarLen > lenAtoB)
            {
                break;

            }
        }
        return cnt;
    }

    public static void main(String[] args){
        Scanner sc;
        int n;
        int lenAtoB;
        int maxCarLen;
        for(int k = 1; k <= 2; k++){
            try {
                sc = new Scanner(new FileReader("petrol" + k + ".txt"));
                String[] str = sc.nextLine().split(" ");
                n = Integer.parseInt(str[0]);
                lenAtoB = Integer.parseInt(str[1]);
                maxCarLen = Integer.parseInt(str[2]);
                petrolsDifLengths = new int[n];
                String strArgs = sc.nextLine();
                str = strArgs.split(" ");
                for(int i = 0; i < n; i++){
                    petrolsDifLengths[i] = Integer.parseInt(str[i]);
                }
                System.out.println("Result#"+k+" : "+minPetrolCount(n,lenAtoB,maxCarLen));
            }catch(FileNotFoundException ex){
                System.out.println("File not found");
            }
        }
    }
}
