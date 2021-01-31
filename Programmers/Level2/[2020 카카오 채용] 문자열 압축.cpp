#include <string>

using namespace std;

int solution(string s) {
    int answer = s.length(), j=0, count=1;
    string str="", prev = "", temp = "";
    
    for(int i=1; i<=s.size()/2; i++){
        str = "";
        count = 1;
        j = 0;
        
        while(i+j<s.size()){
            prev = s.substr(j,i);
            temp = s.substr(j+i,i);
            if(prev == temp){
                count++;
            }
            else {
                if(count != 1) str += to_string(count);;
                str += prev;
                count = 1;
            }
            j += i;
        }
        
        if(count != 1) str += to_string(count) + prev;
        else str += s.substr(j);
        
        if(answer > str.size()) answer = str.size();
    }
    return answer;
}
