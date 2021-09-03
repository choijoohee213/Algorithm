#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t,a,b;
  cin>>t;
  
  for(int i=0; i<t; i++){
    cin>>a>>b;
    int r = a%b;
    if(r>=0) cout<<r<<'\n';
    else cout<<b+r<<'\n';
  }
}
