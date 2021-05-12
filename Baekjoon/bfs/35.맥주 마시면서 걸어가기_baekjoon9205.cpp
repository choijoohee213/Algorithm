#include <bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t,n,startX,startY,endX,endY;
  bool visited[100];
  cin>>t;
  
  while(t--){
    cin>>n;
    vector<pair<int,int>> v;
    queue<pair<int,int>> q;
    memset(visited, false, sizeof(visited));
    
    cin>>startX>>startY; 
    for(int i=0; i<n+1; i++){
      int a,b;
      cin>>a>>b;
      v.push_back({a,b});
    }
    
    bool stop = false;
    q.push({startX,startY});
    
    while(!q.empty() && !stop){
      int x = q.front().first;
      int y = q.front().second; q.pop();
            
      for(int i=0; i<v.size(); i++){
        if(!visited[i] && (abs(x - v[i].first) + abs(y - v[i].second)) <= 1000){
          q.push({v[i].first, v[i].second});
          visited[i] = true;  
        }
        
        if((abs(x - v[n].first) + abs(y - v[n].second)) <= 1000){
          stop = true;
          break;
        }
      }
    }
    if(stop) cout<<"happy\n";
    else cout<<"sad\n";
  }
}
