//https://codingwell.tistory.com/35

#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<vector<string>> clothes) {
    int answer = 1;
    unordered_map<string, int> uom;
    
    for(int i=0; i<clothes.size(); i++)
        uom[clothes[i][1]]++;
    
    for(auto it=uom.begin(); it!=uom.end(); it++)
        answer *= ((*it).second+1);
    answer--;
   
    return answer;
}
