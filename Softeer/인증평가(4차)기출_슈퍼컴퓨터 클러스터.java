import java.util.*;
import java.io.*;


public class Main
{
    static int n;
    static long b;
    static int[] arr;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long left = 1, right = 2000000000;
        long mid = 0, result = 0;

        while(left <= right) {
            mid = (left + right) / 2;
            long val = cal(mid);

            if(val > b) {
                right = mid - 1;
            } else {
                result = mid;
                left = mid + 1;
            }
        }
        System.out.print(result);
    }

    private static long cal(long min) {
        long sum = 0;

        for(int i=0; i<n; i++) {
            if(min <= arr[i]) {
                continue;
            }
            long price = (long)Math.pow(min - arr[i], 2);
            if(sum + price > b) {
                return b+1;
            }
            sum += price;
        }
        return sum;
    }
}