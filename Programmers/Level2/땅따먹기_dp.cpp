#include <vector>
using namespace std;

int solution(vector<vector<int> > land)
{
    int answer = 0, maxValue = 0, r = land.size()-2 == 0 ? 1 : land.size()-2;
    
    while(r>=0){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(i!=j && maxValue<land[r+1][j]) maxValue = land[r+1][j];
            }
            land[r][i] += maxValue;
            maxValue = 0;
        }
        r--;
    }
    
    for(int i=0; i<4; i++)
        if(maxValue < land[0][i]) maxValue = land[0][i];
    return maxValue;
}
