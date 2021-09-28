#include<bits/stdc++.h>
using namespace std;
 
int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n;
  cin>>n;
  vector<int> v(n);
  for(int i=0; i<n; i++)
    cin>>v[i];
  
  long long result = 0;
  stack<int> st; 
  
  for(int i=0; i<n; i++){
    while(!st.empty() && st.top() <= v[i])
      st.pop();
    st.push(v[i]);
    result += st.size()-1; 
  }
  cout<<result; 
} 
