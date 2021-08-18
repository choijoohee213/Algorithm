#include<bits/stdc++.h>
using namespace std;

int n, result = INT_MIN;
string s;

int cal(int a, int b, char op){
  switch(op){
    case '+': a += b; break;
  	case '*': a *= b; break;
  	case '-': a -= b; break;
  }
  return a;
}

void dfs(int index, int num){
  if(index > n-1){
    result = max(result, num);
    return;
  }
  
  char op = (index == 0)? '+' : s[index-1];
  
  //°ıÈ£·Î ¹­±â
  if(index + 2 < n){
    int br = cal(s[index] - '0', s[index+2] - '0', s[index+1]);
    dfs(index+4, cal(num, br, op));
  }
  //°ıÈ£·Î ¾È¹­±â
  dfs(index+2, cal(num, s[index]-'0', op));
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>s;
  dfs(0,0);
  cout<<result;
}
