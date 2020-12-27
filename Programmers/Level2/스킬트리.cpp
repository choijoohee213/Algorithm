#include <string>
#include <vector>

using namespace std;

int solution(string skill, vector<string> skill_trees) {
    int answer = 0, index = 1;
    int d[26] = {0,};

    for(int i=1; i<=skill.length(); i++)
        d[skill[i-1]-'A'] = i;
    
    for(int i=0; i<skill_trees.size(); i++){
        index = 1;
        string st = skill_trees[i];
        for(int j=0; j<st.length(); j++){
            int n = d[st[j]-'A'];
            if(n!= 0 && n != index) {
                index = -1; 
                break;
            }
            else if(n == index) index++; 
        }
        if(index != -1) answer++;
    }
    
    return answer;
}
