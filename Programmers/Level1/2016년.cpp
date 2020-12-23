#include <string>

using namespace std;

string solution(int a, int b) {
    string answer = "";
    int months[12] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    string days[12] = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
    
    for(int i=0; i<a-1; i++)
        b += months[i]; 
    
    answer = days[b % 7];
    return answer;
}
