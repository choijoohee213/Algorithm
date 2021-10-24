#include<bits/stdc++.h>
using namespace std;

string s;
stack<char> st;

bool popPPAP(){
    st.pop();
    
    for(int i=0; i<2; i++){
        if(st.empty()) return false;
        if(st.top() == 'P'){
            st.pop();
        }
        else return false;
    }
    st.push('P');
    return true;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    cin>>s;
    st.push(s[0]);

    for(int i=1; i<s.size(); i++){
        if(s[i] == 'P' && !st.empty() && st.top() == 'A'){
            if(!popPPAP()){
                cout<<"NP";
                return 0;
            }
        }
        else st.push(s[i]);
    }

   if(st.size() == 1 && st.top() == 'P') cout<<"PPAP";
   else cout<<"NP";
}