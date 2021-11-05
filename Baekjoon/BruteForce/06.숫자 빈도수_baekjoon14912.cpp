#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    
    int n,d,cnt=0;
    cin>>n>>d;
    string s, digit = to_string(d);

    for(int i=1; i<=n; i++){
        s = to_string(i);
        for(int j=0; j<s.size(); j++){
            if(digit[0] == s[j]) cnt++;
        }      
    }
    cout<<cnt;
}