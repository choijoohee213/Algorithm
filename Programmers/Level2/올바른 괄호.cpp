#include <string>
#include <stack>

using namespace std;

bool solution(string s)
{
    int i = 1;
    stack<char> st;
    
    st.push(s[0]);
    while(i<s.size()){
        if(!st.empty() && st.top() == '(' && s[i] == ')')
            st.pop();
        else st.push(s[i]);
        i++;
    }
    return st.empty();
}
