#include <bits/stdc++.h>
using namespace std;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n,m, answer=0;
	cin>>n>>m;
	vector<int> d[n];
	vector<bool> visited(n, false);
	queue<int> q;

	for(int i=0; i<m; i++){
		int a,b;
		cin>>a>>b;
		d[a-1].push_back(b-1);
		d[b-1].push_back(a-1);
	}
	
	for(int i=0; i<n; i++){
		if(visited[i]) continue;
		q.push(i);
		visited[i] = true;
		answer++;
		
		while(!q.empty()){
			int k = q.front(); q.pop();
			for(int j : d[k]){
				if(visited[j]) continue;
				q.push(j);
				visited[j] = true;
			}
		}
	}
	cout<<answer;
}
