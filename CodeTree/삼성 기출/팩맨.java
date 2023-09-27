import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * 1. 몬스터 복제 : 자신과 같은방향 가진 알 부화
 * 2. 몬스터 이동
 * 	- 가진 방향대로 한칸 이동 (8방 이동)
 * 	- 가려는 칸에 몬스터 시체/팩맨/바깥이면 반시계 방향으로 45도 회전(갈수있을때까지 계속 회전)
 * 	- 모두 움직일 수 없었다면 안움직임
 * 3. 팩맨 이동
 * 	- 3칸이동. 각 이동마다 4방. 가장 많이 먹을 수 있는 방향으로 움직임(여러개면 상좌하우 순)
 * 	- 몬스터 먹으면 시체남김 (알,움직이기전에 같이있었던 몬스터 안먹음)
 * 4. 몬스터 시체 소멸 : 2턴지나면 소멸
 * 5. 알 부화
 * 모든 턴 진행 후 살아남은 몬스터 마리 수
 */

public class Main {
	static int m, playerR, playerC, mIdx = 0, mCnt = -1;
	static int[] dx = {-1,-1,0,1,1,1,0,-1}, dy = {0,-1,-1,-1,0,1,1,1}, playerPath;
	static Map<Integer, List<Monster>> lived = new HashMap<>();
	static Map<Integer, List<Monster>> eggs = new HashMap<>();
	static Map<Integer, List<Monster>> died = new HashMap<>();

	static class Monster {
		int idx;
		int r,c;
		int dir;
		int diedAt = -5;

		public Monster(int r, int c, int dir) {
			this.idx = mIdx++;
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Monster monster = (Monster) o;
			return idx == monster.idx;
		}

		@Override
		public int hashCode() {
			return Objects.hash(idx);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		playerR = Integer.parseInt(st.nextToken()) - 1;
		playerC = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;

			Monster monster = new Monster(r, c, d);

			if(!lived.containsKey(r*4+c)) {
				lived.put(r*4+c, new ArrayList<>());
			}
			lived.get(r*4+c).add(monster);
		}

		while(t-- > 0) {
			spawnMonster(); //1. 몬스터 복제
			moveMonster(); //2. 몬스터 이동
			movePlayer(t); //3. 팩맨 이동
			disappearMonster(t); //4. 몬스터 소멸
			hatchOutMonster();  //5. 몬스터 부화
		}

		int result = 0;
		for (List<Monster> monsterList : lived.values()) {
			result += monsterList.size();
		}

		br.close();
		System.out.print(result);
	}

	// 1. 몬스터 복제 : 자신과 같은방향 가진 알 부화
	private static void spawnMonster() {
		for (Map.Entry<Integer, List<Monster>> entry : lived.entrySet()) {
			for (Monster monster : entry.getValue()) {
				if(!eggs.containsKey(monster.r*4+ monster.c)) {
					eggs.put(monster.r*4+ monster.c, new ArrayList<>());
				}
				eggs.get(monster.r*4+ monster.c).add(new Monster(monster.r, monster.c, monster.dir));
			}
		}
	}

	/*
	 * 2. 몬스터 이동
	 * 	- 가진 방향대로 한칸 이동 (8방 이동)
	 * 	- 가려는 칸에 몬스터 시체/팩맨/바깥이면 반시계 방향으로 45도 회전(갈수있을때까지 계속 회전)
	 * 	- 모두 움직일 수 없었다면 안움직임
	 */
	private static void moveMonster() {
		Map<Integer, List<Monster>> newLived = new HashMap<>();
		for (Map.Entry<Integer, List<Monster>> entry : lived.entrySet()) {
			for (Monster monster : entry.getValue()) {
				int dir = monster.dir;
				for (int k = 0; k < 8; k++) {
					int nx = monster.r + dx[dir];
					int ny = monster.c + dy[dir];

					if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || isDiedMonster(nx, ny) || isPlayer(nx, ny)) {
						dir = (dir + 1) % 8;
						continue;
					}
					monster.r = nx;
					monster.c = ny;
					monster.dir = dir;
					break;
				}

				if(!newLived.containsKey(monster.r*4+monster.c)) {
					newLived.put(monster.r*4+monster.c, new ArrayList<Monster>());
				}
				newLived.get(monster.r*4+monster.c).add(monster);
			}
		}
		lived = newLived;
	}
	private static boolean isPlayer(int x, int y) {
		return playerR == x && playerC == y;
	}
	private static boolean isDiedMonster(int x, int y) {
		return died.containsKey(x*4+y);
	}

	/*
	 * 3. 팩맨 이동
	 * 	- 3칸이동. 각 이동마다 4방. 가장 많이 먹을 수 있는 방향으로 움직임(여러개면 상좌하우 순)
	 * 	- 몬스터 먹으면 시체남김 (알,움직이기전에 같이있었던 몬스터 안먹음)
	 */
	private static void movePlayer(int t) {
		mCnt = -1;
		findPath(0, 0, playerR, playerC, new int[3], new boolean[4][4]);

		int nx = playerR, ny = playerC;
		for (int dir : playerPath) {
			nx += dx[dir];
			ny += dy[dir];

			if(!lived.containsKey(nx*4+ny)) continue;
			for (Monster mon : lived.get(nx*4+ny)) {
				mon.diedAt = t;

				if(!died.containsKey(nx*4+ny)) {
					died.put(nx*4+ny, new ArrayList<>());
				}
				died.get(nx*4+ny).add(mon);
			}
			lived.remove(nx*4+ny);
		}
		playerR = nx;
		playerC = ny;
	}

	private static void findPath(int cnt, int monster, int x, int y, int[] path, boolean[][] visited) {
		if(cnt == 3) {
			if(monster > mCnt) {
				mCnt = monster;
				playerPath = path.clone();
			}
			return;
		}
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i*2];
			int ny = y + dy[i*2];

			if(nx<0 || nx>=4 || ny<0 || ny>=4) continue;

			path[cnt] = i*2;
			int num = 0;
			if(!visited[nx][ny] && lived.containsKey(nx*4+ny)) {
				num += lived.get(nx*4+ny).size();
			}
			findPath(cnt+1, monster+num, nx, ny, path, visited);
		}
		visited[x][y] = false;
	}

	// 4. 몬스터 시체 소멸 : 2턴지나면 소멸
	private static void disappearMonster(int t) {
		Map<Integer, List<Monster>> newDied = new HashMap<>();
		for (Entry<Integer, List<Monster>> entry : died.entrySet()) {
			List<Monster> newList = new ArrayList<>(entry.getValue());
			for (Monster monster : entry.getValue()) {
				if(monster.diedAt - 2 != t) continue;
				newList.remove(monster);
			}

			if(!newList.isEmpty()) {
				newDied.put(entry.getKey(), newList);
			}
		}
		died = newDied;
	}

	//5. 몬스터 알 부화
	private static void hatchOutMonster() {
		for (List<Monster> monsterList : eggs.values()) {
			for (Monster monster : monsterList) {
				if(!lived.containsKey(monster.r*4+monster.c)) {
					lived.put(monster.r*4+monster.c, new ArrayList<>());
				}
				lived.get(monster.r*4+ monster.c).add(monster);
			}
		}
		eggs.clear();
	}
}