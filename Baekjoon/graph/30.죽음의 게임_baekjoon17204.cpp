#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int n,k,m=1;
    cin>>n>>k;
    vector<int> v(n);
    vector<bool> visited(n, false);

    for(int i=0; i<n; i++){
        cin>>v[i];
    }

    int num = v[0];
    visited[0] = true;
    while(true){
        if(num == k){
            cout<<m;
            return 0;
        }
        
        if(visited[num]){
            cout<<-1;
            return 0;
        }
        visited[num] = true;
        num = v[num];
        m++;
    }
}