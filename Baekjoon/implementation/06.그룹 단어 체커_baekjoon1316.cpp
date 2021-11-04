#include<bits/stdc++.h>
using namespace std;

int n,cnt=0;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);

  cin>>n;
  string s;
  for(int i=0; i<n; i++){                                                                                                                                                                                      
    cin>>s;
    int arr[26];
    memset(arr, -1, sizeof(arr));
    bool flag = true;
    
    for(int j=0; j<s.length(); j++){
      int c = s[j]-'a';
      if(arr[c] == -1){
        arr[c] = j;
      }
      else {
        if(arr[c] == j-1)
          arr[c] = j;
        else{
          flag = false;
          break;
        }
      }
    }
    if(flag) {
      cnt++;
    }
  }
  cout<<cnt;
} 