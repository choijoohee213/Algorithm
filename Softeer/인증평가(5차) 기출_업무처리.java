import java.util.*;
import java.io.*;

public class Main
{
    static int h,k,r,n,result = 0;
    static Queue<int[]>[] taskQueue;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        n = (int)Math.pow(2, h+1) - 1;
        taskQueue = new Queue[n];

        for(int i=0; i<n; i++) {
            taskQueue[i] = new LinkedList<>();
        }

        for(int i=n-(int)Math.pow(2,h); i<n; i++) { //맨 왼쪽 부하직원부터
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<k; j++) {
                taskQueue[i].add(new int[]{Integer.parseInt(st.nextToken())});
            }
        }

        for(int i=1; i<r; i++) {
            doTask(i);
        }

        if(!taskQueue[0].isEmpty()) {
            if(r % 2 == 1) {
                if(taskQueue[0].peek()[0] != -1) {
                    result += taskQueue[0].poll()[0];
                }
            } else {
                if(taskQueue[0].peek()[1] != -1) {
                    result += taskQueue[0].poll()[1];
                }
            }
        }

        System.out.print(result);
    }

    private static void doTask(int day) {
        for(int i=0; i<n; i+=2) { //0,2,4,6...
            if(i == 0) {
                if(!taskQueue[0].isEmpty()) {
                    int left = taskQueue[0].peek()[0];
                    int right = taskQueue[0].peek()[1];
                    if(day % 2 == 1) {
                        result += left;
                        taskQueue[0].peek()[0] = -1;
                        if(right == -1) {
                            taskQueue[0].poll();
                        }
                    } else {
                        result += right;
                        taskQueue[0].peek()[1] = -1;
                        if(left == -1) {
                            taskQueue[0].poll();
                        }
                    }
                }
                continue;
            }


            int parent = (i-2)/2;
            int left = -1,right = -1;
            if(i >= (n-(int)Math.pow(2,h))) { //말단
                if(!taskQueue[i-1].isEmpty()) {
                    left = taskQueue[i-1].poll()[0];
                }
                if(!taskQueue[i].isEmpty()) {
                    right = taskQueue[i].poll()[0];
                }
            } else {
                if(!taskQueue[i-1].isEmpty()) {
                    int left_left = taskQueue[i-1].peek()[0];
                    int left_right = taskQueue[i-1].peek()[1];
                    if(day % 2 == 1) {
                        left = left_left;
                        taskQueue[i-1].peek()[0] = -1;
                        if(left_right == -1) {
                            taskQueue[i-1].poll();
                        }
                    } else {
                        left = left_right;
                        taskQueue[i-1].peek()[1] = -1;
                        if(left_left == -1) {
                            taskQueue[i-1].poll();
                        }
                    }
                }
                if(!taskQueue[i].isEmpty()) {
                    int right_left = taskQueue[i].peek()[0];
                    int right_right = taskQueue[i].peek()[1];
                    if(day % 2 == 1) {
                        right = right_left;
                        taskQueue[i].peek()[0] = -1;
                        if(right_right == -1) {
                            taskQueue[i].poll();
                        }

                    } else {
                        right = right_right;
                        taskQueue[i].peek()[1] = -1;
                        if(right_left == -1) {
                            taskQueue[i].poll();
                        }
                    }
                }
            }
            if(left != -1 && right != -1) {
                taskQueue[parent].add(new int[]{left, right});
            }
        }
    }
}