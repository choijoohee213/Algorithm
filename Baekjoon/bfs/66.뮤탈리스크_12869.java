import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

        boolean[][][] dp = new boolean[61][61][61];
        int[] arr = new int[3];
        for (int i = 0; i <n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{arr[0], arr[1], arr[2], 0});

        int[] cycle = {9,3,1};
        List<int[]> cycleOrder = new ArrayList<>();
        if(n == 3) {
            cycleOrder.add(new int[]{0,1,2});
            cycleOrder.add(new int[]{0,2,1});
            cycleOrder.add(new int[]{1,0,2});
            cycleOrder.add(new int[]{1,2,0});
            cycleOrder.add(new int[]{2,0,1});
            cycleOrder.add(new int[]{2,1,0});
        } else if(n == 2) {
            cycleOrder.add(new int[]{0,1});
            cycleOrder.add(new int[]{1,0});
        } else {
            cycleOrder.add(new int[]{0});
        }

        while(!queue.isEmpty()) {
            int[] curArr = {queue.peek()[0],queue.peek()[1],queue.peek()[2]};
            int cnt = queue.peek()[3];
            queue.poll();

            if(dp[curArr[0]][curArr[1]][curArr[2]]) {
                continue;
            }
            dp[curArr[0]][curArr[1]][curArr[2]] = true;
            boolean finished = true;
            for(int i=0; i<3; i++) {
                if(curArr[i] > 0) {
                    finished = false;
                    break;
                }
            }

            if(finished) {
                System.out.print(cnt);
                return;
            }

            for (int i = 0; i < cycleOrder.size(); i++) {
                int[] order = cycleOrder.get(i);
                finished = false;
                int[] nextArr = {curArr[0], curArr[1], curArr[2]};

                for (int j = 0; j < order.length; j++) {
                    nextArr[j] -= cycle[order[j]];
                    if(nextArr[j] < 0) {
                        nextArr[j] = 0;
                    }
                }

                queue.add(new int[]{nextArr[0], nextArr[1], nextArr[2], cnt+1});
            }


        }

		br.close();
		System.out.print(sb);
	}
}