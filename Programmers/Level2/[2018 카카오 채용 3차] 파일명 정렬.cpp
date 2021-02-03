//https://codingwell.tistory.com/43

#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class FileInfo {
public:
    string head = "";
    int number = 0;
    int index = 0;
    
    bool operator<(FileInfo& fi){
        if(this->head == fi.head){
            if(this->number == fi.number) return this->index < fi.index;
            else return this->number < fi.number;
        }
        else return this->head < fi.head;
    }
};

vector<string> solution(vector<string> files) {
    vector<string> answer;
    vector<FileInfo> v;
    
    for(int i=0; i<files.size(); i++){
        string s = files[i], str;
        FileInfo fi;
        fi.index = i;
        for(int j=0; j<s.size(); j++){
            if(s[j] >='0' && s[j] <= '9'){ 
                //head부분
                str = s.substr(0,j);
                transform(str.begin(),str.end(),str.begin(),
                              [](unsigned char c){ return toupper(c); });
                fi.head = str;
                
                //number부분
                str = s.substr(j);
                fi.number = (int)stoi(str);
                v.push_back(fi);
                break;
            }
        }
    }
    sort(v.begin(), v.end());
    for(int i=0; i<v.size(); i++) answer.push_back(files[v[i].index]);
    return answer;
}
