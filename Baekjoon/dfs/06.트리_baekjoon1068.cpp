#include<bits/stdc++.h>
using namespace std;

int n, deleteNode, result=0;
bool deletedNode[50] = {false,};
vector<int> tree[50];

void deleteN(int node){
  deletedNode[node] = true;
  for(int i=0; i<tree[node].size(); i++)
    deleteN(tree[node][i]);
}

void findLeaf(int node){  
  int dnode = 0;
  for(int i=0; i<tree[node].size(); i++){
    int ch = tree[node][i];
    if(deletedNode[ch])
      dnode++;
    else 
      findLeaf(ch);
  }
  
  if(dnode == tree[node].size())
    result++;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n;
  
  int rootnode = 0;
  for(int i=0; i<n; i++){
    int x;
    cin>>x;
    if(x == -1){
      rootnode = i;
      continue;
    }
    tree[x].push_back(i);
  }
  cin>>deleteNode;
  if(deleteNode == rootnode){
    cout<<0;
    return 0;
  }
  deleteN(deleteNode);
  findLeaf(rootnode);
  cout<<result;
}
