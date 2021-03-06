#include <bits/stdc++.h>
using namespace std;

int answer = 0, m, n, k;
bool farm[51][51] = {false,};
int dx[4] = {0, 1, 0, -1}, dy[4] = {1, 0, -1, 0};

void bfs(int i, int j){	
	for(int w=0; w<4; w++){
		int nx = i + dx[w];
		int ny = j + dy[w];
		
		if(nx>=0 && nx<n && ny>=0 && ny<m && farm[nx][ny]){
			farm[nx][ny] = false;
			bfs(nx,ny);
		}
	}	
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	int t;
	cin>>t;
	
	while(t--){
		answer = 0;
		cin>>m>>n>>k;
		for(int i=0; i<k; i++){
			int x,y;
			cin>>y>>x;
			farm[x][y] = true;
		}	
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(farm[i][j]){
					answer++;
					farm[i][j] = false;
					bfs(i,j);
				} 
			}
		}
		cout<<answer<<endl;
	}	
}
