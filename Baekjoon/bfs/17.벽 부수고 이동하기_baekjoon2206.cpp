#include<bits/stdc++.h>
using namespace std;

typedef struct pos{
	int x;
	int y;
	int breakingWall;
} pos;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n,m;
	cin>>n>>m;
	vector<vector<bool>> map(n, vector<bool>(m, false));
	vector<vector<vector<int>>> visited(n, vector<vector<int>>(
		m, vector<int>(2)));
	queue<pos> q;
	int dx[4] = {0, 1, 0, -1};
	int dy[4] = {1, 0, -1, 0};
	
	for(int i=0; i<n; i++){
		string s;
		cin>>s;
		for(int j=0; j<m; j++)
			if(s[j] == '1') map[i][j] = true;
	}
	
	visited[0][0][0] = 1;
	q.push({0,0,0});
	
	while(!q.empty()){
		pos p = q.front(); q.pop();
		
		if(p.x==n-1 && p.y==m-1) {
			cout<<visited[p.x][p.y][p.breakingWall];
			return 0;
		}
		
		for(int i=0; i<4; i++){
			pos np;
			np.x = dx[i] + p.x;
			np.y = dy[i] + p.y;
			np.breakingWall = p.breakingWall;
			
			if(np.x>=0 && np.x<n && np.y>=0 && np.y<m
				&& visited[np.x][np.y][np.breakingWall]==0){
				//다음 길이 벽이 아닌경우
				if(!map[np.x][np.y]){  
					visited[np.x][np.y][np.breakingWall] = visited[p.x][p.y][p.breakingWall] + 1;
					q.push({np.x, np.y, np.breakingWall});
				}
				//다음 길이 벽인데 한번도 벽을 부시지 않은 경우 
				else if(map[np.x][np.y] && np.breakingWall==0){
					visited[np.x][np.y][1] = visited[p.x][p.y][p.breakingWall] + 1;
					np.breakingWall = 1;
					q.push(np);
				}	
			}
		}
	}
	
	cout<<-1;
}
