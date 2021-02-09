#include <bits/stdc++.h>
#define MAX 100000
using namespace std;

int n,k;
int d[MAX+2] = {0,};

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> k;
	
	queue<int> q;
	q.push(n);
	d[n] = 1;
	while(d[k]==0){
		int loc = q.front(); q.pop();
		for(int nxt : {loc-1, loc+1, loc*2}){
			if(nxt<0 || nxt>MAX || d[nxt]!=0) continue;
			d[nxt] = d[loc]+1;
			q.push(nxt);
		}
	}
	cout<<d[k]-1;
}
