import java.io.*;
import java.util.*;

/**
 * dfs ?
 * 보석 줍고 안줍고 두가지 재귀 호출?
 * dp ? [노드번호][현재가지고있는보석개수]
 * 이미 갔던 곳은 그 뒤로도 보석을 몇개가져올 수 있는지 저장하여 다시안가도록?
 */
class Main {
    static int[] jewels;
    static List<int[]>[] edges;
    static boolean[][] visited;
    static int result = 0, last;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        jewels = new int[n+1];
        edges = new List[n+1];
        visited = new boolean[n+1][1<<k];

        for (int i = 1; i <= k; i++) {
            jewels[Integer.parseInt(br.readLine())] = i;
        }

        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new int[]{b,c});
            edges[b].add(new int[]{a,c});
        }
        dfs(1, 0, 0);
        if(jewels[1] != 0 && (last & 1) == 0) {
            result++;
        }
		br.close();

		System.out.print(result);
	}

    private static void dfs(int node, int state, int jewelCnt) {
        visited[node][state] = true;

        if(node == 1) {
            if(result <= jewelCnt) {
                result = jewelCnt;
                last = state;
            }
        }

        for (int[] next : edges[node]) {
            int nextNode = next[0];
            int val = next[1];

            if(!visited[nextNode][state] && val>=jewelCnt) {
                dfs(nextNode, state, jewelCnt);
            }
            if(jewels[node] != 0) {
                if((state & (1<<(jewels[node]-1))) > 0) continue;
                int nextState = (state | (1<<(jewels[node]-1)));
                if(!visited[nextNode][nextState] && val >= jewelCnt+1) {
                    dfs(nextNode, nextState, jewelCnt+1);
                }
            }
        }
    }
}
