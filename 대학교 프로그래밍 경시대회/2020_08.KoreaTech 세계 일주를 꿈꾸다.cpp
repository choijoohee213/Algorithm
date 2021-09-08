#include<bits/stdc++.h>
using namespace std;

int t,c;

int trip(vector<pair<int,int>>& d){
  long long sum = 0, tank = 0, index = 0;
  
  for(int i=0; i<c; i++){
    if((tank += (d[i].first - d[i].second)) < 0){
      sum += tank;
      index = i + 1;
      tank = 0;
    }
  }
  return ((sum + tank) < 0)? -1 : index;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>t;
  
  while(t--){
    cin>>c;
    vector<pair<int,int>> d(c+1);
    for(int i=0; i<c; i++) cin>>d[i].first;
    for(int i=0; i<c; i++) cin>>d[i].second;
    cout<<trip(d)<<'\n';
  }
}
