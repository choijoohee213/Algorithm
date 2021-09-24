#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t,cnt,result=0;
  string s;
  cin>>t;
  
  while(t--){
    cin>>s;
    result = 0;
    cnt = s.length()-1;
    for(int i=0; i<s.length(); i++){
      if(s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O' || s[i] == 'U'){
        result += s.length()-1-i;
      }
    }
    cout<<result<<'\n';
  }
}
