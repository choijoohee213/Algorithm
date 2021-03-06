#include <bits/stdc++.h>
using namespace std;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n,m, maxValue = 0;
	cin>>n>>m;
	vector<vector<int>> trust(n+1);
	vector<int> answer(n+1);	
	queue<int> q;

	for(int i=0; i<m; i++){
		int a,b;
		cin>>a>>b;
		trust[b].push_back(a);	
	}
	
	for(int i=1; i<=n; i++){
		int cnt=0;
		bool visited[n+1] = {false,};
		q.push(i);
		visited[i] = true;
		
		while(!q.empty()){
			int k = q.front(); q.pop();
			
			for(int x : trust[k]){
				if(visited[x]) continue;
				visited[x] = true;
				cnt++;
				q.push(x);
			}
		}
		maxValue = max(maxValue, cnt);
		answer[i] = cnt;
	}
	
	for(int i=1; i<=n; i++){
		if(answer[i] == maxValue) cout<<i<<" ";
	}
}
