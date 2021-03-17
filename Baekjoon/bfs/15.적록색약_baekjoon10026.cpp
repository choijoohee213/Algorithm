#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, answer=0;
	cin>>n;
	vector<vector<int>> image(n, vector<int>(n, 0));
	vector<vector<bool>> visited(n, vector<bool>(n, false));
	int dx[4] = {0, 1, 0, -1};
	int dy[4] = {1, 0, -1, 0};
	queue<pair<int,int>> q;
	
	//적록색약 아닌사람
	for(int i=0; i<n; i++){
	  string s;
	  cin>>s;
	  for(int j=0; j<n; j++){
	    if(s[j]=='R') image[i][j] = 1; 
	    else if(s[j]=='G') image[i][j] = 2;
	    else image[i][j] = 3;
	  }
	}
	
	for(int i=0; i<n; i++){
	  for(int j=0; j<n; j++){
	    if(visited[i][j]) continue;
	    visited[i][j] = true;
	    answer++;
	    q.push(make_pair(i,j));
	    
	    while(!q.empty()){
	      pair<int,int> p = q.front(); q.pop();
	      int color = image[p.first][p.second];
	      
	      for(int k=0; k<4; k++){
	        int nx = p.first + dx[k];
	        int ny = p.second + dy[k];
	        
	        if(nx>=0 && nx<n && ny>=0 && ny<n && !visited[nx][ny] && color==image[nx][ny]){
	          visited[nx][ny] = true;
	          q.push(make_pair(nx,ny));
	        }
	      }
	    }
	  }
	}
	cout<<answer<<" ";
	answer = 0;
	
	//적록색약
	for(int i=0; i<n; i++){
	  fill(visited[i].begin(), visited[i].end(), false);
	  for(int j=0; j<n; j++)
	    if(image[i][j] == 2) image[i][j] = 1;
	}
	
	for(int i=0; i<n; i++){
	  for(int j=0; j<n; j++){
	    if(visited[i][j]) continue;
	    visited[i][j] = true;
	    answer++;
	    q.push(make_pair(i,j));
	    
	    while(!q.empty()){
	      pair<int,int> p = q.front(); q.pop();
	      int color = image[p.first][p.second];
	      
	      for(int k=0; k<4; k++){
	        int nx = p.first + dx[k];
	        int ny = p.second + dy[k];
	        
	        if(nx>=0 && nx<n && ny>=0 && ny<n && !visited[nx][ny] && color==image[nx][ny]){
	          visited[nx][ny] = true;
	          q.push(make_pair(nx,ny));
	        }
	      }
	    }
	  }
	}
	cout<<answer;
}
