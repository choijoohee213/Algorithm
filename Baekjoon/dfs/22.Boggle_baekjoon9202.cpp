#include<bits/stdc++.h>
using namespace std;

int w,b,max_index,max_len,max_score,word_cnt;
int scores[9] = {0, 0, 0, 1, 1, 2, 3, 5, 11};
vector<string> words;
char board[4][4];
bool found[300001];
bool visited[4][4];
int dx[8] = {1,0,0,-1,-1,1,1,-1};
int dy[8] = {0,1,-1,0,-1,1,-1,1};

void dfs(int word, int index, int x, int y){
  if(words[word].length() == index && !found[word]){
    found[word] = true;
    max_score += scores[index];
    word_cnt++;
    if(index > max_len){
      max_len = index;
      max_index = word;
    }
  }
  else{
    for(int i=0; i<8; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      
      if(nx<0 || nx>=4 || ny<0 || ny>=4) continue;
      if(visited[nx][ny] || board[nx][ny] != words[word][index]) continue;
      visited[nx][ny] = true;
      dfs(word, index+1, nx, ny);
      visited[nx][ny] = false;
    }
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>w;
  for(int i=0; i<w; i++){
    string s;
    cin>>s;
    words.push_back(s);
  }
  sort(words.begin(), words.end());
  
  cin>>b;
  for(int i=0; i<b; i++){
    for(int j=0; j<4; j++){
      for(int k=0; k<4; k++){
        cin>>board[j][k];
      }
    }
    
    for(int i=0; i<w; i++) found[i] = 0;
    max_score = 0;
    max_len = -1;
    max_index = 0;
    word_cnt = 0;
    
    for(int i=0; i<w; i++){
      for(int j=0; j<4; j++){
        for(int k=0; k<4; k++){
          if(board[j][k] == words[i][0] && !found[i]){
            visited[j][k] = true;
            dfs(i,1,j,k);
            visited[j][k] = false;
          }
        }
      }
    }
    cout<<max_score<<' '<<words[max_index]<<' '<<word_cnt<<'\n';
  }
}
