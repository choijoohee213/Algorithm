//최대로 실어서 제일 멀리 있는데 배달 후 , 제일 멀리 있는데서 최대한 수거
//멀리있는 곳부터 포인터를 지정해서 0/0일 경우 포인터를 -1 하도록..?

public class Solution {
    /*public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int idx = n-1;
        int deliverTotalBox = 0, pickupTotalBox = 0;

        for (int i = 0; i < n; i++) {
            if(deliveries[i] > 0) {
                deliverTotalBox+= deliveries[i];
            }
            if(pickups[i] > 0) {
                pickupTotalBox+= pickups[i];
            }
        }

        while(idx >= 0) {
            if (deliveries[idx] == 0 && pickups[idx] == 0) {
                idx--;
                continue;
            }

            int pointer = idx, idx2 = idx;
            answer += (2 * (idx + 1));
            int deliverBox = cap, emptyBox = 0;
            while (pointer >= 0) {
                if (deliverTotalBox > 0 && deliverBox != 0 && deliveries[pointer] > 0) {
                    if (deliveries[pointer] >= deliverBox) {
                        deliveries[pointer] -= deliverBox;
                        deliverTotalBox -= deliverBox;
                        deliverBox = 0;
                    } else {
                        deliverBox -= deliveries[pointer];
                        deliverTotalBox -= deliveries[pointer];
                        deliveries[pointer] = 0;
                    }
                }
                if (pickupTotalBox > 0 && emptyBox != cap && pickups[pointer] > 0) {
                    if (pickups[pointer] >= (cap - emptyBox)) {
                        pickups[pointer] -= (cap - emptyBox);
                        pickupTotalBox -= (cap - emptyBox);
                        emptyBox = cap;
                    } else {
                        emptyBox += pickups[pointer];
                        pickupTotalBox -= pickups[pointer];
                        pickups[pointer] = 0;
                    }
                }
                if(deliveries[pointer] == 0 && pickups[pointer] == 0) {
                    idx--;
                }
                if ((deliverBox == 0 && emptyBox == cap) || (deliverTotalBox == 0 && emptyBox == cap)
                        || (pickupTotalBox == 0 && deliverBox == cap) || (deliverTotalBox == 0 && pickupTotalBox == 0)) {
                    break;
                }
                pointer--;
            }
        }
        return answer;
    }
*/

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int deliver = 0, pickup = 0;
        long answer = 0;

        for (int i = n-1; i >= 0; i--) {
            if(deliveries[i] != 0 || pickups[i] != 0) {
                int cnt = 0;
                while(deliver < deliveries[i] || pickup < pickups[i]) {
                    cnt++;
                    deliver += cap;
                    pickup += cap;
                }
                deliver -= deliveries[i];
                pickup -= pickups[i];
                answer += (i+1) * cnt * 2;
            }
        }
        return answer;
    }
}
