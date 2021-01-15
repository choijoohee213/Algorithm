#include <string>
#include <stack>

using namespace std;

string solution(string number, int k) {
    string answer = "";
    int size = number.size(), n = size-k, i = 1,  mIndex = 0, mStack = 0;
    stack<char> st;
    st.push(number[0]);

    if(n == 0) return answer;
    while(i<size){
        mIndex = size-i, mStack = n-st.size();
        if(!st.empty() && mIndex > mStack && st.top() < number[i])
            st.pop();
        else {
            st.push(number[i]);
            i++;
        }
    }
    
    while(!st.empty()){
        answer = st.top() + answer;
        st.pop();
    }
    
    if(answer.size() > n) answer = answer.substr(0,n);
    return answer;
}
