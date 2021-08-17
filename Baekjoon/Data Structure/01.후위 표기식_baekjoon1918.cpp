#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  string s;
  stack<char> st;
  cin>>s;
  
  for(int i=0; i<s.size(); i++){
    if(s[i] == ')'){
      while(true){
        char c = st.top(); st.pop();
        if(c == '(') break;
        else cout<<c;
      }
    }
    else if(s[i]>='A' && s[i]<='Z'){
      cout<<s[i];
    }
    else if(s[i] == '+' || s[i]=='-'){
      while(!st.empty()){
        if(st.top() != '('){
          char c = st.top(); st.pop();
          cout<<c;
        }
        else break;
      }
      st.push(s[i]);
    }
    else if(s[i] == '*' || s[i] == '/'){
      while(!st.empty()){
        if(st.top() == '*' || st.top() == '/'){
          char c = st.top(); st.pop();
          cout<<c;
        }
        else break;
      }
      st.push(s[i]);
    }
    else st.push(s[i]);
  }
  
  while(!st.empty()){
    char c = st.top(); st.pop();
    cout<<c;
  }
} 
