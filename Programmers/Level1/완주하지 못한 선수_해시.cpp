#include <string>
#include <vector>
#include <unordered_set>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    unordered_multiset<string> st;
    
    for(int i=0; i<participant.size(); i++){
        st.insert(participant[i]);
    }
    
    for(int i=0; i<completion.size(); i++){
        auto it = st.find(completion[i]);
        st.erase(it);
    }
    
    answer = *(st.begin());
    
    return answer;
}
