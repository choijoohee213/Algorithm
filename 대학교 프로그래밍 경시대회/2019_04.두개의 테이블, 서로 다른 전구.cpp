#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t,x,y,n;
  cin>>t;
  
  while(t--){
    cin>>x>>y;
    int result = x ^ y;
    n = 0;
    while(result != 0){
      if(result % 2) n++;
      result /= 2;
    }
    cout<<n<<'\n';
  }
}
