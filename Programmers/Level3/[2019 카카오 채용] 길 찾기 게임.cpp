#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> answer = {{}, {}};

struct Node {
    int x,y;
    int num;
    Node* left;
    Node* right;
};

bool compare(Node& a, Node& b){
    if(a.y != b.y) return a.y > b.y;
    else return a.x < b.x;
}

void preorder(Node* node){
    answer[0].push_back(node->num);
    if(node->left != NULL) preorder(node->left);
    if(node->right != NULL) preorder(node->right);
}

void postorder(Node* node){
    if(node->left != NULL) postorder(node->left);
    if(node->right != NULL) postorder(node->right);
    answer[1].push_back(node->num);
}

void addNode(Node* parent, Node* child){
    if(child->x < parent->x){
        if(parent->left == NULL)
            parent->left = child;
        else addNode(parent->left, child);
    }
    else{
        if(parent->right == NULL)
            parent->right = child;
        else addNode(parent->right, child);
    }
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    vector<Node> nodes;
    for(int i=0; i<nodeinfo.size(); i++){
        Node node;
        node.x = nodeinfo[i][0];
        node.y = nodeinfo[i][1];
        node.num = i+1;
        node.left = NULL;
        node.right = NULL;
        nodes.push_back(node);
    }
    sort(nodes.begin(), nodes.end(), compare);
    
    for(int i=1; i<nodes.size(); i++){
        addNode(&nodes[0], &nodes[i]);
    }
    preorder(&nodes[0]);
    postorder(&nodes[0]);
    return answer;
}
