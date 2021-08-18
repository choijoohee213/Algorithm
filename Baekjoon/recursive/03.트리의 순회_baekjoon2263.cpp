#include<bits/stdc++.h>
using namespace std;

int postOrder[100001];
int inOrder[100001];
int findRoot[100001];

void preOrder(int s1, int e1, int s2, int e2){
  if(s1 > e1 || s2 > e2) return;
  int root = postOrder[e2];
  cout<<root<<" ";
  int pos = findRoot[root];
  
  preOrder(s1, pos-1, s2, s2 + (pos-1-s1));
  preOrder(pos+1, e1, s2 + pos - s1, e2 - 1);
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n;
  cin>>n;
  for(int i=0; i<n; i++){
    cin>>inOrder[i];
    findRoot[inOrder[i]] = i;
  }
  for(int i=0; i<n; i++) cin>>postOrder[i];
  preOrder(0, n-1, 0, n-1);
}
