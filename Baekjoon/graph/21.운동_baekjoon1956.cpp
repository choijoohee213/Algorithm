#include <bits/stdc++.h>
using namespace std;
#define INF 987654321

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int v, e, result=INF;
	cin >> v >> e;
	vector<vector<int>> d(v + 1, vector<int>(v + 1, INF));

	for (int i = 0; i < e; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		d[a][b] = min(d[a][b], c);
	}

	for (int k = 1; k <= v; k++) {
		for (int i = 1; i <= v; i++) {
			for (int j = 1; j <= v; j++) {
			  if(d[i][k] == INF || d[k][j] == INF) continue;
				d[i][j] = min(d[i][j], d[i][k] + d[k][j]);
			}
		}
	}

	for (int i = 1; i <= v; i++) {
		if(d[i][i]!=INF){
		  result = min(result, d[i][i]);
		}
	}
	if(result == INF) cout<<-1;
	else cout<<result;
}
