#include <bits/stdc++.h>
using namespace std;

vector<int> answer;

void dfs(int k, int& n, vector<vector<bool>>& d, vector<bool>& visited){
	for(int i=1; i<n+1; i++){
		if(!d[k][i] || visited[i]) continue;
		
		visited[i] = true;
		answer.push_back(i);
		dfs(i,n,d,visited);
	}	
}

int  main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n,m,v;
	cin>>n>>m>>v;
	vector<vector<bool>> d(n+1, vector<bool>(n+1, false));
	vector<bool> visited(n+1, false);	

	for(int i=0; i<m; i++){
		int a,b;
		cin>>a>>b;
		d[a][b] = true;
		d[b][a] = true;
	}
	
	//dfs
	visited[v] = true;
	answer.push_back(v);
	dfs(v,n,d,visited);
	
	for(int i=0; i<answer.size(); i++)
		cout<<answer[i]<<" ";
	cout<<'\n';
	
	
	//bfs
	answer.clear();
	fill(visited.begin(), visited.end(), false);
	queue<int> q;
	
	q.push(v);
	visited[v] = true;
	answer.push_back(v);
	
	while(!q.empty()){
		int k = q.front(); q.pop();
		
		for(int i=1; i<n+1; i++){
			if(!d[k][i] || visited[i]) continue;
			
			q.push(i);
			visited[i] = true;
			answer.push_back(i);
		}	
	}
	
	for(int i=0; i<answer.size(); i++)
		cout<<answer[i]<<" ";		
}
