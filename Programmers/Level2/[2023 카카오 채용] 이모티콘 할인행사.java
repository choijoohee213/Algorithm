//순열로 이모티콘 할인율 결정

public class Solution {
    private int[][] usersInfo;
    private int[] emoticonsInfo;
    private int[] discountRate;
    private int emoticonPlus = 0, sales = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        usersInfo = users;
        emoticonsInfo = emoticons;
        int m = emoticons.length;
        discountRate = new int[m];

        setDiscountRate(m, 0);
        return new int[]{emoticonPlus, sales};
    }

    private void setDiscountRate(int m, int cnt) {
        if(cnt == m) {
            calResult();
            return;
        }

        for (int i = 10; i <= 40; i+=10) {
            discountRate[cnt] = i;
            setDiscountRate(m, cnt+1);
        }
    }

    private void calResult() {
        int sumEmotionPlus = 0;
        int sumSales = 0;

        for (int i = 0; i < usersInfo.length; i++) {
            int rate = usersInfo[i][0];
            int price = usersInfo[i][1];
            int sum = 0;

            for (int j = 0; j < discountRate.length; j++) {
                if(rate <= discountRate[j]) {
                    sum += emoticonsInfo[j] - (emoticonsInfo[j] * ((double)discountRate[j] / 100));
                    if(sum >= price) {
                        sum = 0;
                        sumEmotionPlus++;
                        break;
                    }
                }
            }
            sumSales += sum;
        }

        if(sumEmotionPlus > emoticonPlus) {
            emoticonPlus = sumEmotionPlus;
            sales = sumSales;
        } else if(sumEmotionPlus == emoticonPlus && sumSales > sales) {
            sales = sumSales;
        }
    }
}
