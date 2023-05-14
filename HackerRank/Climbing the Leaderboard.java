import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        int rank = 1;
        int[] ranks = new int[ranked.size()];
        ranks[0] = 1;

        for(int i=1; i<ranked.size(); i++) {
            if(!ranked.get(i-1).equals(ranked.get(i))) {
                rank++;
            }
            ranks[i] = rank;
        }


        List<Integer> newRanked = new ArrayList<>();
        int idx = 0;
        for(int i=ranked.size()-1; i>=0; i--) {
            if(idx >= player.size()) break;
            int score = player.get(idx);
            if(ranked.get(i) == score) {
                newRanked.add(ranks[i]);
                idx++;
                i++;
            } else if(ranked.get(i) > score) {
                newRanked.add(ranks[i] + 1);
                idx++;
                i++;
            }
        }

        while(idx++ < player.size()) {
            newRanked.add(1);
        }

        return newRanked;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
