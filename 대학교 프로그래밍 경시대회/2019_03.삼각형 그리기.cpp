#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t;
  long long x[3], y[3];
  cin>>t;
  
  while(t--){
    cin>>x[0]>>y[0]>>x[1]>>y[1]>>x[2]>>y[2];
   
    long long ay = y[0] - y[1];
    long long ax = x[0] - x[1];
    long long by = y[1] - y[2];
    long long bx = x[1] - x[2];
        
    if(ay * bx != by * ax) cout<<"Yes\n";
    else cout<<"No\n";
  }
}
