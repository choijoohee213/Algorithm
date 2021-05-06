#include <bits/stdc++.h>
using namespace std;

bool visited[10000];
string c[4] = {"D", "S", "L", "R"};

int operation(int index, int num){
  if(index==0){
    num *= 2;
    if(num>9999) num %= 10000;
    return num;
  }
  else if(index==1){
    num = num-1 == -1 ? 9999 : num-1;
    return num;
  }
  else if(index==2){
    int n = num / 1000;
	  int temp = (num % 1000) * 10;
  	return temp+n;
  }
  else{
    int n = num % 10;
  	int temp = num / 10;
	  return 1000 * n + temp;
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
    
  int t,a,b;
  cin>>t;
  
  while(t--){
    cin>>a>>b;
    bool finish = false;
    int k;
    queue<pair<string, int>> q;
    memset(visited, false, sizeof(visited));
    
    for(int i=0; i<4; i++){
      k = operation(i, a);
      if(k == b) { 
        cout<<c[i]<<'\n'; 
        finish = true;
        break;
      }
      else {
        q.push({c[i], k});
        visited[k] = true;
      }
    }

    while(!q.empty() && !finish){
      string s = q.front().first; 
      int n = q.front().second; q.pop();
      
      for(int i=0; i<4; i++){  //DSLR
        string temp = s;
        int r = n;
        r = operation(i, r);
        temp += c[i];
        
        if(r == b){
          cout<<temp<<'\n';
          finish = true;
          break;
        }
        else if(!visited[r]){
          q.push({temp, r});
          visited[r] = true;              
        } 
      }
    }
  }   
}
