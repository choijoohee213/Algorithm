#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int n, m;
    cin>>n;

    unordered_map<int, vector<int>> uom;

    for(int i=1; i<=n; i++){
        int x;
        cin>>x;
        uom[x].push_back(i);
    }
    
    cin>>m;
    for(int i=1; i<=m; i++){
        int x;
        cin>>x;
        if(uom[x].size() == 0) cout<<-1;
        else{
            for(int j : uom[x]) cout<<j<<" ";
        }
        cout<<'\n';
    }
}