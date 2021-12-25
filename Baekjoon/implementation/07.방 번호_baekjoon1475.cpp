#include<bits/stdc++.h>
using namespace std;

int main() {
    int n, cnt=0, cur;
    unordered_map<int, int> uom;
    cin>>n;
    string s = to_string(n);
    for(int i=0; i<s.length(); i++) {
        cur = s[i] - '0';
        if(cur == 6 && uom[cur] == cnt && uom[9] < cnt) {
            cur = 9;
        }
        else if(cur == 9 && uom[cur] == cnt && uom[6] < cnt) {
            cur = 6;
        }
        if(uom[cur] == cnt) {
            cnt++;
        }            
        uom[cur]++;
    }
    cout<<cnt;
}