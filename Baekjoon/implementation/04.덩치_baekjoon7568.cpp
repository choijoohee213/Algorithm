#include<bits/stdc++.h>
using namespace std;

int main(){
  int n;
  cin>>n;
  vector<pair<int,int>> v;
  vector<int> result(n+1, 1);

  for(int i=0; i<n; i++){
    int x,y;
    cin>>x>>y;
    v.push_back({x,y});
  }

  for(int i=0; i<n; i++){
    for(int j=0; j<n; j++){
      if(v[i].first>v[j].first && v[i].second>v[j].second){
        result[j]++;
      }
    }
  }

  for(int i=0; i<n; i++){
    cout<<result[i]<<' ';
  }
}