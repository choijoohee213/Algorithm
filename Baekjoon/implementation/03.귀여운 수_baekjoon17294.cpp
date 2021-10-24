#include<bits/stdc++.h>
using namespace std;

int main(){
    string s;
    cin>>s;
    int diff;
    bool result = true;

    for(int i=1; i<s.size(); i++){
        int x = s[i] - '0';
        int prev = s[i-1]-'0';
        if(i == 1){
            diff = prev - x;
            continue;
        }
        if(prev - x != diff){
            result = false;
            break;
        }
    }
    if(result) cout<<"?(?????)?..¡Æ¢½ ²î¿ä¹Ì!!";
    else cout<<"ÈïÄ©»×!! <(£ş ? £ş)>";
    º¸¼ºÀÌ ¹«½¼ÁË
}