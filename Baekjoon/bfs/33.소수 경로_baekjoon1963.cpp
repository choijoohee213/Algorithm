#include<bits/stdc++.h>
using namespace std;

int t,a,b;
int d[9999] = {0,};

bool isPrimeNum(int& n){
  int maxN = (int)sqrt(n);
  for(int i=2; i<=maxN; i++){
    if(n%i == 0) return false;
  }
  return true;
}

int bfs(int& a){
  queue<int> q;
  q.push(a);
  d[a] = 0;

  while(!q.empty()){
    int x = q.front(); q.pop();
    string s = to_string(x);
    
    for(int i=0; i<4; i++){
      for(int j=48; j<=57; j++){
        if(s[i] == j) continue;
        char c = (char)j;
        string temp = s;
        temp[i] = c;
                
        int nx = stoi(temp);
        if(nx == b) return d[x] + 1;
        if(nx >= 1000 && d[nx]==0 && isPrimeNum(nx)){
          q.push(nx);
          d[nx] = d[x] + 1;
        }
      }
    }
  } 
  return -1;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>t;
  
  while(t--){
    cin>>a>>b;
    if(a==b){
      cout<<0<<'\n';
      continue;
    }
    memset(d, 0, sizeof(d));
    
    int result = bfs(a);
    if(result != -1) cout<<result<<'\n';
    else cout<<"Impossible\n";
  }
}
