#include<bits/stdc++.h>
using namespace std;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	 
	int m,n,k,answer=0;
	cin>>m>>n>>k;
	vector<vector<bool>> map(n, vector<bool>(m, false));
	vector<int> v;
	queue<pair<int,int>> q;
	int dx[4] = {0, 1, 0, -1};
	int dy[4] = {1, 0, -1, 0};
	
	for(int i=0; i<k; i++){
		int x1,y1,x2,y2;
		cin>>x1>>y1>>x2>>y2;
		
		for(int j=x1; j<x2; j++){
			for(int k=y1; k<y2; k++){
				map[j][k] = true;
			}
		}
	}
	
	for(int i=0; i<n; i++){
		for(int j=0; j<m; j++){
			if(map[i][j]) continue;
			answer++;
			int cnt = 1;
			map[i][j] = true;
			q.push(make_pair(i,j));
			
			while(!q.empty()){
				pair<int,int> p = q.front(); q.pop();
				
				for(int k=0; k<4; k++){
					int nx = dx[k] + p.first;
					int ny = dy[k] + p.second;
					
					if(nx>=0 && nx<n && ny>=0 && ny<m && !map[nx][ny]){
						cnt++;
						map[nx][ny] = true;
						q.push(make_pair(nx,ny));
					} 
				}	
			}		
			v.push_back(cnt);
		}
	}	
	cout<<answer<<'\n';
	sort(v.begin(), v.end());
	for(int i=0; i<v.size(); i++) cout<<v[i]<<" ";
}
