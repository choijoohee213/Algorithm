#include<bits/stdc++.h>
using namespace std;

int t,n,m;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);

  int n;
  cin>>n;
  stack<int> st;
  vector<bool> d(n+1, false);
  string result = "";
  int num = 1;
  
  for(int i=1; i<=n; i++){
    int x;
    cin>>x;
    if(!d[x]){
      while(!d[x] && num<=n){
        st.push(num);
        result += "+\n";
        d[num++] = true;
      }
      if(d[x] && st.top() == x){
        st.pop();
        result += "-\n";
        d[x] = false;
      }
      else if(num>n){
        cout<<"NO";
        return 0;
      }
    }
    else if(st.top() == x){
      st.pop();
      result += "-\n";
      d[x] = false;
    }
    else{
      cout<<"NO";
      return 0;
    }  
  }

  cout<<result;
}