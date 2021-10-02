#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int n;
    cin>>n;
    string s;
    int d[26] = {0,};

    for(int i=0; i<n; i++){
        cin>>s;
        for(int j=0; j<s.length(); j++){
            d[s[j]-'a']++;
        }
    }

    bool flag = false;
    for(int i=0; i<26; i++){
        if(d[i]!=0){
            if(d[i] % n == 0) 
                flag = true;
            else{
                flag = false;
                break;
            }
        }
    }
    if(flag || n == 1) cout<<"true";
    else cout<<"false";
}