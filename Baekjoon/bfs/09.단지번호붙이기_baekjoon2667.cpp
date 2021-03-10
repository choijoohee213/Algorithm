#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, answer = 0;
	cin >> n;
	vector<vector<bool>> path(n, vector<bool>(n, false));
    vector<int> v;
	int dx[4] = {0, 1, 0, -1}, dy[4] = {1, 0, -1, 0};

	for (int i = 0; i < n; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < n; j++)
			if (s[j] == '1')
				path[i][j] = true;
	}

	queue<pair<int, int>> q;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if(!path[i][j]) continue;
			answer++;
			int cnt = 1;
			path[i][j] = false;
			q.push(make_pair(i,j));
			
			while (!q.empty()) {
				pair<int, int> p = q.front();
				q.pop();

				for (int k = 0; k < 4; k++) {
					int x = p.first + dx[k];
					int y = p.second + dy[k];

					if (x >= 0 && x < n && y >= 0 && y < n && path[x][y]) {
						path[x][y] = false;
						cnt++;
						q.push(make_pair(x, y));
					}
				}
			}
			v.push_back(cnt);
		}
	}

	sort(v.begin(), v.end());
	cout<<answer<<'\n';
	for(int i=0; i<answer; i++)
	  cout<<v[i]<<'\n';
}
