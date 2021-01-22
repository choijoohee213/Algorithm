#include <stack>
#include <string>
using namespace std;

int solution(string s)
{
    int i=1;
    stack<char> st;
    st.push(s[0]);
    
    while(i<s.size()){
        if(!st.empty() && st.top() == s[i]) st.pop();
        else st.push(s[i]);
        i++;
    } 
    
    return st.empty();
}
