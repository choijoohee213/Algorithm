#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int n;
    long long ans = 0;
    cin>>n;
    vector<int> v(n);
    for(int i=0; i<n; i++){
        cin>>v[i];
    }
    sort(v.begin(), v.end());

    for(int i=0; i<n; i++){
        ans += (long long)abs(v[i] - (i+1));
    }
    cout<<ans;
}