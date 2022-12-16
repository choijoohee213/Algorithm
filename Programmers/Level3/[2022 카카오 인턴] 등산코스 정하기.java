/*
import java.util.*;

class Solution {
    private int N, resultNode = Integer.MAX_VALUE, minIntensity = Integer.MAX_VALUE, curSummit;
    private List<Integer>[] node;
    private int[][] cost;
    private Set<Integer> gateSet = new HashSet<>();
    private Set<Integer> summitSet = new HashSet<>();
    private boolean flag;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        N = n;
        node = new List[n+1];
        cost = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            node[i] = new ArrayList<>();
        }

        for (int gate : gates) {
            gateSet.add(gate);
        }

        for (int summit : summits) {
            summitSet.add(summit);
        }

        for (int[] path : paths) {
            node[path[0]].add(path[1]);
            node[path[1]].add(path[0]);
            cost[path[0]][path[1]] = path[2];
            cost[path[1]][path[0]] = path[2];
        }

        for (int gate : gates) {
            boolean[] visited = new boolean[n+1];
            visited[gate] = true;
            upDfs(gate, gate, 0, visited);
        }
        return new int[]{resultNode, minIntensity};
    }

    private void upDfs(int x, int start, int intensity, boolean[] visited) {
        if(summitSet.contains(x)) {
            boolean[] newVisited = new boolean[N+1];
            newVisited[x] = true;
            curSummit = x;
            flag = false;
            downDfs(x, start, intensity, newVisited);
            return;
        }

        for (int i = 0; i < node[x].size(); i++) {
            int next = node[x].get(i);
            if(gateSet.contains(next) || visited[next]) continue;
            visited[next] = true;
            upDfs(next, start, Math.max(intensity, cost[x][next]), visited);
            visited[next] = false;
        }
    }

    private void downDfs(int x, int start, int intensity, boolean[] visited) {
        if(flag) return;

        if(gateSet.contains(x)) {
            if(minIntensity > intensity) {
                minIntensity = intensity;
                resultNode = curSummit;
                flag = true;
            } else if(minIntensity == intensity && resultNode > curSummit) {
                resultNode = curSummit;
                flag = true;
            }
        }

        for (int i = 0; i < node[x].size(); i++) {
            int next = node[x].get(i);
            if(summitSet.contains(next) || (gateSet.contains(next) && next != start) || visited[next]) continue;
            visited[next] = true;
            downDfs(next, start, Math.max(intensity, cost[x][next]), visited);
            visited[next] = false;
        }
    }
}*/


import java.util.*;

class Node {
    int e,w;

    public Node(int e, int w) {
        this.e = e;
        this.w = w;
    }
}

class Solution {
    private int resultNode = Integer.MAX_VALUE, minIntensity = Integer.MAX_VALUE;
    private List<Node>[] node;
    private int[] d;
    private Set<Integer> gateSet = new HashSet<>();
    private Set<Integer> summitSet = new HashSet<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        node = new List[n+1];
        d = new int[n+1];

        for (int i = 1; i <= n; i++) {
            node[i] = new ArrayList<>();
        }

        for (int gate : gates) {
            gateSet.add(gate);
        }

        for (int summit : summits) {
            summitSet.add(summit);
        }

        for (int[] path : paths) {
            if(gateSet.contains(path[0]) || summitSet.contains(path[1])) {
                node[path[0]].add(new Node(path[1], path[2]));
            } else if(gateSet.contains(path[1]) || summitSet.contains(path[0])) {
                node[path[1]].add(new Node(path[0], path[2]));
            } else {
                node[path[0]].add(new Node(path[1], path[2]));
                node[path[1]].add(new Node(path[0], path[2]));
            }
        }

        dijkstra(n, gates, summits);
        return new int[]{resultNode, minIntensity};
    }

    private void dijkstra(int n, int[] gates, int[] summits) {
        Queue<int[]> queue = new ArrayDeque<>();
        Arrays.fill(d, Integer.MAX_VALUE);

        for (int gate : gates) {
            queue.add(new int[]{gate, 0});
            d[gate] = 0;
        }

        while(!queue.isEmpty()) {
            int e = queue.peek()[0];
            int w = queue.peek()[1];
            queue.poll();

            if(d[e] < w) continue;

            for (Node next : node[e]) {
                int dis = Math.max(d[e], next.w);
                if(d[next.e] > dis) {
                    d[next.e] = dis;
                    queue.add(new int[]{next.e, dis});
                }
            }
        }

        Arrays.sort(summits);
        for (int summit : summits) {
            if(minIntensity > d[summit]) {
                minIntensity = d[summit];
                resultNode = summit;
            }
        }
    }
}