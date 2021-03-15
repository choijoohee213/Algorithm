#include<bits/stdc++.h>
using namespace std;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, maxNum=0, answer=1;
	cin>>n;
	int dx[4] = {0,1,0,-1};
	int dy[4] = {1,0,-1,0};
	vector<vector<int>> map(n, vector<int>(n, 0));
	
	for(int i=0; i<n; i++){
		for(int j=0; j<n; j++){
			int x;
			cin>>x;
			map[i][j] = x;
			maxNum = max(x, maxNum);
		}
	}
	
	for(int i=1; i<=maxNum; i++){
		int cnt = 0;
		queue<pair<int,int>> q;
		vector<vector<int>> d = map;
		
		for(int j=0; j<n; j++){
			for(int k=0; k<n; k++){
				if(d[j][k]<=i) continue;
				
				q.push(make_pair(j,k));
				d[j][k] = i;
				cnt++;
				
				while(!q.empty()){
					pair<int,int> p = q.front(); q.pop();
					
					for(int a=0; a<4; a++){
						int nx = p.first + dx[a];
						int ny = p.second + dy[a];
						
						if(nx>=0 && nx<n && ny>=0 && ny<n && d[nx][ny]>i){
							d[nx][ny] = i;
							q.push(make_pair(nx,ny));
						}
					}
				}
			}
		}
		answer = max(cnt, answer);
	}
	cout<<answer;
}
