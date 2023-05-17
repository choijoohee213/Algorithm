import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    public static int lilysHomework(int n, List<Integer> arr) {
        int cnt1 = countSwap(new ArrayList<>(arr), Comparator.naturalOrder());
        int cnt2 = countSwap(new ArrayList<>(arr), Comparator.reverseOrder());
        return Math.min(cnt1, cnt2);
    }

    private static int countSwap(List<Integer> arr, Comparator<Integer> comparator) {
        Map<Integer, Integer> sortedMap = new TreeMap<>(comparator);

        for(int i = 0; i< arr.size(); i++) {
            sortedMap.put(arr.get(i), i);
        }

        int left = 0, cnt = 0;
        for (Entry<Integer, Integer> en : sortedMap.entrySet()) {
            int idx = en.getValue();

            if(idx != left) {
                int tmp = arr.get(idx);
                arr.set(idx, arr.get(left));
                sortedMap.put(arr.get(left), idx);
                arr.set(left, tmp);
                cnt++;
            }
            left++;
        }
        return cnt;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.lilysHomework(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
