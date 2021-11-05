#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int sum = 0, x, diff, prev_diff=200;
    for(int i=0; i<10; i++){
        cin>>x;
        sum += x;
        diff = abs(100 - sum);
        if(diff>prev_diff){
            cout<<sum-x;
            return 0;
        }
        prev_diff = diff;
    }
    cout<<sum;
}