#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int n; 
    string s;
    cin >> s >> n;
    list<char> l(s.begin(),s.end());  
    auto cursor = l.end();
    
    while(n--){ 
        char tmp;
        cin >> tmp;
        
        if(tmp == 'L'){
            if(cursor != l.begin()){
                cursor--;
            }
        } 
        else if(tmp == 'D'){
            if(cursor != l.end()){
                cursor++;
            }
        }
        else if(tmp == 'B'){
            if(cursor != l.begin()){
                cursor = l.erase(--cursor);
            }
        }
        else if(tmp == 'P'){
            char c;
            cin >> c;
            l.insert(cursor, c);
        }    
    }
    for (auto it = l.begin(); it != l.end(); it++) {
        cout << *it;
    }
    return 0;     
}
