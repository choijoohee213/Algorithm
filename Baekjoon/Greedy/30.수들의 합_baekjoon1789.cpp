#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    long long s;
    int n = 0;
    cin>>s;

    while(true){
        if(s >= ++n) 
            s -= n;
        else break;
    }
    cout<<n-1;
}