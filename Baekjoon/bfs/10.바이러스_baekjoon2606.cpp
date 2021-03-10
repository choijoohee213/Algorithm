#include<bits/stdc++.h>
using namespace std;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int c, n, answer = 0;
	cin>>c>>n;
	vector<int> network[c];
	vector<bool> d(c,false);
		
	for(int i=0; i<n; i++){
		int a,b;
		cin>>a>>b;
		network[a-1].push_back(b-1);
		network[b-1].push_back(a-1);
	}
	
	queue<int> q;
	q.push(0);
	d[0] = true;
	while(!q.empty()){
		int x = q.front(); q.pop();

		for(int nx : network[x]){
			if(d[nx]) continue;
			d[nx] = true;
			answer++;
			q.push(nx);
		}
	}
	cout<<answer;
}
