#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, m, answer = 999999999;
	cin >> n >> m;
	vector<vector<bool>> path(n + 1, vector<bool>(m + 1, false));
	vector<vector<int>> d(n + 1, vector<int>(m + 1, 1));

	int dx[4] = {0, 1, 0, -1}, dy[4] = {1, 0, -1, 0};

	for (int i = 1; i <= n; i++) {
		string s;
		cin>>s;
		for (int j = 1; j <= m; j++)
		  if(s[j-1] == '1') path[i][j] = true;
	}

	queue<pair<int, int>> q;
	q.push(make_pair(1, 1));
	path[1][1] = false;
	
	while (!q.empty()) {
		pair<int, int> p = q.front();
		q.pop();
		int k = d[p.first][p.second];

		if (p.first == n && p.second == m) {
			answer = min(answer, k);
		}
    
		for (int i = 0; i < 4; i++) {
			int x = p.first + dx[i];
			int y = p.second + dy[i];

			if (x > 0 && x <= n && y > 0 && y <= m && path[x][y]) {
				d[x][y] = k + 1;
				path[x][y] = false;
				q.push(make_pair(x, y));
			}
		}
	}
	cout << answer;
}
