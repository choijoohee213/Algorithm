#include <bits/stdc++.h>
using namespace std;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int dx[9] = {-1,0,1,-1,0,1,-1,0,1};
	int dy[9] = {-1,-1,-1,0,0,0,1,1,1};
	
	while(true){				
		int w,h, answer=0;
		cin>>w>>h;
		if(w == 0 && h == 0) return 0;
		
		vector<vector<bool>> map(h, vector<bool>(w, false));
		vector<pair<int,int>> islands;
		queue<pair<int,int>> q;
		
		for(int i=0; i<h; i++){
			for(int j=0; j<w; j++){
				int x;
				cin>>x;
				if(x) {
					map[i][j] = true;
					islands.push_back(make_pair(i,j));
				}
			}
		}
		
		for(int i=0; i<islands.size(); i++){
			pair<int,int> sp = islands[i];
			if(!map[sp.first][sp.second]) continue;
			
			q.push(make_pair(sp.first, sp.second));
			map[sp.first][sp.second] = false;
			answer++;
			
			while(!q.empty()){
				pair<int,int> p = q.front(); q.pop();
				
				for(int j=0; j<9; j++){
					int nx = p.first + dx[j];
					int ny = p.second + dy[j];
					
					if(nx>=0 && nx<h && ny>=0 && ny<w && map[nx][ny]){
						map[nx][ny] = false;
						q.push(make_pair(nx,ny));
					}
				}
			}
		}	
		cout<<answer<<'\n';
	}
}
