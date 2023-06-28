class Solution {
    int answer;
    int[][] weakArr;

    public int solution(int n, int[] weak, int[] dist) {
        weakArr = new int[weak.length][weak.length];
        answer = dist.length + 1;

        for (int i = 0; i < weak.length; i++) {
            for (int j = 0; j < weak.length; j++) {
                weakArr[i][j] = weak[(i+j) % weak.length];
                if((i+j) >= weak.length) {
                    weakArr[i][j] += n;
                }
            }
        }

        permutation(0, dist, new int[dist.length], new boolean[dist.length]);
        return answer == dist.length + 1 ? -1 : answer;
    }

    private void permutation(int cnt, int[] dist, int[] arr, boolean[] visited) {
        if(cnt == dist.length) {
            check(arr);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            arr[cnt] = dist[i];
            permutation(cnt+1, dist, arr, visited);
            visited[i] = false;
        }
    }

    private void check(int[] arr) {
        loop:
        for (int[] weak : weakArr) {
            int idx = 0, pos = weak[0] + arr[idx];

            for (int i = 1; i < weak.length; i++) {
                if(pos >= weak[i]) continue;
                if(++idx >= arr.length) {
                    continue loop;
                }
                pos = weak[i] + arr[idx];
            }
            answer = Math.min(answer, idx+1);
        }
    }
}