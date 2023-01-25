import java.util.*;

public class Solution {
    public int solution(int[] order) {
        int answer = 0, idx = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 1; i <= order.length; i++) {
            if(order[idx] == i) {
                answer++;
                idx++;
            } else {
                st.add(i);
            }
            while(!st.isEmpty() && st.peek() == order[idx]) {
                answer++;
                idx++;
                st.pop();
            }
            if(answer == order.length) {
                break;
            }
        }

        return answer;
    }
}