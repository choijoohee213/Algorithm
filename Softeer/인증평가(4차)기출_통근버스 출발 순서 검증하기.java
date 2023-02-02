import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        int[][] arr = new int[n+1][n+1];

        for(int i=0; i<n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=n; i++) {
            for(int j=n-1; j>=0; j--) {
                arr[i][j] = arr[i][j+1];
                if(nums[j]<i) {
                    arr[i][j]++;
                }
            }
        }

        long result = 0;
        for(int i=0; i<n-2; i++) {
            for(int j=i; j<n-1; j++) {
                if(nums[i]<nums[j]) {
                    result += arr[nums[i]][j];
                }
            }
        }
        System.out.print(result);
    }
}