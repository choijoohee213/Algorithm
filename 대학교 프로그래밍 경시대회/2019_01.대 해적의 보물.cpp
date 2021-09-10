#include<bits/stdc++.h>
using namespace std;

string solution(string a, string b){
  if(b.length() > a.length()) return solution(b,a);
  if(b.empty()) return a;
  if(a.substr(0, b.size()) != b) return "";
  return solution(a.substr(b.size()), b);
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);
  
  int t;
  string l,m;
  cin>>t;
  
  while(t--){
    cin>>l>>m;
    cout<<solution(l,m)<<'\n';
  }
}
