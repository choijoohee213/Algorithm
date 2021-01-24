#include <string>
#include <vector>
#include <unordered_map>
#include <sstream>

using namespace std;

vector<string> solution(vector<string> record) {
    vector<string> answer;
    string command, id, nickname;
    unordered_map<string,string> uom;
    
    for(int i=0; i<record.size(); i++){
        stringstream sst(record[i]);
        sst>>command;
        if(command[0] == 'E' || command[0] == 'C'){
            sst>>id;
            sst>>nickname;
            uom[id] = nickname;
        }
    }
    
    for(int i=0; i<record.size(); i++){
        stringstream sst(record[i]);
        sst>>command;
        if(command[0] == 'E'){
            sst>>id;
            answer.push_back(uom[id] + "님이 들어왔습니다.");
        }
        else if(command[0] == 'L'){
            sst>>id;
            answer.push_back(uom[id] + "님이 나갔습니다.");
        }
    }
    return answer;
}
