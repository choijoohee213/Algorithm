#include <string>
#include <vector>
#include <cmath>

using namespace std;

string answer = "";
int l = 9, r = 11;

int compareDistance(int dest){
    int destX = dest / 3, destY = dest % 3;
    int leftD = abs(l/3 - destX) + abs(l%3 - destY);
    int rightD = abs(r/3 - destX) + abs(r%3 - destY);       
    if(leftD > rightD){
        r = dest;
        answer += "R";
    }
    else if(leftD < rightD){
        l = dest;
        answer += "L";
    }
    else
        return 1;
    return 0;
}

string solution(vector<int> numbers, string hand) {
    for(int i=0; i<numbers.size(); i++){
        int dest = numbers[i] - 1;
        if(dest == -1) dest = 10;
        
        if(dest == 0 || dest == 3 || dest == 6){
            l = dest;
            answer += "L";
        }
        else if(dest == 2 || dest == 5 || dest == 8){
            r = dest;
            answer += "R";
        }
        else {
            if(compareDistance(dest)){
                if(hand == "left"){
                    l = dest;
                    answer += "L";
                }
                else {
                    r = dest;
                    answer += "R";
                }
            }
        }
    }
    return answer;
}
