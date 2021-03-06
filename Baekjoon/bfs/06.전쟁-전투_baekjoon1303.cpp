#include <bits/stdc++.h>
using namespace std;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int m,n;
	int dx[4] = {0, 1, 0, -1}, dy[4] = {1, 0, -1, 0};
	int answer[2] = {0, 0};
	cin>>n>>m;
	
	vector<vector<bool>> soliders(m+1, vector<bool>(n+1, false));
	vector<vector<bool>> visited(m+1, vector<bool>(n+1, false));
	queue<pair<int,int>> q;

	for(int i=0; i<m; i++){
		for(int j=0; j<n; j++){
			char s;
			cin>>s;
			if(s == 'W') soliders[i][j] = true;
		}
	}
	
	for(int i=0; i<m; i++){
		for(int j=0; j<n; j++){
			if(visited[i][j]) continue;
			
			int cnt = 0;
			bool s;
			visited[i][j] = true;
			q.push(make_pair(i,j));
			
			while(!q.empty()){
				cnt++;
				pair<int,int> p = q.front(); q.pop();
				s = soliders[p.first][p.second];
				
				for(int k=0; k<4; k++){
					int nx = p.first + dx[k];
					int ny = p.second + dy[k];
					
					if(nx<m && nx>=0 && ny<n && ny>=0 && 
						soliders[nx][ny] == s && !visited[nx][ny]){
						
						visited[nx][ny] = true;
						q.push(make_pair(nx,ny));
						
					}
				}
			}
			answer[s] += cnt*cnt;
		}
	}

	cout<<answer[1]<<" "<<answer[0];
}
