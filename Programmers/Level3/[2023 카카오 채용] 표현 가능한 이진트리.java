import java.util.*;

class Solution {
    int result = 1;

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i=0; i< numbers.length; i++) {
            result = 1;
            int[] binary = getBinary(numbers[i]);
            inorder(0, binary.length-1, false, binary);
            answer[i] = result;
        }

        return answer;
    }

    private void inorder(int start, int end, boolean isParentZero, int[] binary) {
        if(result == 0) {
            return;
        }

        int mid = (start + end) / 2;

        if(isParentZero && binary[mid] == 1) {
            result = 0;
            return;
        }

        if(start != end) {
            inorder(start, mid-1, binary[mid] == 0, binary);
            inorder(mid+1, end, binary[mid] == 0, binary);
        }
    }

    private int[] getBinary(long number) {
        List<Integer> binary = new ArrayList<>();

        while(number > 0) {
            binary.add((int)(number % 2));
            number /= 2;
        }

        int size = binary.size();
        int i = 1;
        int cur = 0;
        while(cur < binary.size()) {
            cur = (int) (Math.pow(2, i++)) - 1;
        }

        for (int j = 0; j < cur - size; j++) {
            binary.add(0);
        }

        int[] newBinary = new int[binary.size()];
        for (i = 0; i < binary.size(); i++) {
            newBinary[i] = binary.get(binary.size() - i - 1);
        }
        return newBinary;
    }
}