#include <string>

using namespace std;

string solution(int n, int t, int m, int p) {
    string answer = "0", s = "";
    int count = (t-1) * m + p, num=1, a=0, i=0;
    while(i<count){
        s = "";
        a = num++;
        while(a>0){
            int b = a % n;
            if(b >= 10 && b <= 15) s = (char)(b-10+'A') + s;
            else s = to_string(b) + s;
            a /= n;
            i++;
        }
        answer += s;
    }
    s = "";
    for(i=p-1; i<count; i+=m) s += answer[i];
    return s;
}
