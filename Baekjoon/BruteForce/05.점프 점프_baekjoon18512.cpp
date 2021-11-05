#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int x,y,p1,p2,diff,prev_diff=200;
    cin>>x>>y>>p1>>p2;
    unordered_map<int,int> uom;

    for(int i=0; i<100; i++){
        uom[p1] = 1;
        p1 += x;
    }

    for(int i=0; i<100; i++){
        if(uom[p2]>0){
            cout<<p2;
            return 0;
        }
        p2 += y;
    }
    cout<<-1;
}