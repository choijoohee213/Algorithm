#include<bits/stdc++.h>
typedef long long ll;
using namespace std;

int n,m,k;
vector<ll> v;
vector<ll> tree_arr;
vector<pair<int, pair<int, ll>>> command;

ll make_segmentTree(int node, int s, int e){
    if(s == e) return tree_arr[node] = v[s];

    int mid = (s + e) / 2;
    ll left_result = make_segmentTree(node * 2, s, mid);
    ll right_result = make_segmentTree(node * 2 + 1, mid+1, e);
    tree_arr[node] = left_result + right_result;
    return tree_arr[node];
}

void update_segmentTree(int node, int s, int e, int index, ll diff){
    if(index < s || index > e) return;
    tree_arr[node] = tree_arr[node] + diff;

    if(s != e){
        int mid = (s + e) / 2;
        update_segmentTree(node * 2, s, mid, index, diff);
        update_segmentTree(node * 2 + 1, mid+1, e, index, diff);
    }
}

ll Sum(int node, int s, int e, int l, int r){
    if(l > e || r < s) return 0;
    if(l <= s && e <= r) return tree_arr[node];

    int mid = (s + e) / 2;
    ll left_result = Sum(node * 2, s, mid, l, r);
    ll right_result = Sum(node * 2 + 1, mid+1, e, l, r);
    return left_result + right_result;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    cin>>n>>m>>k;

    for(int i=0; i<n; i++){
        int a;
        cin>>a;
        v.push_back(a);
    }

    for(int i=0; i<m+k; i++){
        int a,b;
        ll c;
        cin>>a>>b>>c;
        command.push_back({a,{b,c}});
    }

    int treeHeight = (int)ceil(log2(n));
    int treeSize = (1 << (treeHeight + 1));
    tree_arr.resize(treeSize);
    make_segmentTree(1,0,n-1);

    for(auto c : command){
        if(c.first == 1){
            int index = c.second.first - 1;
            ll value = c.second.second;
            ll diff = value - v[index];
            v[index] = value;
            update_segmentTree(1, 0, n-1, index, diff);
        }
        else {
            int l = c.second.first - 1;
            int r = c.second.second - 1;
            ll result = Sum(1, 0, n-1, l, r);
            cout<<result<<endl;
        }
    }
}