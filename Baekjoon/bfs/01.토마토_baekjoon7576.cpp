#include<bits/stdc++.h>
using namespace std;

int m,n,answer=0;
int d[1001][1001];
int check[1001][1001];
int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};
queue<pair<int,int>> q;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin>>n>>m;
	for(int i=0; i<m; i++){
		for(int j=0; j<n; j++){
			cin >> d[i][j];
			if(d[i][j] == 1) q.push(make_pair(i,j));
			else if(d[i][j] == 0) check[i][j] = -1;
		}
	}

	while(!q.empty()){
		pair<int,int> loc = q.front(); q.pop();
		for(int i=0; i<4; i++){
			int x = loc.first + dx[i];
			int y = loc.second + dy[i];
			
			if(x<0 || x>=m || y<0 || y>=n || check[x][y] >= 0) continue;
			q.push(make_pair(x,y));
			check[x][y] = check[loc.first][loc.second]+1;
		}
	}
	
	for(int i=0; i<m; i++){
		for(int j=0; j<n; j++){
			if(check[i][j] == -1){ cout<<"-1"; return 0;}
			if(check[i][j] > answer) answer = check[i][j];
		}	
	}
	cout<<answer;
}
