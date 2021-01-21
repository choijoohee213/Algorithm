#include <string>
#include <stack>
#include <algorithm>

using namespace std;

string s = "",result = "";
stack<pair<char, int> > st;

void pushStack(char c, int i){
    if(!st.empty()){
        int a = s.find(c), b;
        while(!st.empty()){
            char cc = st.top().first;
            b = s.find(cc);
            if(a < b || (a == b && i > st.top().second)){
                st.pop();
                result += cc;
            }
            else break;
        }
    }
    st.push(make_pair(c,i));
}

long long calculate(){
    string num = "";
    stack<long long> cal;
    long long a = 0, b = 0;
    
    for(int i=0; i<result.size(); i++){
        char c = result[i];
        if(c == '-' || c == '+' || c == '*'){
            if(num != "")cal.push(stoll(num)); 
            b = cal.top(); cal.pop();
            a = cal.top(); cal.pop();
            if(c == '-') cal.push(a - b);
            else if(c == '+') cal.push(a + b);
            else cal.push(a * b);
            num = "";
        }
        else if (c == ' ' && num != ""){ cal.push(stoll(num)); num = "";}
        else if(c >= '0' && c <= '9') num += c; 
    }
    return cal.top() < 0 ? -cal.top() : cal.top();
}

long long solution(string expression) {
    long long answer = 0;
    bool plus = false, minus = false, multi = false;
    
    for(int i=0; i<expression.size(); i++){
       if(!plus && expression[i] == '+'){ plus = true; s += '+';}
       else if(!minus && expression[i] == '-'){ minus = true; s += '-';}
       else if(!multi && expression[i] == '*'){ multi = true; s += '*';} 
    }
    
    sort(s.begin(), s.end());
    
    do{
        result = "";
        for(int i=0; i<expression.size(); i++){
            char c = expression[i];
            if(c == '-' || c == '+' || c == '*'){
                pushStack(c,i);
                result += ' ';
            }
            else result += c; 
        }
        
        while(!st.empty()){
            result += st.top().first;
            st.pop();
        }
        
        answer = max(answer, calculate());    
    } while(next_permutation(s.begin(), s.end()));
    return answer;
}
