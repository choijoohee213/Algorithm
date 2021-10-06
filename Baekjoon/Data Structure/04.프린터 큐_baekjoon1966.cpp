#include<bits/stdc++.h>
using namespace std;

int t,n,m;

void print(){
	int cnt = 0;
	queue<pair<int,int>> q;
	priority_queue<int> pq;

	for(int i=0; i<n; i++){
		int x;
		cin>>x;
		q.push({x, i});
		pq.push(x);
	}

	while(!q.empty()){
		int x = q.front().first;
		int y = q.front().second; q.pop();

		if(pq.top() != x){
			q.push({x,y});
		}
		else{
			cnt++;
			pq.pop();
			if(y == m) {
				cout<<cnt<<"\n";
				return;
			}
		}
	}
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    cin>>t;

    while(t--){
      cin>>n>>m;
			print();
    }
}