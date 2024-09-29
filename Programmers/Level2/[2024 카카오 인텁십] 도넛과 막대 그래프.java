import java.util.*;

//임의의 생성한 정점은 방문수가 0일거임. 다른곳으로 향하는 방향밖에없으니
//1. 생성 정점 알아내기
//2. 생성 정점에서 시작해서 그래프 별로 돌기
//3. 도넛의 경우, 출발햇던 정점으로 돌아오는게 1개
//   막대의 경우, 출발햇던 정점 돌아오지도 않고, 방문수가 1씩임
//   8자의 경우, 출발했던 정점으로 돌아오는게 2개 이상

class Solution {
    List<Integer>[] graph;
    int cnt = 0;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        graph = new List[1000001];

        for (int i = 0; i < 1000001; i++) {
            graph[i] = new ArrayList<>();
        }

        // 도넛, 막대, 8자
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            graph[a].add(b);
        }

        boolean[] checked = new boolean[1000001];
        for (int i = 0; i < 1000001; i++) {
            if (graph[i].isEmpty() || checked[i]) {
                continue;
            }
            findCreatedNum(i, checked);
        }

        int createdNum = 0;
        for (int i = 0; i < 1000001; i++) {
            if (graph[i].size() >= 2 && !checked[i]) {
                createdNum = i;
                answer[0] = createdNum;
            }
        }

        int[] visited = new int[1000001];
        for (int start : graph[createdNum]) {
            dfs(start, visited);
            if(cnt == 0) {
                answer[2]++;
            } else if(cnt == 1) {
                answer[1]++;
            } else {
                answer[3]++;
            }
            cnt = 0;
        }

        return answer;
    }

    void findCreatedNum(int num, boolean[] checked) {
        for (int next : graph[num]) {
            if (checked[next]) {
                continue;
            }
            checked[next] = true;
            findCreatedNum(next, checked);
        }
    }

    void dfs(int num, int[] visited) {
        if(++visited[num] >= 2) {
            cnt++;
            return;
        }
        for (int next : graph[num]) {
            dfs(next, visited);
        }
    }
}