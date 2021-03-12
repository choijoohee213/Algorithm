#include <bits/stdc++.h>
using namespace std;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n,m, answer = 0, zero=0;
	cin>>n>>m;
	vector<vector<int>> d(n, vector<int>(m, 0));
	vector<int> index;
	vector<pair<int,int>> virus;
	int dx[4] = {0, 1, 0, -1};
	int dy[4] = {1, 0, -1, 0};
	
	for(int i=0; i<n; i++){
		for(int j=0; j<m; j++){
			int x;
			cin>>x;
			d[i][j] = x;
			if(x==0) index.push_back(i*m+j);
			if(x==2) virus.push_back(make_pair(i,j));
		}			
	}
	zero = index.size()-3;

	for(int i=0; i<index.size(); i++){
		for(int j=i+1; j<index.size(); j++){
			for(int k=j+1; k<index.size(); k++){
				int n1 = index[i], n2 = index[j], n3 = index[k];				
				queue<pair<int,int>> q;
				vector<vector<int>> temp = d;
				int cnt = 0;
						
				temp[n1/m][n1%m] = 1;
				temp[n2/m][n2%m] = 1;
				temp[n3/m][n3%m] = 1;
		
				for(int x=0; x<virus.size(); x++){
					pair<int,int> p = virus[x];
					q.push(make_pair(p.first, p.second));
					
					while(!q.empty()){
						pair<int,int> pa = q.front(); q.pop();
						
						for(int y=0; y<4; y++){
							int nx = pa.first + dx[y];
							int ny = pa.second + dy[y];
							
							if(nx>=0 && nx<n && ny>=0 && ny<m 
								&& temp[nx][ny]==0){
								q.push(make_pair(nx, ny));
								temp[nx][ny] = 1;
								cnt++;	
							}
						}
					}
				}
				answer = max(answer,zero-cnt);
			}	
		}	
	}
	cout<<answer;	
}
