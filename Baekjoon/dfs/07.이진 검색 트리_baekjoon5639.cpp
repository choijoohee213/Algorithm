#include<iostream>
using namespace std;

struct Node{
  int data;
  Node* left;
  Node* right;
};

Node* insert(Node* node, int data){
  if(node == NULL){
    node = new Node();
    node->data = data;
    node->left = NULL;
    node->right = NULL;
  }
  else if(data <= node->data)
    node->left = insert(node->left, data);
  else
    node->right = insert(node->right, data);
  return node;
}

void postorder(Node* node){
  if(node->left != NULL)
    postorder(node->left);
  if(node->right != NULL)
    postorder(node->right);
  cout<<node->data<<'\n';
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  Node* root = NULL;
  int x;
  while(cin>>x){
    if(x == EOF) break;
    root = insert(root, x);
  }
  postorder(root);
}
