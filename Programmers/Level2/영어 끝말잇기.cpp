#include <string>
#include <vector>
#include <set>

using namespace std;

vector<int> solution(int n, vector<string> words) {
    vector<int> answer(2,0);
    set<string> st;
    pair<set<string>::iterator, bool> pr;
    
    int i=0, j=1;
    char c;
    
    while(i<words.size()){
        string s = words[i];
        pr = st.insert(s);
        if((i!=0 && c != s[0]) || !pr.second) {
            answer[0] = i%n + 1;
            answer[1] = j;
            break;
        }
        i++;
        c = s[s.size()-1];
        if(i % n == 0) j++;
    }
    
    return answer;
}
