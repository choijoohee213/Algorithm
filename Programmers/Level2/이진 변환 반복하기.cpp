#include <string>
#include <vector>

using namespace std;

vector<int> solution(string s) {
    vector<int> answer(2);
    int i=0, rmZero=0, count=0, n=0;
    while(true){
        rmZero = 0;
        for(i=0; i<s.size(); i++)
            if(s[i] == '0') rmZero++;
        n = s.size() - rmZero;
        s = "";
        while(n>0){
            s = s + to_string(n % 2);
            n /= 2;
        }
        count++;
        answer[1] += rmZero;
        if(s == "1"){
            answer[0] = count;
            return answer;
        }
    }
}
