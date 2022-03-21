import java.io.*;
import java.util.*;

class cctv {
	public int x,y,num,dir;

	public cctv(int x, int y, int num, int dir) {
		this.x = x;
		this.y = y;
		this.num = num;
		this.dir = dir;
	}
}

class Main {
	static int n,m,result = Integer.MAX_VALUE,wall = 0;
	static int[][] arr;
	static List<cctv> cctvList = new ArrayList<>();
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 6) wall++;
				else if(arr[i][j] > 0) {
					cctvList.add(new cctv(i,j,arr[i][j],0)); //x,y,cctv 인덱스,방향
				}
			}
		}

		permutation(0);
		br.close();
		System.out.print(result);
	}

	static void permutation(int cnt) {
		if(cnt == cctvList.size()) {
			Set<Integer> canSee = new HashSet<>();
			for (cctv c : cctvList) {
				if(c.num == 1) checkArea(canSee, c);
				else if(c.num == 2) {
					checkArea(canSee, c);
					c.dir = (c.dir+2) % 4;
					checkArea(canSee, c);
				}
				else if(c.num == 3) {
					checkArea(canSee, c);
					c.dir = (c.dir+1) % 4;
					checkArea(canSee, c);
				}
				else if(c.num == 4 || c.num == 5) {
					checkArea(canSee,c);
					c.dir = (c.dir+1) % 4;
					checkArea(canSee,c);
					c.dir = (c.dir+1) % 4;
					checkArea(canSee,c);
					if(c.num == 5) {
						c.dir = (c.dir+1) % 4;
						checkArea(canSee,c);
					}
				}
			}
			result = Math.min(result, n*m - canSee.size() - wall);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if(cctvList.get(cnt).num == 2 && i>1) continue;
			if(cctvList.get(cnt).num == 5 && i>0) continue;
			cctvList.get(cnt).dir = i;
			permutation(cnt+1);
		}
	}

	static void checkArea(Set<Integer> canSee, cctv c) {
		int nx = c.x, ny = c.y;
		do {
			canSee.add(nx * m + ny);
			nx += dx[c.dir];
			ny += dy[c.dir];
		} while (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] != 6);
	}
}