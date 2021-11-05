#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int n,ans=-9999;
    cin>>n;
    vector<int> v(n);
    for(int i=0; i<n; i++) cin>>v[i];

    sort(v.begin(), v.end());
    do {
        int result = 0;
        for(int i=0; i<n-1; i++)
            result += abs(v[i]-v[i+1]);
        ans = max(ans, result);
    } while(next_permutation(v.begin(), v.end()));
    cout<<ans;
}