#include<bits/stdc++.h>
using namespace std;

int n,k,result = 0, value;
vector<int> v;

void dfs(int num, int ten){
    if(num>n) return;
    result = max(result, num);

    for(int i=0; i<k; i++)
        dfs(num + ten * v[i], ten * 10);
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    cin>>n>>k;
    for(int i=0; i<k; i++){
        int x;
        cin>>x;
        v.push_back(x);
    }
    sort(v.begin(), v.end());
    dfs(0, 1);
    cout<<result;
}