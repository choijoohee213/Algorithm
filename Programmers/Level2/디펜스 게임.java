import java.util.*;

public class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0, len = enemy.length;
        if(k >= len) {
            return len;
        }

        int sum = 0;
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < len; i++) {
            sum += enemy[i];
            pq.add(enemy[i]);

            while(sum > n && !pq.isEmpty()) {
                sum -= pq.poll();
                if(k == 0) {
                    return i;
                }
                k--;
            }
        }
        return len;
    }
}