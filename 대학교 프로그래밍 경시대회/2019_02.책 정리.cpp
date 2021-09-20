#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t, n, m;
  float c;
  string s;
  cin>>t;
  
  while(t--){
    vector<pair<float,string>> v;
    cin>>n;
    for(int i=0; i<n; i++){
      cin>>c>>s;
      v.push_back({c,s});
    }
    
    cin>>m;
    for(int i=0; i<m; i++){
      cin>>c>>s;
      v.push_back({c,s});  
    }
    sort(v.begin(), v.end());
  
    for(auto a : v)
      cout<<a.first<<" "<<a.second<<'\n';
  }
}
