#include <string>
#include <vector>

using namespace std;

int solution(string s) {
    int answer = s.length(), count = 1, n = s.length()/2;
    string a="", b="", result = "";
    vector<string> v;
    
    for(int i=1; i<=n; i++){
        int j=0;
        count = 1;
        result = "";
        while(i+j<s.length()){
            a = s.substr(j,i);
            b = s.substr(j+i,i);
            if(a == b){ 
                count++;
            }
            else{
                if(count != 1) result += to_string(count);
                result += a;
                count = 1;
            }
            j+=i;
        }
        if(count != 1){
            result += to_string(count);
            result += a;
        }
        else result += s.substr(j, s.length()-j);
        v.push_back(result);
    }
    
    for(int i=0; i<v.size(); i++){
        a = v[i];
        if(answer > a.size())
            answer = a.size();
    }
    return answer;
}
