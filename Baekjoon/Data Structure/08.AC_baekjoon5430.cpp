#include<bits/stdc++.h>
using namespace std;

int t,n;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);

  cin>>t;
  while(t--){
    string s,arr;
    cin>>s>>n>>arr;
    
    string temp = "";
    int l=0, r;
    bool left = true;
    vector<int> v;
    
    for(int i=0; i<arr.length(); i++){  
      if(arr[i] == ',' || arr[i] == ']'){
        if(temp == "") continue;
        v.push_back(stoi(temp));
        temp = "";
      }
      else if(arr[i]>='0' && arr[i]<='9') temp += arr[i];
    }
    
    bool isError = false;
    r = v.size()-1;   
    
    for(char c : s){
      if(c == 'R') left = !left;
      else if(c == 'D'){
        if(l>=v.size() || r<0){
          isError = true;
          break;
        } 
        if(left) l++;
        else r--;
      }
    }
    
    if(!isError){
      cout<<'[';
      if(left){
        for(int j = l; j<=r; j++){
          cout<<v[j];
          if(j!=r) cout<<',';
        }  
      }
      else{
        for(int j = r; j>=l; j--){
          cout<<v[j];
          if(j!=l) cout<<',';
        } 
      }
      cout<<"]\n";
    }
    else cout<<"error\n";
  }
} 