#include<bits/stdc++.h>
using namespace std;

string s, result = "";
int cnt = 0;

void greedy(){
    if(cnt % 2 != 0){
        cout<<-1;
        exit(0);
    }

    while(cnt>0){
        if(cnt>=4){
            cnt -= 4;
            result += "AAAA";
        }
        else if(cnt>=2){
            cnt -= 2;
            result += "BB";
        }
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    cin>>s;

    for(int i=0; i<s.size(); i++){
        if(s[i] == '.' && cnt>0){
            greedy();
            cnt = 0;
        }
        if(s[i] == '.') result += '.';
        if(s[i] == 'X') cnt++;
    }
    if(cnt>0) greedy();
    cout<<result;
}