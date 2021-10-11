#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
	
    int n,x;
    cin>>n;
    while(n--){
        cin>>x;
        if(x==0){
			if(pq.empty()){
                cout<<0<<'\n';
            }
            else {
                cout<<pq.top().second<<'\n';
                pq.pop();
            }
        }
        else{
			pq.push({abs(x), x});
        }
    }

}