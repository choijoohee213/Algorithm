#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);

  int n;
  cin>>n;
  bool asc = true;
  if(n == 1) asc = true;  
  else if(n == 8) asc = false;
  else{
    cout<<"mixed";
    return 0;
  }
  for(int i=1; i<8; i++){
    cin>>n;
    if((asc && n!=i+1) || (!asc && n!=8-i)){     
        cout<<"mixed";
        return 0;
    }
  }
  if(asc) cout<<"ascending";
  else cout<<"descending";
} 