#include <string>
#include <vector>
#include <stdlib.h>
#include <algorithm>

using namespace std;

int solution(int n, vector<string> data) {
    int answer = 0, distance = 0, interval = 0;
    char c = 'a';
    bool satisfied = true;
    string friends = "ACFJMNRT";

    do {
        satisfied = true;
        for(int i=0; i<data.size(); i++){
            distance = abs((int)(friends.find(data[i][0])-friends.find(data[i][2])))-1;
            interval = data[i][4] - '0';
            c = data[i][3];
            
            if(!(c == '=' && distance == interval)
               && !(c == '>' && distance > interval) 
               && !(c == '<' && distance < interval)) {
                satisfied = false;
                break;
            }
        }
        if(satisfied) answer++;
    } while(next_permutation(friends.begin(), friends.end()));

    return answer;
}
