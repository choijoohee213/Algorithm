#include <string>
#include <stack>

using namespace std;

stack<char> st;

string check(string p){
    if(p == "") return p;
    int i = 1;
    bool correct = true;
    string u = "", v = "", s = "";
    st.push(p[0]);

    for(; i<p.size(); i++){
        if(st.empty()) break;
        char c = st.top();
        if(c == '(' && p[i] == ')') st.pop();
        else if(c == ')' && p[i] == '('){
            st.pop();
            correct = false;
        }
        else st.push(p[i]);
    }
    u = p.substr(0,i);
    v = p.substr(i);
    
    if(correct) s += u + check(v);
    else{ 
        s += '(';
        s += check(v);
        s += ')';
        for(int i=0; i<u.size(); i++){
            if(i!=0 && i!=u.size()-1){
                u[i] == ')'? s += '(' : s += ')';
            }
        }
    }
    return s;
}

string solution(string p) {
    string answer = "";
    answer = check(p);
    return answer;
}
