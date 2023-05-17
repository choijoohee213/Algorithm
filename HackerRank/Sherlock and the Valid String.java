import java.io.*;
import java.util.*;

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
        int[] count = new int[27];
        for(int i=0; i<s.length(); i++) {
            ++count[s.charAt(i) - 'a'];
        }

        Map<Integer, Integer> countNums = new HashMap<>();
        for(int i=0; i<27; i++) {
            if(count[i] == 0) continue;
            countNums.put(count[i], countNums.getOrDefault(count[i], 0) + 1);
        }

        if(countNums.size() == 1) {
            return "YES";
        } else if(countNums.size() == 2) {
            int[] cntInfo = new int[2];
            int[] kindInfo = new int[2];
            int idx = 0;

            for (Map.Entry<Integer, Integer> entry : countNums.entrySet()) {
                cntInfo[idx] = entry.getKey();
                kindInfo[idx++] = entry.getValue();
            }

            if(kindInfo[0] == 1 || kindInfo[1] == 1) {
                if(kindInfo[0] == 1) {
                    if(cntInfo[0] == 1 || cntInfo[0] - 1 == cntInfo[1]) {
                        return "YES";
                    }
                } else {
                    if(cntInfo[1] == 1 || cntInfo[1] - 1 == cntInfo[0]) {
                        return "YES";
                    }
                }
            }
        }
        return "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
