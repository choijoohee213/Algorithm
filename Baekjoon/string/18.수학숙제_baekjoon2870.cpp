#include<bits/stdc++.h>
using namespace std;

bool comp(string a, string b){
  if(a.size() < b.size()) return true;
  else if(a.size() == b.size()){
    if(a<b) return true;
  }
  return false;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n;
  string s;
  vector<string> v;
  cin>>n;
  
  for(int i=0; i<n; i++){
    cin>>s;
    string tmp = "";
    for(int j=0; j<s.length(); j++){
      if(s[j]>='a' && s[j]<='z') {
        if(tmp != ""){
          v.push_back(tmp);
          tmp = "";  
        }
      }
      else tmp += s[j];
    }
    if(tmp!="") v.push_back(tmp);
  }
  
  for(int i=0; i<v.size(); i++){
    if(v[i][0] != '0') continue;
    
    int j;
    bool flag = false;
    for(j=1; j<v[i].size(); j++){
      if(v[i][j] != '0') {
        flag = true;
        break;
      }
    }
    if(flag) v[i] = v[i].substr(j);
    else if(v[i][v[i].size()-1] == '0') v[i] = "0";
  }
  sort(v.begin(), v.end(), comp);
  for(string x : v) cout<<x<<endl;
} 
