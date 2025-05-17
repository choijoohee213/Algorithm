import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class Main {
    static int parent[];


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
        parent = new int[n+1];
        int[][] d = new int[n+1][n+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                d[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i <m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            d[a][b] = 1;
            d[b][a] = 1;

            if(!isSameParent(a, b)) {
                unionParent(a, b);
            }
        }

        Map<Integer, List<Integer>> commitee = new HashMap<>();
        for(int i=1; i<=n; i++) {
            int commiteeIndex = getParent(i);

            if (!commitee.containsKey(commiteeIndex)) {
                commitee.put(commiteeIndex, new ArrayList<>());
            }
            commitee.get(commiteeIndex).add(i);
        }
        System.out.println(commitee.size());


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if(d[i][j] == Integer.MAX_VALUE || d[j][k] == Integer.MAX_VALUE) continue;
                    d[i][k] = Math.min(d[i][k], d[i][j] + d[j][k]);
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for (Entry<Integer, List<Integer>> entry : commitee.entrySet()) {
            List<Integer> members = entry.getValue();
            int minVal = Integer.MAX_VALUE;
            int king = members.get(0);

            for (int member1 : members) {
                int maxVal = 0;
                for (int member2 : members) {
                    if(member1 == member2) {
                        continue;
                    }

                    maxVal = Math.max(maxVal, d[member1][member2]);
                }
                if(minVal > maxVal) {
                    minVal = maxVal;
                    king = member1;
                }
            }
            result.add(king);
        }

        result.sort(Comparator.naturalOrder());

        for(int king : result) {
            System.out.println(king);
        }
		br.close();
	}

    private static void unionParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static boolean isSameParent(int a, int b) {
        return getParent(a) == getParent(b);
    }

    private static int getParent(int x) {
        if(parent[x] == x) {
            return x;
        }
        return parent[x] = getParent(parent[x]);
    }
}